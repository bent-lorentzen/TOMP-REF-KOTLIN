package org.tomp.api.booking

import io.swagger.model.Booking
import io.swagger.model.BookingRequest
import io.swagger.model.Fare
import io.swagger.model.FarePart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.model.LookupService
import org.tomp.api.repository.DefaultRepository
import org.tomp.api.tripexecution.TaxiTripExecutionProvider
import org.tomp.api.utils.ClientUtil
import org.tomp.api.utils.FareUtil
import org.tomp.api.utils.MailUtil
import java.util.Optional
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.booking"], havingValue = "taxi", matchIfMissing = false)
class TaxiBookingProvider(
    repository: DefaultRepository, mailService: Optional<MailUtil?>,
    configuration: ExternalConfiguration, clientUtil: ClientUtil, lookupService: LookupService
) : SharedCarBookingProvider(repository, mailService, configuration, clientUtil, lookupService) {
    @Autowired
    var tripExecution: TaxiTripExecutionProvider? = null

    @Autowired
    override var fareUtil: FareUtil? = null
    override fun addNewBooking(body: @Valid BookingRequest?, acceptLanguage: String?): Booking? {
        if (body!!.from == null || body.to == null || body.from!!.physicalAddress == null || body.to!!.physicalAddress == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "From and to addresses are required")
        }
        val booking = super.addNewBooking(body, acceptLanguage)
        val fare = fareUtil!!.calculateFare(booking!!.getLegs()!![0])
        val pricing = Fare()
        val partsItem = FarePart()
        partsItem.amount = fare.toFloat()
        partsItem.type = FarePart.TypeEnum.FIXED
        partsItem.currencyCode = booking.getLegs()!![0].pricing!!.getParts()!![0].currencyCode
        pricing.addPartsItem(partsItem)
        pricing.isEstimated = false
        booking.pricing = pricing
        return booking
    }

    override fun saveResult(id: String?, committed: Boolean, remark: String?) {
        super.saveResult(id, committed, remark)
        if (committed) {
            startTripExecution(id)
        }
    }

    private fun startTripExecution(tripId: String?) {
        tripExecution!!.startExecution(tripId)
    }
}
