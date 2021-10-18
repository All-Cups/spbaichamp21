package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * A planet
 *
 * @param id Unique identifier of the planet
 * @param x X coordinate
 * @param y Y coordinate
 * @param harvestableResource Resource that can be harvested on the planet
 * @param workerGroups List of worker groups
 * @param resources Resources stored on the planet
 * @param building Building on the planet
 */
case class Planet(id: Int, x: Int, y: Int, harvestableResource: Option[spb_ai_champ.model.Resource], workerGroups: Seq[spb_ai_champ.model.WorkerGroup], resources: Map[spb_ai_champ.model.Resource, Int], building: Option[spb_ai_champ.model.Building]) {
    /**
     * Write Planet to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, id)
        StreamUtil.writeInt(stream, x)
        StreamUtil.writeInt(stream, y)
        harvestableResource match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
        StreamUtil.writeInt(stream, workerGroups.length)
        workerGroups.foreach { value =>
            value.writeTo(stream)
        }
        StreamUtil.writeInt(stream, resources.size)
        resources.foreach { case (key, value) =>
            key.writeTo(stream)
            StreamUtil.writeInt(stream, value)
        }
        building match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }

    /**
     * Get string representation of Planet
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("Planet { ")
        stringBuilder.append("id: ")
        stringBuilder.append(id)
        stringBuilder.append(", ")
        stringBuilder.append("x: ")
        stringBuilder.append(x)
        stringBuilder.append(", ")
        stringBuilder.append("y: ")
        stringBuilder.append(y)
        stringBuilder.append(", ")
        stringBuilder.append("harvestableResource: ")
        stringBuilder.append(harvestableResource)
        stringBuilder.append(", ")
        stringBuilder.append("workerGroups: ")
        stringBuilder.append(workerGroups)
        stringBuilder.append(", ")
        stringBuilder.append("resources: ")
        stringBuilder.append(resources)
        stringBuilder.append(", ")
        stringBuilder.append("building: ")
        stringBuilder.append(building)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object Planet {
    /**
     * Read Planet from input stream
     */
    def readFrom(stream: java.io.InputStream): Planet = Planet(
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Resource.readFrom(stream)
        ) else None,
        (0 until StreamUtil.readInt(stream)).map { _ =>
            spb_ai_champ.model.WorkerGroup.readFrom(stream)
        },
        (0 until StreamUtil.readInt(stream)).map { _ => (
            spb_ai_champ.model.Resource.readFrom(stream),
            StreamUtil.readInt(stream)
        )}.toMap,
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Building.readFrom(stream)
        ) else None
    )
}