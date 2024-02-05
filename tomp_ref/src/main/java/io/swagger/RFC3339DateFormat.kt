package io.swagger

import com.fasterxml.jackson.databind.util.ISO8601DateFormat
import com.fasterxml.jackson.databind.util.ISO8601Utils
import java.text.FieldPosition
import java.util.Date

class RFC3339DateFormat : ISO8601DateFormat() {
    // Same as ISO8601DateFormat but serializing milliseconds.
    override fun format(date: Date, toAppendTo: StringBuffer, fieldPosition: FieldPosition): StringBuffer {
        val value = ISO8601Utils.format(date, true)
        toAppendTo.append(value)
        return toAppendTo
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}