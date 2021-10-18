package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * A building
 */
class Building {
    /**
     * Building's type
     */
    var buildingType: spb_ai_champ.model.BuildingType
    /**
     * Current health
     */
    var health: Int
    /**
     * Amount of work done for current task
     */
    var workDone: Int

    constructor(buildingType: spb_ai_champ.model.BuildingType, health: Int, workDone: Int) {
        this.buildingType = buildingType
        this.health = health
        this.workDone = workDone
    }

    /**
     * Write Building to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, buildingType.tag)
        StreamUtil.writeInt(stream, health)
        StreamUtil.writeInt(stream, workDone)
    }

    /**
     * Get string representation of Building
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("Building { ")
        stringBuilder.append("buildingType: ")
        stringBuilder.append(buildingType)
        stringBuilder.append(", ")
        stringBuilder.append("health: ")
        stringBuilder.append(health)
        stringBuilder.append(", ")
        stringBuilder.append("workDone: ")
        stringBuilder.append(workDone)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read Building from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Building {
            var buildingType: spb_ai_champ.model.BuildingType
            buildingType = spb_ai_champ.model.BuildingType.readFrom(stream)
            var health: Int
            health = StreamUtil.readInt(stream)
            var workDone: Int
            workDone = StreamUtil.readInt(stream)
            return Building(buildingType, health, workDone)
        }
    }
}