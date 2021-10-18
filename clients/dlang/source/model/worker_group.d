module model.worker_group;

import stream;
import std.conv;
import std.typecons : Nullable;


/// Group of workers on a planet
struct WorkerGroup {
    /// Index of player controlling the workers
    int playerIndex;
    /// Number of workers in the group
    int number;

    this(int playerIndex, int number) {
        this.playerIndex = playerIndex;
        this.number = number;
    }

    /// Read WorkerGroup from reader
    static WorkerGroup readFrom(Stream reader) {
        int playerIndex;
        playerIndex = reader.readInt();
        int number;
        number = reader.readInt();
        return WorkerGroup(playerIndex, number);
    }

    /// Write WorkerGroup to writer
    void writeTo(Stream writer) const {
        writer.write(playerIndex);
        writer.write(number);
    }
}