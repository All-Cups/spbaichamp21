package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player (game participant)
 *
 * @param teamIndex Team index
 * @param score Current score points
 * @param specialty Player's specialty
 */
case class Player(teamIndex: Int, score: Int, specialty: Option[spb_ai_champ.model.Specialty]) {
    /**
     * Write Player to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, teamIndex)
        StreamUtil.writeInt(stream, score)
        specialty match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }

    /**
     * Get string representation of Player
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("Player { ")
        stringBuilder.append("teamIndex: ")
        stringBuilder.append(teamIndex)
        stringBuilder.append(", ")
        stringBuilder.append("score: ")
        stringBuilder.append(score)
        stringBuilder.append(", ")
        stringBuilder.append("specialty: ")
        stringBuilder.append(specialty)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object Player {
    /**
     * Read Player from input stream
     */
    def readFrom(stream: java.io.InputStream): Player = Player(
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Specialty.readFrom(stream)
        ) else None
    )
}