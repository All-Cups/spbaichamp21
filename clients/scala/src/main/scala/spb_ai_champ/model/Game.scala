package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Current game's state
 *
 * @param myIndex Your player's index
 * @param currentTick Current tick number
 * @param maxTickCount Max number of ticks in the game
 * @param players List of players
 * @param planets List of planets
 * @param flyingWorkerGroups List of flying worker groups
 * @param maxFlyingWorkerGroups Max number of flying worker groups for one player
 * @param maxTravelDistance Max distance of direct travel between planets
 * @param logisticsUpgrade Additional distance of direct travel between planets for player with Logistics specialty
 * @param productionUpgrade Additional work done by player with Production specialty (in percent)
 * @param combatUpgrade Additional strength workers for player with Combat specialty (in percent)
 * @param maxBuilders Max number of workers performing building on one planet
 * @param buildingProperties Properties of every building type
 * @param specialtiesAllowed Whether choosing specialties is allowed
 * @param viewDistance View distance
 */
case class Game(myIndex: Int, currentTick: Int, maxTickCount: Int, players: Seq[spb_ai_champ.model.Player], planets: Seq[spb_ai_champ.model.Planet], flyingWorkerGroups: Seq[spb_ai_champ.model.FlyingWorkerGroup], maxFlyingWorkerGroups: Int, maxTravelDistance: Int, logisticsUpgrade: Int, productionUpgrade: Int, combatUpgrade: Int, maxBuilders: Int, buildingProperties: Map[spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties], specialtiesAllowed: Boolean, viewDistance: Option[Int]) {
    /**
     * Write Game to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, myIndex)
        StreamUtil.writeInt(stream, currentTick)
        StreamUtil.writeInt(stream, maxTickCount)
        StreamUtil.writeInt(stream, players.length)
        players.foreach { value =>
            value.writeTo(stream)
        }
        StreamUtil.writeInt(stream, planets.length)
        planets.foreach { value =>
            value.writeTo(stream)
        }
        StreamUtil.writeInt(stream, flyingWorkerGroups.length)
        flyingWorkerGroups.foreach { value =>
            value.writeTo(stream)
        }
        StreamUtil.writeInt(stream, maxFlyingWorkerGroups)
        StreamUtil.writeInt(stream, maxTravelDistance)
        StreamUtil.writeInt(stream, logisticsUpgrade)
        StreamUtil.writeInt(stream, productionUpgrade)
        StreamUtil.writeInt(stream, combatUpgrade)
        StreamUtil.writeInt(stream, maxBuilders)
        StreamUtil.writeInt(stream, buildingProperties.size)
        buildingProperties.foreach { case (key, value) =>
            key.writeTo(stream)
            value.writeTo(stream)
        }
        StreamUtil.writeBoolean(stream, specialtiesAllowed)
        viewDistance match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                StreamUtil.writeInt(stream, value)
            }
        }
    }

    /**
     * Get string representation of Game
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("Game { ")
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
        stringBuilder.append(players)
        stringBuilder.append(", ")
        stringBuilder.append("planets: ")
        stringBuilder.append(planets)
        stringBuilder.append(", ")
        stringBuilder.append("flyingWorkerGroups: ")
        stringBuilder.append(flyingWorkerGroups)
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
        stringBuilder.toString()
    }
}

object Game {
    /**
     * Read Game from input stream
     */
    def readFrom(stream: java.io.InputStream): Game = Game(
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        (0 until StreamUtil.readInt(stream)).map { _ =>
            spb_ai_champ.model.Player.readFrom(stream)
        },
        (0 until StreamUtil.readInt(stream)).map { _ =>
            spb_ai_champ.model.Planet.readFrom(stream)
        },
        (0 until StreamUtil.readInt(stream)).map { _ =>
            spb_ai_champ.model.FlyingWorkerGroup.readFrom(stream)
        },
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        StreamUtil.readInt(stream),
        (0 until StreamUtil.readInt(stream)).map { _ => (
            spb_ai_champ.model.BuildingType.readFrom(stream),
            spb_ai_champ.model.BuildingProperties.readFrom(stream)
        )}.toMap,
        StreamUtil.readBoolean(stream),
        if (StreamUtil.readBoolean(stream)) Some(
            StreamUtil.readInt(stream)
        ) else None
    )
}