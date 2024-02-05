package org.tomp.api.payment

import io.swagger.model.ExtraCosts
import io.swagger.model.JournalEntry
import io.swagger.model.JournalState
import org.threeten.bp.OffsetDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull

interface PaymentProvider {
    fun claimExtraCosts(acceptLanguage: String?, api: String?, apiVersion: String?, id: String?, body: ExtraCosts?): JournalEntry
    fun getJournalEntries(
        acceptLanguage: String?, api: String?, apiVersion: String?,
        from: @NotNull @Valid OffsetDateTime?, to: @NotNull @Valid OffsetDateTime?, state: JournalState?, category: String?, id: String?,
        maasId: String?
    ): List<JournalEntry?>?
}
