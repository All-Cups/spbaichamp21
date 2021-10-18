#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Group of workers on a planet
type WorkerGroup = {
    /// Index of player controlling the workers
    PlayerIndex: int;
    /// Number of workers in the group
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