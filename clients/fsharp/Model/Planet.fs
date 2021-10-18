#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type Planet = {
    /// TODO - Document
    Id: int;
    /// TODO - Document
    X: int;
    /// TODO - Document
    Y: int;
    /// TODO - Document
    HarvestableResource: option<Model.Resource>;
    /// TODO - Document
    WorkerGroups: Model.WorkerGroup[];
    /// TODO - Document
    Resources: Map<Model.Resource, int>;
    /// TODO - Document
    Building: option<Model.Building>;
} with

    /// Write Planet to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Id
        writer.Write this.X
        writer.Write this.Y
        match this.HarvestableResource with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        writer.Write this.WorkerGroups.Length
        this.WorkerGroups |> Array.iter (fun value ->
            value.writeTo writer )
        writer.Write this.Resources.Count
        this.Resources |> Map.iter (fun key value ->
            writer.Write (int key)
            writer.Write value )
        match this.Building with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        ()

    /// Read Planet from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Id = reader.ReadInt32()
        X = reader.ReadInt32()
        Y = reader.ReadInt32()
        HarvestableResource = match reader.ReadBoolean() with
                                  | true -> Some(reader.ReadInt32() |> enum)
                                  | false -> None
        WorkerGroups = [|for _ in 1 .. reader.ReadInt32() do
                           yield Model.WorkerGroup.readFrom reader; |]
        Resources = [for _ in 1 .. reader.ReadInt32() do
                        let key = reader.ReadInt32() |> enum
                        let value = reader.ReadInt32()
                        yield (key, value) ] |> Map.ofList
        Building = match reader.ReadBoolean() with
                       | true -> Some(Model.Building.readFrom reader;)
                       | false -> None
    }