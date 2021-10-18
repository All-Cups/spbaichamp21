#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Building order
type BuildingAction = {
    /// Id of the planet where the action needs to be performed
    Planet: int;
    /// Type of a building to build. If absent, current building will be destroyed
    BuildingType: option<Model.BuildingType>;
} with

    /// Write BuildingAction to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Planet
        match this.BuildingType with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        ()

    /// Read BuildingAction from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Planet = reader.ReadInt32()
        BuildingType = match reader.ReadBoolean() with
                           | true -> Some(reader.ReadInt32() |> enum)
                           | false -> None
    }