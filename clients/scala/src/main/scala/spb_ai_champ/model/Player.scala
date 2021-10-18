package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player (game participant)
 *
 * @param score Current score points
 */
case class Player(score: Int) {
    /**
     * Write Player to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, score)
    }

    /**
     * Get string representation of Player
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("Player { ")
        stringBuilder.append("score: ")
        stringBuilder.append(score)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object Player {
    /**
     * Read Player from input stream
     */
    def readFrom(stream: java.io.InputStream): Player = Player(
        StreamUtil.readInt(stream)
    )
}