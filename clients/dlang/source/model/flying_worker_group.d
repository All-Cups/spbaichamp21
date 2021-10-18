module model.flying_worker_group;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.resource;

/// A flying worker group
struct FlyingWorkerGroup {
    /// Index of player controlling workers
    int playerIndex;
    /// Number of workers in the group
    int number;
    /// Tick when workers left previous planet on their path
    int departureTick;
    /// Id of the previous planet on the path
    int departurePlanet;
    /// Tick when workers will arrive to the next planet in their path
    int nextPlanetArrivalTick;
    /// Id of the next planet in the path
    int nextPlanet;
    /// Id of the target planet
    int targetPlanet;
    /// Resource that workers are carrying
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