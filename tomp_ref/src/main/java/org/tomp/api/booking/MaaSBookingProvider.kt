package org.tomp.api.booking

import io.swagger.client.ApiException
import io.swagger.model.Booking
import io.swagger.model.BookingOperation
import io.swagger.model.BookingOperation.OperationEnum
import io.swagger.model.BookingRequest
import io.swagger.model.BookingState
import io.swagger.model.Card
import io.swagger.model.CardType
import io.swagger.model.ConditionRequireBookingData
import io.swagger.model.ConditionRequireBookingData.RequiredFieldsEnum
import io.swagger.model.Planning
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.tomp.api.model.Segment
import org.tomp.api.model.TransportOperator
import org.tomp.api.model.Trip
import org.tomp.api.repository.MPRepository
import org.tomp.api.utils.ClientUtil
import java.util.AbstractMap
import java.util.Arrays
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.booking"], havingValue = "maasprovider", matchIfMissing = false)
class MaaSBookingProvider : GenericBookingProvider() {
    @Autowired
    var maasRepository: MPRepository? = null

    @Autowired
    override var clientUtil: ClientUtil? = null
    override fun addNewBooking(body: @Valid BookingRequest?, acceptLanguage: String?): Booking? {
        log.info("Book option {}", body!!.id)
        val savedOption = maasRepository!!.getTrip(body.id)
        if (savedOption != null) {
            log.info("Found option {}", body.id)
            val booking = Booking()
            booking.state = BookingState.PENDING
            booking.id = body.id
            log.info("Book legs")
            val state = bookAllLegs(body, savedOption)
            repository!!.saveBooking(booking)
            // all bookings in pending
            return if (state === BookingState.PENDING) {
                commitAllLegs(body, savedOption, booking)
                booking
            } else {
                log.info("Booking switched to state {}", state)
                booking.state = state
                booking
            }
        }
        log.error("Illegal request. I didn't provide this id")
        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    override fun addNewBookingEvent(body: BookingOperation?, acceptLanguage: String?, id: String): Booking? {
        log.info("{} {}", body!!.operation, id)
        val booking = repository!!.getBooking(id)
        if (booking != null) {
            handleMaaSBooking(body, booking)
            return booking
        }
        return handleClientBooking(body, id, booking)
    }

    private fun handleClientBooking(body: BookingOperation?, id: String, booking: Booking?): Booking? {
        val clientBooking = maasRepository!!.getClientBooking(id)
        when (body!!.operation) {
            OperationEnum.COMMIT -> return commitConditionalConfirmedBooking(booking, clientBooking)
            OperationEnum.CANCEL -> return bookingIsCancelled(clientBooking)
            OperationEnum.DENY -> return denyConditionalConfirmedBooking(booking, clientBooking)
            OperationEnum.EXPIRE -> return conditionalConfirmedBookingIsExpired(booking)
            else -> {}
        }
        return null
    }

    private fun bookingIsCancelled(booking: Booking?): Booking? {
        booking!!.state = BookingState.CANCELLED
        repository!!.saveBooking(booking)
        return booking
    }

    private fun conditionalConfirmedBookingIsExpired(booking: Booking?): Booking? {
        booking!!.state = BookingState.EXPIRED
        repository!!.saveBooking(booking)
        return booking
    }

    private fun denyConditionalConfirmedBooking(maasBooking: Booking?, deniedBooing: Booking?): Booking? {
        if (deniedBooing!!.state === BookingState.CONDITIONAL_CONFIRMED) {
            deniedBooing!!.state = BookingState.RELEASED
        } else {
            throw UnsupportedOperationException()
        }
        maasBooking!!.state = BookingState.RELEASED
        repository!!.saveBooking(maasBooking)
        return maasBooking
    }

    private fun commitConditionalConfirmedBooking(booking: Booking?, clientBooking: Booking?): Booking? {
        if (clientBooking!!.state === BookingState.CONDITIONAL_CONFIRMED) {
            clientBooking!!.state = BookingState.CONFIRMED
        } else {
            throw UnsupportedOperationException()
        }
        booking!!.state = BookingState.CONFIRMED
        repository!!.saveBooking(booking)
        return booking
    }

    private fun handleMaaSBooking(body: BookingOperation?, booking: Booking) {
        if (body!!.operation === OperationEnum.CANCEL) {
            cancelAllClientBookings(booking)
            return
        }
        throw UnsupportedOperationException()
    }

    private fun cancelAllClientBookings(booking: Booking) {
        val clientBookings: List<AbstractMap.SimpleEntry<Booking?, TransportOperator?>?>? = maasRepository!!.getClientBookings(booking)
        for (clientBooking in clientBookings!!) {
            val operation = BookingOperation()
            operation.operation = OperationEnum.CANCEL
            try {
                clientUtil!!.post(
                    clientBooking!!.value, "/bookings/" + clientBooking.key!!.id + "/events/",
                    operation, Booking::class.java
                )
            } catch (e: ApiException) {
                log.error(e.message)
            }
        }
        booking.state = BookingState.CANCELLED
    }

    private fun commitAllLegs(body: BookingRequest?, savedOption: Trip, booking: Booking) {
        var allPending = true
        var cancelled = false
        for (segment in savedOption.segments) {
            val operator = segment.operators.iterator().next()
            val operation = BookingOperation()
            val planningResult = segment!!.getResult(operator)!!.getOptions()!![0]
            operation.operation = OperationEnum.COMMIT
            try {
                val clientBooking = clientUtil!!.post(
                    operator, "/bookings/" + planningResult.id + "/events/",
                    operation, Booking::class.java
                ) ?: throw RuntimeException()
                if (clientBooking.state != BookingState.CONFIRMED) {
                    allPending = false
                }
                if (clientBooking.state == BookingState.CANCELLED) {
                    cancelAll(savedOption.segments)
                    cancelled = true
                    break
                }
                maasRepository!!.addClientBooking(booking, operator, clientBooking)
            } catch (e: ApiException) {
                log.error("Error during committing {}", operator.name)
                log.error(e.message)
            }
        }
        booking.customer = body!!.customer
        if (cancelled) {
            booking.state = BookingState.CANCELLED
        } else if (allPending) {
            booking.state = BookingState.CONFIRMED
        } else {
            booking.state = BookingState.CONDITIONAL_CONFIRMED
        }
    }

    private fun cancelAll(segments: List<Segment?>?) {
        val operation = BookingOperation()
        operation.operation = OperationEnum.CANCEL
        for (segment in segments!!) {
            val operator = segment.getOperators().iterator().next()
            val planningResult = segment!!.getResult(operator)!!.getOptions()!![0]
            try {
                clientUtil!!.post(operator, "/bookings/" + planningResult.id + "/events/", operation, Booking::class.java)
            } catch (e: ApiException) {
                log.error("Operator {} possibly didn't receive CANCEL {}", operator.name, planningResult.id)
            }
        }
    }

    private fun bookAllLegs(body: BookingRequest?, savedOption: Trip): BookingState? {
        var generalState = BookingState.PENDING
        for (segment in savedOption.segments) {
            val operator = segment.operators.iterator().next()
            val option = BookingRequest()
            val result = segment!!.getResult(operator)
            val simpleLeg = result!!.getOptions()!![0]
            val id = simpleLeg.id
            option.id = id
            option.customer = body!!.customer
            addRequiredFields(option, result, simpleLeg)
            try {
                val clientBooking = clientUtil!!.post(operator, "/bookings/", option, Booking::class.java) ?: return BookingState.CANCELLED
                if (clientBooking.state != BookingState.PENDING) {
                    generalState = clientBooking.state
                }
            } catch (e: ApiException) {
                log.error("Error during booking {}", operator.name)
            }
        }
        return generalState
    }

    private fun addRequiredFields(option: BookingRequest, result: Planning?, booking: Booking) {
        for (leg in booking.getLegs()!!) {
            for (condition in leg.getConditions()!!) {
                if (condition is ConditionRequireBookingData) {
                    for (field in condition.getRequiredFields()!!) {
                        addRequiredField(option, field)
                    }
                }
            }
        }
    }

    private fun addRequiredField(option: BookingRequest, field: RequiredFieldsEnum) {
        when (field) {
            RequiredFieldsEnum.BANK_CARDS -> {
                val card = Card()
                card.type = CardType.TypeEnum.BANK
                card.cardNumber = "NL21RABO43892222"
                card.country = "NL"
                option.customer!!.setCards(Arrays.asList(card))
            }

            RequiredFieldsEnum.BIRTHDATE -> {}
            RequiredFieldsEnum.CREDIT_CARDS -> {}
            RequiredFieldsEnum.DISCOUNT_CARDS -> {}
            RequiredFieldsEnum.EMAIL -> {}
            RequiredFieldsEnum.FROM_ADDRESS -> {}
            RequiredFieldsEnum.ID_CARDS -> {}
            RequiredFieldsEnum.LICENSES -> {}
            RequiredFieldsEnum.PERSONAL_ADDRESS -> {}
            RequiredFieldsEnum.PHONE_NUMBERS -> {}
            RequiredFieldsEnum.TO_ADDRESS -> {}
            RequiredFieldsEnum.TRAVEL_CARDS -> {}
            else -> {}
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(MaaSBookingProvider::class.java)
    }
}
