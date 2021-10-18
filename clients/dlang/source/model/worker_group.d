module model.worker_group;

import stream;
import std.conv;
import std.typecons : Nullable;


/// TODO - Document
struct WorkerGroup {
    /// TODO - Document
    int playerIndex;
    /// TODO - Document
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