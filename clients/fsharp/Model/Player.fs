#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type Player = {
    /// TODO - Document
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