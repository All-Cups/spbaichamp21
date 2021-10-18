module model.building;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_type;

/// TODO - Document
struct Building {
    /// TODO - Document
    model.BuildingType buildingType;
    /// TODO - Document
    int health;
    /// TODO - Document
    int workDone;

    this(model.BuildingType buildingType, int health, int workDone) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
    }

    /// Read Building from reader
    static Building readFrom(Stream reader) {
        model.BuildingType buildingType;
        buildingType = readBuildingType(reader);
        int health;
        health = reader.readInt();
        int workDone;
        workDone = reader.readInt();
        return Building(buildingType, health, workDone);
    }

    /// Write Building to writer
    void writeTo(Stream writer) const {
        writer.write(cast(int)(buildingType));
        writer.write(health);
        writer.write(workDone);
    }
}