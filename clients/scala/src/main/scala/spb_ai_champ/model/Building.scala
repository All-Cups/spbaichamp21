package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * A building
 *
 * @param buildingType Building's type
 * @param health Current health
 * @param workDone Amount of work done for current task
 * @param lastTickTasksDone Number of tasks finished since last tick
 */
case class Building(buildingType: spb_ai_champ.model.BuildingType, health: Int, workDone: Int, lastTickTasksDone: Int) {
    /**
     * Write Building to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        buildingType.writeTo(stream)
        StreamUtil.writeInt(stream, health)
        StreamUtil.writeInt(stream, workDone)
        StreamUtil.writeInt(stream, lastTickTasksDone)
    }

    /**
     * Get string representation of Building
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("Building { ")
        stringBuilder.append("buildingType: ")
        stringBuilder.append(buildingType)
        stringBuilder.append(", ")
        stringBuilder.append("health: ")
        stringBuilder.append(health)
        stringBuilder.append(", ")
        stringBuilder.append("workDone: ")
        stringBuilder.append(workDone)
        stringBuilder.append(", ")
        stringBuilder.append("lastTickTasksDone: ")
        stringBuilder.append(lastTickTasksDone)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object Building {
    /**
     * Read Building from input stream
     */
    def readFrom(stream: java.io.InputStream): Building = Building(
        spb_ai_champ.model.BuildingType.readFrom(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream)
    )
}