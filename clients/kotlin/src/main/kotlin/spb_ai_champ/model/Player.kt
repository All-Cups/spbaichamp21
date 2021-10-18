package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player (game participant)
 */
class Player {
    /**
     * Current score points
     */
    var score: Int

    constructor(score: Int) {
        this.score = score
    }

    /**
     * Write Player to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, score)
    }

    /**
     * Get string representation of Player
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("Player { ")
        stringBuilder.append("score: ")
        stringBuilder.append(score)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read Player from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Player {
            var score: Int
            score = StreamUtil.readInt(stream)
            return Player(score)
        }
    }
}