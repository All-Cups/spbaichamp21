package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 *
 * @param planet TODO - Document
 * @param buildingType TODO - Document
 */
case class BuildingAction(planet: Int, buildingType: Option[spb_ai_champ.model.BuildingType]) {
    /**
     * Write BuildingAction to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, planet)
        buildingType match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }

    /**
     * Get string representation of BuildingAction
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("BuildingAction { ")
        stringBuilder.append("planet: ")
        stringBuilder.append(planet)
        stringBuilder.append(", ")
        stringBuilder.append("buildingType: ")
        stringBuilder.append(buildingType)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object BuildingAction {
    /**
     * Read BuildingAction from input stream
     */
    def readFrom(stream: java.io.InputStream): BuildingAction = BuildingAction(
        StreamUtil.readInt(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.BuildingType.readFrom(stream)
        ) else None
    )
}