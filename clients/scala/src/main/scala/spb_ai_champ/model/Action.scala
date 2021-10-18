package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player's actions
 *
 * @param moves List of movement orders
 * @param buildings List of building orders
 * @param chooseSpecialty Choosing specialty
 */
case class Action(moves: Seq[spb_ai_champ.model.MoveAction], buildings: Seq[spb_ai_champ.model.BuildingAction], chooseSpecialty: Option[spb_ai_champ.model.Specialty]) {
    /**
     * Write Action to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, moves.length)
        moves.foreach { value =>
            value.writeTo(stream)
        }
        StreamUtil.writeInt(stream, buildings.length)
        buildings.foreach { value =>
            value.writeTo(stream)
        }
        chooseSpecialty match {
            case None => StreamUtil.writeBoolean(stream, false)
            case Some(value) => {
                StreamUtil.writeBoolean(stream, true)
                value.writeTo(stream)
            }
        }
    }

    /**
     * Get string representation of Action
     */
    override def toString(): String = {
        var stringBuilder = new StringBuilder("Action { ")
        stringBuilder.append("moves: ")
        stringBuilder.append(moves)
        stringBuilder.append(", ")
        stringBuilder.append("buildings: ")
        stringBuilder.append(buildings)
        stringBuilder.append(", ")
        stringBuilder.append("chooseSpecialty: ")
        stringBuilder.append(chooseSpecialty)
        stringBuilder.append(" }")
        stringBuilder.toString()
    }
}

object Action {
    /**
     * Read Action from input stream
     */
    def readFrom(stream: java.io.InputStream): Action = Action(
        (0 until StreamUtil.readInt(stream)).map { _ =>
            spb_ai_champ.model.MoveAction.readFrom(stream)
        },
        (0 until StreamUtil.readInt(stream)).map { _ =>
            spb_ai_champ.model.BuildingAction.readFrom(stream)
        },
        if (StreamUtil.readBoolean(stream)) Some(
            spb_ai_champ.model.Specialty.readFrom(stream)
        ) else None
    )
}