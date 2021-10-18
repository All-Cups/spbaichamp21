module model.action;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_action;
import model.move_action;
import model.specialty;

/// Player's actions
struct Action {
    /// List of movement orders
    model.MoveAction[] moves;
    /// List of building orders
    model.BuildingAction[] buildings;
    /// Choosing specialty
    Nullable!(model.Specialty) chooseSpecialty;

    this(model.MoveAction[] moves, model.BuildingAction[] buildings, Nullable!(model.Specialty) chooseSpecialty) {
        this.moves = moves;
        this.buildings = buildings;
        this.chooseSpecialty = chooseSpecialty;
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
        Nullable!(model.Specialty) chooseSpecialty;
        if (reader.readBool()) {
            chooseSpecialty = readSpecialty(reader);
        } else {
            chooseSpecialty.nullify();
        }
        return Action(moves, buildings, chooseSpecialty);
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
        if (chooseSpecialty.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto chooseSpecialtyValue = chooseSpecialty.get;
            writer.write(cast(int)(chooseSpecialtyValue));
        }
    }
}