package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Movement order
 *
 * @param startPlanet Id of the planet where workers need to be sent from
 * @param targetPlanet Id of the target planet
 * @param workerNumber Number of workers to send
 * @param takeResource Resource workers should carry
 */
case class MoveAction(startPlanet: Int, targetPlanet: Int, workerNumber: Int, takeResource: Option[spb_ai_champ.model.Resource]) {
    /**
     * Write MoveAction to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, startPlanet)
        StreamUtil.writeInt(stream, targetPlanet)
        StreamUtil.writeInt(stream, workerNumber)
        takeResource match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }

    /**
     * Get string representation of MoveAction
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("MoveAction { ")
        stringBuilder.append("startPlanet: ")
        stringBuilder.append(startPlanet)
        stringBuilder.append(", ")
        stringBuilder.append("targetPlanet: ")
        stringBuilder.append(targetPlanet)
        stringBuilder.append(", ")
        stringBuilder.append("workerNumber: ")
        stringBuilder.append(workerNumber)
        stringBuilder.append(", ")
        stringBuilder.append("takeResource: ")
        stringBuilder.append(takeResource)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object MoveAction {
    /**
     * Read MoveAction from input stream
     */
    def readFrom(stream: java.io.InputStream): MoveAction = MoveAction(
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Resource.readFrom(stream)
        ) else None
    )
}