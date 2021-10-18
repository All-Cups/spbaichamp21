package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Current game's state
 */
class Game {
    /**
     * Your player's index
     */
    var myIndex: Int
    /**
     * Current tick number
     */
    var currentTick: Int
    /**
     * Max number of ticks in the game
     */
    var maxTickCount: Int
    /**
     * List of players
     */
    var players: Array<spb_ai_champ.model.Player>
    /**
     * List of planets
     */
    var planets: Array<spb_ai_champ.model.Planet>
    /**
     * List of flying worker groups
     */
    var flyingWorkerGroups: Array<spb_ai_champ.model.FlyingWorkerGroup>
    /**
     * Max number of flying worker groups for one player
     */
    var maxFlyingWorkerGroups: Int
    /**
     * Max distance of direct travel between planets
     */
    var maxTravelDistance: Int
    /**
     * Additional distance of direct travel between planets for player with Logistics specialty
     */
    var logisticsUpgrade: Int
    /**
     * Additional work done by player with Production specialty (in percent)
     */
    var productionUpgrade: Int
    /**
     * Additional strength workers for player with Combat specialty (in percent)
     */
    var combatUpgrade: Int
    /**
     * Max number of workers performing building on one planet
     */
    var maxBuilders: Int
    /**
     * Properties of every building type
     */
    var buildingProperties: MutableMap<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties>
    /**
     * Whether choosing specialties is allowed
     */
    var specialtiesAllowed: Boolean
    /**
     * View distance
     */
    var viewDistance: Int?

    constructor(myIndex: Int, currentTick: Int, maxTickCount: Int, players: Array<spb_ai_champ.model.Player>, planets: Array<spb_ai_champ.model.Planet>, flyingWorkerGroups: Array<spb_ai_champ.model.FlyingWorkerGroup>, maxFlyingWorkerGroups: Int, maxTravelDistance: Int, logisticsUpgrade: Int, productionUpgrade: Int, combatUpgrade: Int, maxBuilders: Int, buildingProperties: MutableMap<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties>, specialtiesAllowed: Boolean, viewDistance: Int?) {
        this.myIndex = myIndex
        this.currentTick = currentTick
        this.maxTickCount = maxTickCount
        this.players = players
        this.planets = planets
        this.flyingWorkerGroups = flyingWorkerGroups
        this.maxFlyingWorkerGroups = maxFlyingWorkerGroups
        this.maxTravelDistance = maxTravelDistance
        this.logisticsUpgrade = logisticsUpgrade
        this.productionUpgrade = productionUpgrade
        this.combatUpgrade = combatUpgrade
        this.maxBuilders = maxBuilders
        this.buildingProperties = buildingProperties
        this.specialtiesAllowed = specialtiesAllowed
        this.viewDistance = viewDistance
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
        StreamUtil.writeInt(stream, logisticsUpgrade)
        StreamUtil.writeInt(stream, productionUpgrade)
        StreamUtil.writeInt(stream, combatUpgrade)
        StreamUtil.writeInt(stream, maxBuilders)
        StreamUtil.writeInt(stream, buildingProperties.size)
        for (buildingPropertiesEntry in buildingProperties) {
            val buildingPropertiesKey = buildingPropertiesEntry.key
            StreamUtil.writeInt(stream, buildingPropertiesKey.tag)
            val buildingPropertiesValue = buildingPropertiesEntry.value
            buildingPropertiesValue.writeTo(stream)
        }
        StreamUtil.writeBoolean(stream, specialtiesAllowed)
        val viewDistanceValue = viewDistance
        if (viewDistanceValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, viewDistanceValue)
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
        stringBuilder.append("logisticsUpgrade: ")
        stringBuilder.append(logisticsUpgrade)
        stringBuilder.append(", ")
        stringBuilder.append("productionUpgrade: ")
        stringBuilder.append(productionUpgrade)
        stringBuilder.append(", ")
        stringBuilder.append("combatUpgrade: ")
        stringBuilder.append(combatUpgrade)
        stringBuilder.append(", ")
        stringBuilder.append("maxBuilders: ")
        stringBuilder.append(maxBuilders)
        stringBuilder.append(", ")
        stringBuilder.append("buildingProperties: ")
        stringBuilder.append(buildingProperties)
        stringBuilder.append(", ")
        stringBuilder.append("specialtiesAllowed: ")
        stringBuilder.append(specialtiesAllowed)
        stringBuilder.append(", ")
        stringBuilder.append("viewDistance: ")
        stringBuilder.append(viewDistance)
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
            var logisticsUpgrade: Int
            logisticsUpgrade = StreamUtil.readInt(stream)
            var productionUpgrade: Int
            productionUpgrade = StreamUtil.readInt(stream)
            var combatUpgrade: Int
            combatUpgrade = StreamUtil.readInt(stream)
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
            var specialtiesAllowed: Boolean
            specialtiesAllowed = StreamUtil.readBoolean(stream)
            var viewDistance: Int?
            if (StreamUtil.readBoolean(stream)) {
                viewDistance = StreamUtil.readInt(stream)
            } else {
                viewDistance = null
            }
            return Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, logisticsUpgrade, productionUpgrade, combatUpgrade, maxBuilders, buildingProperties, specialtiesAllowed, viewDistance)
        }
    }
}