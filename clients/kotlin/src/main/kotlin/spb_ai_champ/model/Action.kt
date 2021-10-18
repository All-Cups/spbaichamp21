package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player's actions
 */
class Action {
    /**
     * List of movement orders
     */
    var moves: Array<spb_ai_champ.model.MoveAction>
    /**
     * List of building orders
     */
    var buildings: Array<spb_ai_champ.model.BuildingAction>

    constructor(moves: Array<spb_ai_champ.model.MoveAction>, buildings: Array<spb_ai_champ.model.BuildingAction>) {
        this.moves = moves
        this.buildings = buildings
    }

    /**
     * Write Action to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, moves.size)
        for (movesElement in moves) {
            movesElement.writeTo(stream)
        }
        StreamUtil.writeInt(stream, buildings.size)
        for (buildingsElement in buildings) {
            buildingsElement.writeTo(stream)
        }
    }

    /**
     * Get string representation of Action
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("Action { ")
        stringBuilder.append("moves: ")
        stringBuilder.append("[ ")
        var movesIndex = 0
        for (movesElement in moves) {
            if (movesIndex != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(movesElement)
            movesIndex++
        }
        stringBuilder.append(" ]")
        stringBuilder.append(", ")
        stringBuilder.append("buildings: ")
        stringBuilder.append("[ ")
        var buildingsIndex = 0
        for (buildingsElement in buildings) {
            if (buildingsIndex != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(buildingsElement)
            buildingsIndex++
        }
        stringBuilder.append(" ]")
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read Action from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Action {
            var moves: Array<spb_ai_champ.model.MoveAction>
            moves = Array(StreamUtil.readInt(stream), {
                var movesElement: spb_ai_champ.model.MoveAction
                movesElement = spb_ai_champ.model.MoveAction.readFrom(stream)
                movesElement
            })
            var buildings: Array<spb_ai_champ.model.BuildingAction>
            buildings = Array(StreamUtil.readInt(stream), {
                var buildingsElement: spb_ai_champ.model.BuildingAction
                buildingsElement = spb_ai_champ.model.BuildingAction.readFrom(stream)
                buildingsElement
            })
            return Action(moves, buildings)
        }
    }
}