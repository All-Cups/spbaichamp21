package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Player's actions
 */
public class Action {
    /**
     * List of movement orders
     */
    private spb_ai_champ.model.MoveAction[] moves;

    /**
     * List of movement orders
     */
    public spb_ai_champ.model.MoveAction[] getMoves() {
        return moves;
    }

    /**
     * List of movement orders
     */
    public void setMoves(spb_ai_champ.model.MoveAction[] value) {
        this.moves = value;
    }
    /**
     * List of building orders
     */
    private spb_ai_champ.model.BuildingAction[] buildings;

    /**
     * List of building orders
     */
    public spb_ai_champ.model.BuildingAction[] getBuildings() {
        return buildings;
    }

    /**
     * List of building orders
     */
    public void setBuildings(spb_ai_champ.model.BuildingAction[] value) {
        this.buildings = value;
    }

    public Action(spb_ai_champ.model.MoveAction[] moves, spb_ai_champ.model.BuildingAction[] buildings) {
        this.moves = moves;
        this.buildings = buildings;
    }

    /**
     * Read Action from input stream
     */
    public static Action readFrom(java.io.InputStream stream) throws java.io.IOException {
        spb_ai_champ.model.MoveAction[] moves;
        moves = new spb_ai_champ.model.MoveAction[StreamUtil.readInt(stream)];
        for (int movesIndex = 0; movesIndex < moves.length; movesIndex++) {
            spb_ai_champ.model.MoveAction movesElement;
            movesElement = spb_ai_champ.model.MoveAction.readFrom(stream);
            moves[movesIndex] = movesElement;
        }
        spb_ai_champ.model.BuildingAction[] buildings;
        buildings = new spb_ai_champ.model.BuildingAction[StreamUtil.readInt(stream)];
        for (int buildingsIndex = 0; buildingsIndex < buildings.length; buildingsIndex++) {
            spb_ai_champ.model.BuildingAction buildingsElement;
            buildingsElement = spb_ai_champ.model.BuildingAction.readFrom(stream);
            buildings[buildingsIndex] = buildingsElement;
        }
        return new Action(moves, buildings);
    }

    /**
     * Write Action to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, moves.length);
        for (spb_ai_champ.model.MoveAction movesElement : moves) {
            movesElement.writeTo(stream);
        }
        StreamUtil.writeInt(stream, buildings.length);
        for (spb_ai_champ.model.BuildingAction buildingsElement : buildings) {
            buildingsElement.writeTo(stream);
        }
    }

    /**
     * Get string representation of Action
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Action { ");
        stringBuilder.append("moves: ");
        stringBuilder.append("[ ");
        for (int movesIndex = 0; movesIndex < moves.length; movesIndex++) {
            if (movesIndex != 0) {
                stringBuilder.append(", ");
            }
            spb_ai_champ.model.MoveAction movesElement = moves[movesIndex];
            stringBuilder.append(String.valueOf(movesElement));
        }
        stringBuilder.append(" ]");
        stringBuilder.append(", ");
        stringBuilder.append("buildings: ");
        stringBuilder.append("[ ");
        for (int buildingsIndex = 0; buildingsIndex < buildings.length; buildingsIndex++) {
            if (buildingsIndex != 0) {
                stringBuilder.append(", ");
            }
            spb_ai_champ.model.BuildingAction buildingsElement = buildings[buildingsIndex];
            stringBuilder.append(String.valueOf(buildingsElement));
        }
        stringBuilder.append(" ]");
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}