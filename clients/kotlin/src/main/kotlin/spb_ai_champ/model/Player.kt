package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player (game participant)
 */
class Player {
    /**
     * Team index
     */
    var teamIndex: Int
    /**
     * Current score points
     */
    var score: Int
    /**
     * Player's specialty
     */
    var specialty: spb_ai_champ.model.Specialty?

    constructor(teamIndex: Int, score: Int, specialty: spb_ai_champ.model.Specialty?) {
        this.teamIndex = teamIndex
        this.score = score
        this.specialty = specialty
    }

    /**
     * Write Player to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, teamIndex)
        StreamUtil.writeInt(stream, score)
        val specialtyValue = specialty
        if (specialtyValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, specialtyValue.tag)
        }
    }

    /**
     * Get string representation of Player
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("Player { ")
        stringBuilder.append("teamIndex: ")
        stringBuilder.append(teamIndex)
        stringBuilder.append(", ")
        stringBuilder.append("score: ")
        stringBuilder.append(score)
        stringBuilder.append(", ")
        stringBuilder.append("specialty: ")
        stringBuilder.append(specialty)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read Player from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Player {
            var teamIndex: Int
            teamIndex = StreamUtil.readInt(stream)
            var score: Int
            score = StreamUtil.readInt(stream)
            var specialty: spb_ai_champ.model.Specialty?
            if (StreamUtil.readBoolean(stream)) {
                specialty = spb_ai_champ.model.Specialty.readFrom(stream)
            } else {
                specialty = null
            }
            return Player(teamIndex, score, specialty)
        }
    }
}