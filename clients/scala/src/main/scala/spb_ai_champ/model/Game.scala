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
 * @param maxBuilders Max number of workers performing building on one planet
 * @param buildingProperties Properties of every building type
 */
case class Game(myIndex: Int, currentTick: Int, maxTickCount: Int, players: Seq[spb_ai_champ.model.Player], planets: Seq[spb_ai_champ.model.Planet], flyingWorkerGroups: Seq[spb_ai_champ.model.FlyingWorkerGroup], maxFlyingWorkerGroups: Int, maxTravelDistance: Int, maxBuilders: Int, buildingProperties: Map[spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties]) {
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
        StreamUtil.writeInt(stream, maxBuilders)
        StreamUtil.writeInt(stream, buildingProperties.size)
        buildingProperties.foreach { case (key, value) =>
            key.writeTo(stream)
            value.writeTo(stream)
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
        stringBuilder.append("maxBuilders: ")
        stringBuilder.append(maxBuilders)
        stringBuilder.append(", ")
        stringBuilder.append("buildingProperties: ")
        stringBuilder.append(buildingProperties)
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
        (0 until StreamUtil.readInt(stream)).map { _ => (
            spb_ai_champ.model.BuildingType.readFrom(stream),
            spb_ai_champ.model.BuildingProperties.readFrom(stream)
        )}.toMap
    )
}