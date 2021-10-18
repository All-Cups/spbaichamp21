module model.building;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_type;

/// A building
struct Building {
    /// Building's type
    model.BuildingType buildingType;
    /// Current health
    int health;
    /// Amount of work done for current task
    int workDone;
    /// Number of tasks finished since last tick
    int lastTickTasksDone;

    this(model.BuildingType buildingType, int health, int workDone, int lastTickTasksDone) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
        this.lastTickTasksDone = lastTickTasksDone;
    }

    /// Read Building from reader
    static Building readFrom(Stream reader) {
        model.BuildingType buildingType;
        buildingType = readBuildingType(reader);
        int health;
        health = reader.readInt();
        int workDone;
        workDone = reader.readInt();
        int lastTickTasksDone;
        lastTickTasksDone = reader.readInt();
        return Building(buildingType, health, workDone, lastTickTasksDone);
    }

    /// Write Building to writer
    void writeTo(Stream writer) const {
        writer.write(cast(int)(buildingType));
        writer.write(health);
        writer.write(workDone);
        writer.write(lastTickTasksDone);
    }
}