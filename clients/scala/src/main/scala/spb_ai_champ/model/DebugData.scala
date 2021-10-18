package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
case class DebugData() {
    /**
     * Write DebugData to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
    }

    /**
     * Get string representation of DebugData
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("DebugData { ")
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object DebugData {
    /**
     * Read DebugData from input stream
     */
    def readFrom(stream: java.io.InputStream): DebugData = DebugData(
    )
}