package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 *
 * @param playerIndex TODO - Document
 * @param number TODO - Document
 * @param departureTick TODO - Document
 * @param departurePlanet TODO - Document
 * @param nextPlanetArrivalTick TODO - Document
 * @param nextPlanet TODO - Document
 * @param targetPlanet TODO - Document
 * @param resource TODO - Document
 */
case class FlyingWorkerGroup(playerIndex: Int, number: Int, departureTick: Int, departurePlanet: Int, nextPlanetArrivalTick: Int, nextPlanet: Int, targetPlanet: Int, resource: Option[spb_ai_champ.model.Resource]) {
    /**
     * Write FlyingWorkerGroup to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, playerIndex)
        StreamUtil.writeInt(stream, number)
        StreamUtil.writeInt(stream, departureTick)
        StreamUtil.writeInt(stream, departurePlanet)
        StreamUtil.writeInt(stream, nextPlanetArrivalTick)
        StreamUtil.writeInt(stream, nextPlanet)
        StreamUtil.writeInt(stream, targetPlanet)
        resource match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }

    /**
     * Get string representation of FlyingWorkerGroup
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("FlyingWorkerGroup { ")
        stringBuilder.append("playerIndex: ")
        stringBuilder.append(playerIndex)
        stringBuilder.append(", ")
        stringBuilder.append("number: ")
        stringBuilder.append(number)
        stringBuilder.append(", ")
        stringBuilder.append("departureTick: ")
        stringBuilder.append(departureTick)
        stringBuilder.append(", ")
        stringBuilder.append("departurePlanet: ")
        stringBuilder.append(departurePlanet)
        stringBuilder.append(", ")
        stringBuilder.append("nextPlanetArrivalTick: ")
        stringBuilder.append(nextPlanetArrivalTick)
        stringBuilder.append(", ")
        stringBuilder.append("nextPlanet: ")
        stringBuilder.append(nextPlanet)
        stringBuilder.append(", ")
        stringBuilder.append("targetPlanet: ")
        stringBuilder.append(targetPlanet)
        stringBuilder.append(", ")
        stringBuilder.append("resource: ")
        stringBuilder.append(resource)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object FlyingWorkerGroup {
    /**
     * Read FlyingWorkerGroup from input stream
     */
    def readFrom(stream: java.io.InputStream): FlyingWorkerGroup = FlyingWorkerGroup(
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Resource.readFrom(stream)
        ) else None
    )
}