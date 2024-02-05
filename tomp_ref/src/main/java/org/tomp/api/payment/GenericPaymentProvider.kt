package org.tomp.api.payment

import io.swagger.model.ExtraCosts
import io.swagger.model.JournalEntry
import io.swagger.model.JournalState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.tomp.api.repository.DefaultRepository
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Component
@ConditionalOnProperty(value = ["tomp.providers.payment"], havingValue = "generic", matchIfMissing = true)
class GenericPaymentProvider : PaymentProvider {
    @Autowired
    var repository: DefaultRepository? = null
    override fun claimExtraCosts(
        acceptLanguage: String?, api: String?, apiVersion: String?, id: String?,
        body: ExtraCosts?
    ): JournalEntry {
        val extraCosts = JournalEntry()
        extraCosts.amount = body!!.amount
        extraCosts.amountExVat = body.amountExVat
        extraCosts.comment = body.description
        extraCosts.currencyCode = body.currencyCode
        extraCosts.details = body
        // extraCosts.setExpirationDate(body.get);
        extraCosts.journalId = id
        extraCosts.state = JournalState.TO_INVOICE
        extraCosts.vatCountryCode = body.vatCountryCode
        extraCosts.vatRate = body.vatRate
        return extraCosts
    }

    override fun getJournalEntries(
        acceptLanguage: String?, api: String?, apiVersion: String?,
        from: @NotNull @Valid OffsetDateTime?, to: @NotNull @Valid OffsetDateTime?, state: JournalState?, category: String?,
        id: String?, maasId: String?
    ): List<JournalEntry?>? {
        return repository!!.getJournalEntries(acceptLanguage, from, to, state, category, id, maasId)
    }
}
