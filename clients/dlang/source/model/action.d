module model.action;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_action;
import model.move_action;

/// Player's actions
struct Action {
    /// List of movement orders
    model.MoveAction[] moves;
    /// List of building orders
    model.BuildingAction[] buildings;

    this(model.MoveAction[] moves, model.BuildingAction[] buildings) {
        this.moves = moves;
        this.buildings = buildings;
    }

    /// Read Action from reader
    static Action readFrom(Stream reader) {
        model.MoveAction[] moves;
        moves = new model.MoveAction[reader.readInt()];
        for (int movesIndex = 0; movesIndex < moves.length; movesIndex++) {
            model.MoveAction movesKey;
            movesKey = model.MoveAction.readFrom(reader);
            moves[movesIndex] = movesKey;
        }
        model.BuildingAction[] buildings;
        buildings = new model.BuildingAction[reader.readInt()];
        for (int buildingsIndex = 0; buildingsIndex < buildings.length; buildingsIndex++) {
            model.BuildingAction buildingsKey;
            buildingsKey = model.BuildingAction.readFrom(reader);
            buildings[buildingsIndex] = buildingsKey;
        }
        return Action(moves, buildings);
    }

    /// Write Action to writer
    void writeTo(Stream writer) const {
        writer.write(cast(int)(moves.length));
        foreach (movesElement; moves) {
            movesElement.writeTo(writer);
        }
        writer.write(cast(int)(buildings.length));
        foreach (buildingsElement; buildings) {
            buildingsElement.writeTo(writer);
        }
    }
}