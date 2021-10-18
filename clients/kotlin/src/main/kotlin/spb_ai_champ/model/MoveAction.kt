package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
class MoveAction {
    /**
     * TODO - Document
     */
    var startPlanet: Int
    /**
     * TODO - Document
     */
    var targetPlanet: Int
    /**
     * TODO - Document
     */
    var workerNumber: Int
    /**
     * TODO - Document
     */
    var takeResource: spb_ai_champ.model.Resource?

    constructor(startPlanet: Int, targetPlanet: Int, workerNumber: Int, takeResource: spb_ai_champ.model.Resource?) {
        this.startPlanet = startPlanet
        this.targetPlanet = targetPlanet
        this.workerNumber = workerNumber
        this.takeResource = takeResource
    }

    /**
     * Write MoveAction to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, startPlanet)
        StreamUtil.writeInt(stream, targetPlanet)
        StreamUtil.writeInt(stream, workerNumber)
        val takeResourceValue = takeResource
        if (takeResourceValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, takeResourceValue.tag)
        }
    }

    /**
     * Get string representation of MoveAction
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("MoveAction { ")
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
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read MoveAction from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): MoveAction {
            var startPlanet: Int
            startPlanet = StreamUtil.readInt(stream)
            var targetPlanet: Int
            targetPlanet = StreamUtil.readInt(stream)
            var workerNumber: Int
            workerNumber = StreamUtil.readInt(stream)
            var takeResource: spb_ai_champ.model.Resource?
            if (StreamUtil.readBoolean(stream)) {
                takeResource = spb_ai_champ.model.Resource.readFrom(stream)
            } else {
                takeResource = null
            }
            return MoveAction(startPlanet, targetPlanet, workerNumber, takeResource)
        }
    }
}