#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Player (game participant)
type Player = {
    /// Current score points
    Score: int;
} with

    /// Write Player to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Score
        ()

    /// Read Player from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Score = reader.ReadInt32()
    }