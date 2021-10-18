#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Player (game participant)
type Player = {
    /// Team index
    TeamIndex: int;
    /// Current score points
    Score: int;
    /// Player's specialty
    Specialty: option<Model.Specialty>;
} with

    /// Write Player to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.TeamIndex
        writer.Write this.Score
        match this.Specialty with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        ()

    /// Read Player from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        TeamIndex = reader.ReadInt32()
        Score = reader.ReadInt32()
        Specialty = match reader.ReadBoolean() with
                        | true -> Some(reader.ReadInt32() |> enum)
                        | false -> None
    }