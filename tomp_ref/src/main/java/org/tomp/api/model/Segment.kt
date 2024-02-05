package org.tomp.api.model

import io.swagger.model.Leg
import io.swagger.model.Planning

class Segment : Leg() {
    private val offeredResults: MutableMap<TransportOperator?, Planning?> = HashMap()
    fun addResult(operator: TransportOperator?, result: Planning?) {
        offeredResults[operator] = result
    }

    fun getResult(operator: TransportOperator?): Planning? {
        return offeredResults[operator]
    }

    fun getResult(operatorId: String): Planning? {
        for ((key) in offeredResults) {
            if (key.getId() == operatorId) return offeredResults[key]
        }
        return null
    }

    val operators: Set<TransportOperator?>
        get() = offeredResults.keys
}
