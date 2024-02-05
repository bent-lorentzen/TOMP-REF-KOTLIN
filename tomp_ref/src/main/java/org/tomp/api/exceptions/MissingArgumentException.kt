package org.tomp.api.exceptions

class MissingArgumentException(requiredArgument: String?) : RuntimeException() {
    var requiredArgument: String? = null

    init {
        this.requiredArgument = requiredArgument
    }

    override val message: String
        get() = "Missing argument: $requiredArgument"

    companion object {
        private const val serialVersionUID = -3443398319953659611L
    }
}
