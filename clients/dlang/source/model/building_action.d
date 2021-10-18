module model.building_action;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_type;

/// Building order
struct BuildingAction {
    /// Id of the planet where the action needs to be performed
    int planet;
    /// Type of a building to build. If absent, current building will be destroyed
    Nullable!(model.BuildingType) buildingType;

    this(int planet, Nullable!(model.BuildingType) buildingType) {
        this.planet = planet;
        this.buildingType = buildingType;
    }

    /// Read BuildingAction from reader
    static BuildingAction readFrom(Stream reader) {
        int planet;
        planet = reader.readInt();
        Nullable!(model.BuildingType) buildingType;
        if (reader.readBool()) {
            buildingType = readBuildingType(reader);
        } else {
            buildingType.nullify();
        }
        return BuildingAction(planet, buildingType);
    }

    /// Write BuildingAction to writer
    void writeTo(Stream writer) const {
        writer.write(planet);
        if (buildingType.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto buildingTypeValue = buildingType.get;
            writer.write(cast(int)(buildingTypeValue));
        }
    }
}