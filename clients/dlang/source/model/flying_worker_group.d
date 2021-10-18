module model.flying_worker_group;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.resource;

/// TODO - Document
struct FlyingWorkerGroup {
    /// TODO - Document
    int playerIndex;
    /// TODO - Document
    int number;
    /// TODO - Document
    int departureTick;
    /// TODO - Document
    int departurePlanet;
    /// TODO - Document
    int nextPlanetArrivalTick;
    /// TODO - Document
    int nextPlanet;
    /// TODO - Document
    int targetPlanet;
    /// TODO - Document
    Nullable!(model.Resource) resource;

    this(int playerIndex, int number, int departureTick, int departurePlanet, int nextPlanetArrivalTick, int nextPlanet, int targetPlanet, Nullable!(model.Resource) resource) {
        this.playerIndex = playerIndex;
        this.number = number;
        this.departureTick = departureTick;
        this.departurePlanet = departurePlanet;
        this.nextPlanetArrivalTick = nextPlanetArrivalTick;
        this.nextPlanet = nextPlanet;
        this.targetPlanet = targetPlanet;
        this.resource = resource;
    }

    /// Read FlyingWorkerGroup from reader
    static FlyingWorkerGroup readFrom(Stream reader) {
        int playerIndex;
        playerIndex = reader.readInt();
        int number;
        number = reader.readInt();
        int departureTick;
        departureTick = reader.readInt();
        int departurePlanet;
        departurePlanet = reader.readInt();
        int nextPlanetArrivalTick;
        nextPlanetArrivalTick = reader.readInt();
        int nextPlanet;
        nextPlanet = reader.readInt();
        int targetPlanet;
        targetPlanet = reader.readInt();
        Nullable!(model.Resource) resource;
        if (reader.readBool()) {
            resource = readResource(reader);
        } else {
            resource.nullify();
        }
        return FlyingWorkerGroup(playerIndex, number, departureTick, departurePlanet, nextPlanetArrivalTick, nextPlanet, targetPlanet, resource);
    }

    /// Write FlyingWorkerGroup to writer
    void writeTo(Stream writer) const {
        writer.write(playerIndex);
        writer.write(number);
        writer.write(departureTick);
        writer.write(departurePlanet);
        writer.write(nextPlanetArrivalTick);
        writer.write(nextPlanet);
        writer.write(targetPlanet);
        if (resource.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto resourceValue = resource.get;
            writer.write(cast(int)(resourceValue));
        }
    }
}