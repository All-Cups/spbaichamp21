module model.debug_data;

import stream;
import std.conv;
import std.typecons : Nullable;


/// TODO - Document
struct DebugData {

    /// Read DebugData from reader
    static DebugData readFrom(Stream reader) {
        return DebugData();
    }

    /// Write DebugData to writer
    void writeTo(Stream writer) const {
    }
}