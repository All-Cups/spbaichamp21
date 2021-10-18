#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type MoveAction = {
    /// TODO - Document
    StartPlanet: int;
    /// TODO - Document
    TargetPlanet: int;
    /// TODO - Document
    WorkerNumber: int;
    /// TODO - Document
    TakeResource: option<Model.Resource>;
} with

    /// Write MoveAction to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.StartPlanet
        writer.Write this.TargetPlanet
        writer.Write this.WorkerNumber
        match this.TakeResource with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        ()

    /// Read MoveAction from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        StartPlanet = reader.ReadInt32()
        TargetPlanet = reader.ReadInt32()
        WorkerNumber = reader.ReadInt32()
        TakeResource = match reader.ReadBoolean() with
                           | true -> Some(reader.ReadInt32() |> enum)
                           | false -> None
    }