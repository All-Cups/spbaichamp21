module model.move_action;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.resource;

/// TODO - Document
struct MoveAction {
    /// TODO - Document
    int startPlanet;
    /// TODO - Document
    int targetPlanet;
    /// TODO - Document
    int workerNumber;
    /// TODO - Document
    Nullable!(model.Resource) takeResource;

    this(int startPlanet, int targetPlanet, int workerNumber, Nullable!(model.Resource) takeResource) {
        this.startPlanet = startPlanet;
        this.targetPlanet = targetPlanet;
        this.workerNumber = workerNumber;
        this.takeResource = takeResource;
    }

    /// Read MoveAction from reader
    static MoveAction readFrom(Stream reader) {
        int startPlanet;
        startPlanet = reader.readInt();
        int targetPlanet;
        targetPlanet = reader.readInt();
        int workerNumber;
        workerNumber = reader.readInt();
        Nullable!(model.Resource) takeResource;
        if (reader.readBool()) {
            takeResource = readResource(reader);
        } else {
            takeResource.nullify();
        }
        return MoveAction(startPlanet, targetPlanet, workerNumber, takeResource);
    }

    /// Write MoveAction to writer
    void writeTo(Stream writer) const {
        writer.write(startPlanet);
        writer.write(targetPlanet);
        writer.write(workerNumber);
        if (takeResource.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto takeResourceValue = takeResource.get;
            writer.write(cast(int)(takeResourceValue));
        }
    }
}