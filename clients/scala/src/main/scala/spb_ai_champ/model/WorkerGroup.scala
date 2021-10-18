package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Group of workers on a planet
 *
 * @param playerIndex Index of player controlling the workers
 * @param number Number of workers in the group
 */
case class WorkerGroup(playerIndex: Int, number: Int) {
    /**
     * Write WorkerGroup to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, playerIndex)
        StreamUtil.writeInt(stream, number)
    }

    /**
     * Get string representation of WorkerGroup
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("WorkerGroup { ")
        stringBuilder.append("playerIndex: ")
        stringBuilder.append(playerIndex)
        stringBuilder.append(", ")
        stringBuilder.append("number: ")
        stringBuilder.append(number)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object WorkerGroup {
    /**
     * Read WorkerGroup from input stream
     */
    def readFrom(stream: java.io.InputStream): WorkerGroup = WorkerGroup(
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream)
    )
}