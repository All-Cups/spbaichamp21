#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type DebugData = struct end with

    /// Write DebugData to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        ()

    /// Read DebugData from reader
    static member readFrom(reader: System.IO.BinaryReader) = new DebugData()