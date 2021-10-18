package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Group of workers on a planet
 */
class WorkerGroup {
    /**
     * Index of player controlling the workers
     */
    var playerIndex: Int
    /**
     * Number of workers in the group
     */
    var number: Int

    constructor(playerIndex: Int, number: Int) {
        this.playerIndex = playerIndex
        this.number = number
    }

    /**
     * Write WorkerGroup to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, playerIndex)
        StreamUtil.writeInt(stream, number)
    }

    /**
     * Get string representation of WorkerGroup
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("WorkerGroup { ")
        stringBuilder.append("playerIndex: ")
        stringBuilder.append(playerIndex)
        stringBuilder.append(", ")
        stringBuilder.append("number: ")
        stringBuilder.append(number)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read WorkerGroup from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): WorkerGroup {
            var playerIndex: Int
            playerIndex = StreamUtil.readInt(stream)
            var number: Int
            number = StreamUtil.readInt(stream)
            return WorkerGroup(playerIndex, number)
        }
    }
}