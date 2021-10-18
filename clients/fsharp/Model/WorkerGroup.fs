#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type WorkerGroup = {
    /// TODO - Document
    PlayerIndex: int;
    /// TODO - Document
    Number: int;
} with

    /// Write WorkerGroup to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.PlayerIndex
        writer.Write this.Number
        ()

    /// Read WorkerGroup from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        PlayerIndex = reader.ReadInt32()
        Number = reader.ReadInt32()
    }