package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
class DebugData {

    constructor() {
    }

    /**
     * Write DebugData to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
    }

    /**
     * Get string representation of DebugData
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("DebugData { ")
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read DebugData from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): DebugData {
            return DebugData()
        }
    }
}