package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * A flying worker group
 */
class FlyingWorkerGroup {
    /**
     * Index of player controlling workers
     */
    var playerIndex: Int
    /**
     * Number of workers in the group
     */
    var number: Int
    /**
     * Tick when workers left previous planet on their path
     */
    var departureTick: Int
    /**
     * Id of the previous planet on the path
     */
    var departurePlanet: Int
    /**
     * Tick when workers will arrive to the next planet in their path
     */
    var nextPlanetArrivalTick: Int
    /**
     * Id of the next planet in the path
     */
    var nextPlanet: Int
    /**
     * Id of the target planet
     */
    var targetPlanet: Int
    /**
     * Resource that workers are carrying
     */
    var resource: spb_ai_champ.model.Resource?

    constructor(playerIndex: Int, number: Int, departureTick: Int, departurePlanet: Int, nextPlanetArrivalTick: Int, nextPlanet: Int, targetPlanet: Int, resource: spb_ai_champ.model.Resource?) {
        this.playerIndex = playerIndex
        this.number = number
        this.departureTick = departureTick
        this.departurePlanet = departurePlanet
        this.nextPlanetArrivalTick = nextPlanetArrivalTick
        this.nextPlanet = nextPlanet
        this.targetPlanet = targetPlanet
        this.resource = resource
    }

    /**
     * Write FlyingWorkerGroup to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, playerIndex)
        StreamUtil.writeInt(stream, number)
        StreamUtil.writeInt(stream, departureTick)
        StreamUtil.writeInt(stream, departurePlanet)
        StreamUtil.writeInt(stream, nextPlanetArrivalTick)
        StreamUtil.writeInt(stream, nextPlanet)
        StreamUtil.writeInt(stream, targetPlanet)
        val resourceValue = resource
        if (resourceValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, resourceValue.tag)
        }
    }

    /**
     * Get string representation of FlyingWorkerGroup
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("FlyingWorkerGroup { ")
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
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read FlyingWorkerGroup from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): FlyingWorkerGroup {
            var playerIndex: Int
            playerIndex = StreamUtil.readInt(stream)
            var number: Int
            number = StreamUtil.readInt(stream)
            var departureTick: Int
            departureTick = StreamUtil.readInt(stream)
            var departurePlanet: Int
            departurePlanet = StreamUtil.readInt(stream)
            var nextPlanetArrivalTick: Int
            nextPlanetArrivalTick = StreamUtil.readInt(stream)
            var nextPlanet: Int
            nextPlanet = StreamUtil.readInt(stream)
            var targetPlanet: Int
            targetPlanet = StreamUtil.readInt(stream)
            var resource: spb_ai_champ.model.Resource?
            if (StreamUtil.readBoolean(stream)) {
                resource = spb_ai_champ.model.Resource.readFrom(stream)
            } else {
                resource = null
            }
            return FlyingWorkerGroup(playerIndex, number, departureTick, departurePlanet, nextPlanetArrivalTick, nextPlanet, targetPlanet, resource)
        }
    }
}