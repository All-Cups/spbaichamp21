package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 *
 * @param buildResources TODO - Document
 * @param maxHealth TODO - Document
 * @param maxWorkers TODO - Document
 * @param workResources TODO - Document
 * @param produceWorker TODO - Document
 * @param produceResource TODO - Document
 * @param produceAmount TODO - Document
 * @param produceScore TODO - Document
 * @param harvest TODO - Document
 * @param workAmount TODO - Document
 */
case class BuildingProperties(buildResources: Map[spb_ai_champ.model.Resource, Int], maxHealth: Int, maxWorkers: Int, workResources: Map[spb_ai_champ.model.Resource, Int], produceWorker: Boolean, produceResource: Option[spb_ai_champ.model.Resource], produceAmount: Int, produceScore: Int, harvest: Boolean, workAmount: Int) {
    /**
     * Write BuildingProperties to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, buildResources.size)
        buildResources.foreach { case (key, value) =>
            key.writeTo(stream)
            StreamUtil.writeInt(stream, value)
        }
        StreamUtil.writeInt(stream, maxHealth)
        StreamUtil.writeInt(stream, maxWorkers)
        StreamUtil.writeInt(stream, workResources.size)
        workResources.foreach { case (key, value) =>
            key.writeTo(stream)
            StreamUtil.writeInt(stream, value)
        }
        StreamUtil.writeBoolean(stream, produceWorker)
        produceResource match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
        StreamUtil.writeInt(stream, produceAmount)
        StreamUtil.writeInt(stream, produceScore)
        StreamUtil.writeBoolean(stream, harvest)
        StreamUtil.writeInt(stream, workAmount)
    }

    /**
     * Get string representation of BuildingProperties
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("BuildingProperties { ")
        stringBuilder.append("buildResources: ")
        stringBuilder.append(buildResources)
        stringBuilder.append(", ")
        stringBuilder.append("maxHealth: ")
        stringBuilder.append(maxHealth)
        stringBuilder.append(", ")
        stringBuilder.append("maxWorkers: ")
        stringBuilder.append(maxWorkers)
        stringBuilder.append(", ")
        stringBuilder.append("workResources: ")
        stringBuilder.append(workResources)
        stringBuilder.append(", ")
        stringBuilder.append("produceWorker: ")
        stringBuilder.append(produceWorker)
        stringBuilder.append(", ")
        stringBuilder.append("produceResource: ")
        stringBuilder.append(produceResource)
        stringBuilder.append(", ")
        stringBuilder.append("produceAmount: ")
        stringBuilder.append(produceAmount)
        stringBuilder.append(", ")
        stringBuilder.append("produceScore: ")
        stringBuilder.append(produceScore)
        stringBuilder.append(", ")
        stringBuilder.append("harvest: ")
        stringBuilder.append(harvest)
        stringBuilder.append(", ")
        stringBuilder.append("workAmount: ")
        stringBuilder.append(workAmount)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object BuildingProperties {
    /**
     * Read BuildingProperties from input stream
     */
    def readFrom(stream: java.io.InputStream): BuildingProperties = BuildingProperties(
        (0 until StreamUtil.readInt(stream)).map { _ => (
            spb_ai_champ.model.Resource.readFrom(stream),
            StreamUtil.readInt(stream)
        )}.toMap,
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        (0 until StreamUtil.readInt(stream)).map { _ => (
            spb_ai_champ.model.Resource.readFrom(stream),
            StreamUtil.readInt(stream)
        )}.toMap,
        StreamUtil.readBoolean(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Resource.readFrom(stream)
        ) else None,
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readBoolean(stream),
        StreamUtil.readInt(stream)
    )
}