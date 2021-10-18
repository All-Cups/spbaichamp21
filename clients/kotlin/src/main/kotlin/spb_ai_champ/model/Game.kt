package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
class Game {
    /**
     * TODO - Document
     */
    var myIndex: Int
    /**
     * TODO - Document
     */
    var currentTick: Int
    /**
     * TODO - Document
     */
    var maxTickCount: Int
    /**
     * TODO - Document
     */
    var players: Array<spb_ai_champ.model.Player>
    /**
     * TODO - Document
     */
    var planets: Array<spb_ai_champ.model.Planet>
    /**
     * TODO - Document
     */
    var flyingWorkerGroups: Array<spb_ai_champ.model.FlyingWorkerGroup>
    /**
     * TODO - Document
     */
    var maxFlyingWorkerGroups: Int
    /**
     * TODO - Document
     */
    var maxTravelDistance: Int
    /**
     * TODO - Document
     */
    var maxBuilders: Int
    /**
     * TODO - Document
     */
    var buildingProperties: MutableMap<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties>

    constructor(myIndex: Int, currentTick: Int, maxTickCount: Int, players: Array<spb_ai_champ.model.Player>, planets: Array<spb_ai_champ.model.Planet>, flyingWorkerGroups: Array<spb_ai_champ.model.FlyingWorkerGroup>, maxFlyingWorkerGroups: Int, maxTravelDistance: Int, maxBuilders: Int, buildingProperties: MutableMap<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties>) {
        this.myIndex = myIndex
        this.currentTick = currentTick
        this.maxTickCount = maxTickCount
        this.players = players
        this.planets = planets
        this.flyingWorkerGroups = flyingWorkerGroups
        this.maxFlyingWorkerGroups = maxFlyingWorkerGroups
        this.maxTravelDistance = maxTravelDistance
        this.maxBuilders = maxBuilders
        this.buildingProperties = buildingProperties
    }

    /**
     * Write Game to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, myIndex)
        StreamUtil.writeInt(stream, currentTick)
        StreamUtil.writeInt(stream, maxTickCount)
        StreamUtil.writeInt(stream, players.size)
        for (playersElement in players) {
            playersElement.writeTo(stream)
        }
        StreamUtil.writeInt(stream, planets.size)
        for (planetsElement in planets) {
            planetsElement.writeTo(stream)
        }
        StreamUtil.writeInt(stream, flyingWorkerGroups.size)
        for (flyingWorkerGroupsElement in flyingWorkerGroups) {
            flyingWorkerGroupsElement.writeTo(stream)
        }
        StreamUtil.writeInt(stream, maxFlyingWorkerGroups)
        StreamUtil.writeInt(stream, maxTravelDistance)
        StreamUtil.writeInt(stream, maxBuilders)
        StreamUtil.writeInt(stream, buildingProperties.size)
        for (buildingPropertiesEntry in buildingProperties) {
            val buildingPropertiesKey = buildingPropertiesEntry.key
            StreamUtil.writeInt(stream, buildingPropertiesKey.tag)
            val buildingPropertiesValue = buildingPropertiesEntry.value
            buildingPropertiesValue.writeTo(stream)
        }
    }

    /**
     * Get string representation of Game
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("Game { ")
        stringBuilder.append("myIndex: ")
        stringBuilder.append(myIndex)
        stringBuilder.append(", ")
        stringBuilder.append("currentTick: ")
        stringBuilder.append(currentTick)
        stringBuilder.append(", ")
        stringBuilder.append("maxTickCount: ")
        stringBuilder.append(maxTickCount)
        stringBuilder.append(", ")
        stringBuilder.append("players: ")
        stringBuilder.append("[ ")
        var playersIndex = 0
        for (playersElement in players) {
            if (playersIndex != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(playersElement)
            playersIndex++
        }
        stringBuilder.append(" ]")
        stringBuilder.append(", ")
        stringBuilder.append("planets: ")
        stringBuilder.append("[ ")
        var planetsIndex = 0
        for (planetsElement in planets) {
            if (planetsIndex != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(planetsElement)
            planetsIndex++
        }
        stringBuilder.append(" ]")
        stringBuilder.append(", ")
        stringBuilder.append("flyingWorkerGroups: ")
        stringBuilder.append("[ ")
        var flyingWorkerGroupsIndex = 0
        for (flyingWorkerGroupsElement in flyingWorkerGroups) {
            if (flyingWorkerGroupsIndex != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(flyingWorkerGroupsElement)
            flyingWorkerGroupsIndex++
        }
        stringBuilder.append(" ]")
        stringBuilder.append(", ")
        stringBuilder.append("maxFlyingWorkerGroups: ")
        stringBuilder.append(maxFlyingWorkerGroups)
        stringBuilder.append(", ")
        stringBuilder.append("maxTravelDistance: ")
        stringBuilder.append(maxTravelDistance)
        stringBuilder.append(", ")
        stringBuilder.append("maxBuilders: ")
        stringBuilder.append(maxBuilders)
        stringBuilder.append(", ")
        stringBuilder.append("buildingProperties: ")
        stringBuilder.append(buildingProperties)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read Game from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Game {
            var myIndex: Int
            myIndex = StreamUtil.readInt(stream)
            var currentTick: Int
            currentTick = StreamUtil.readInt(stream)
            var maxTickCount: Int
            maxTickCount = StreamUtil.readInt(stream)
            var players: Array<spb_ai_champ.model.Player>
            players = Array(StreamUtil.readInt(stream), {
                var playersElement: spb_ai_champ.model.Player
                playersElement = spb_ai_champ.model.Player.readFrom(stream)
                playersElement
            })
            var planets: Array<spb_ai_champ.model.Planet>
            planets = Array(StreamUtil.readInt(stream), {
                var planetsElement: spb_ai_champ.model.Planet
                planetsElement = spb_ai_champ.model.Planet.readFrom(stream)
                planetsElement
            })
            var flyingWorkerGroups: Array<spb_ai_champ.model.FlyingWorkerGroup>
            flyingWorkerGroups = Array(StreamUtil.readInt(stream), {
                var flyingWorkerGroupsElement: spb_ai_champ.model.FlyingWorkerGroup
                flyingWorkerGroupsElement = spb_ai_champ.model.FlyingWorkerGroup.readFrom(stream)
                flyingWorkerGroupsElement
            })
            var maxFlyingWorkerGroups: Int
            maxFlyingWorkerGroups = StreamUtil.readInt(stream)
            var maxTravelDistance: Int
            maxTravelDistance = StreamUtil.readInt(stream)
            var maxBuilders: Int
            maxBuilders = StreamUtil.readInt(stream)
            var buildingProperties: MutableMap<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties>
            val buildingPropertiesSize = StreamUtil.readInt(stream)
            buildingProperties = mutableMapOf();
            for (buildingPropertiesIndex in 0 until buildingPropertiesSize) {
                var buildingPropertiesKey: spb_ai_champ.model.BuildingType
                buildingPropertiesKey = spb_ai_champ.model.BuildingType.readFrom(stream)
                var buildingPropertiesValue: spb_ai_champ.model.BuildingProperties
                buildingPropertiesValue = spb_ai_champ.model.BuildingProperties.readFrom(stream)
                buildingProperties.put(buildingPropertiesKey, buildingPropertiesValue)
            }
            return Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, maxBuilders, buildingProperties)
        }
    }
}