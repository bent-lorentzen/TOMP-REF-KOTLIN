package org.tomp.api.utils

object JsonUtil {
    fun getValue(fields: String?, map: Map<String, Any>?): String {
        val street = StringBuilder()
        for (field in fields!!.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            val value = getFieldValue(field, map)
            if (value != null) {
                street.append(value)
            } else if (field.contains(",") || field.contains(" ") || field == "-") {
                street.append(field)
            }
        }
        return street.toString().trim { it <= ' ' }
    }

    private fun getFieldValue(field: String, map: Map<String, Any>?): String? {
        if (field.contains(".")) {
            val mapName = field.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            val submap = map!![mapName] as Map<String, Any>?
            return getFieldValue(field.substring(field.indexOf('.') + 1), submap)
        }
        return if (map!!.containsKey(field)) {
            (map[field] as String?)!!.trim { it <= ' ' }
        } else null
    }
}
