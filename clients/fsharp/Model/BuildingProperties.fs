#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type BuildingProperties = {
    /// TODO - Document
    BuildResources: Map<Model.Resource, int>;
    /// TODO - Document
    MaxHealth: int;
    /// TODO - Document
    MaxWorkers: int;
    /// TODO - Document
    WorkResources: Map<Model.Resource, int>;
    /// TODO - Document
    ProduceWorker: bool;
    /// TODO - Document
    ProduceResource: option<Model.Resource>;
    /// TODO - Document
    ProduceAmount: int;
    /// TODO - Document
    ProduceScore: int;
    /// TODO - Document
    Harvest: bool;
    /// TODO - Document
    WorkAmount: int;
} with

    /// Write BuildingProperties to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.BuildResources.Count
        this.BuildResources |> Map.iter (fun key value ->
            writer.Write (int key)
            writer.Write value )
        writer.Write this.MaxHealth
        writer.Write this.MaxWorkers
        writer.Write this.WorkResources.Count
        this.WorkResources |> Map.iter (fun key value ->
            writer.Write (int key)
            writer.Write value )
        writer.Write this.ProduceWorker
        match this.ProduceResource with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        writer.Write this.ProduceAmount
        writer.Write this.ProduceScore
        writer.Write this.Harvest
        writer.Write this.WorkAmount
        ()

    /// Read BuildingProperties from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        BuildResources = [for _ in 1 .. reader.ReadInt32() do
                             let key = reader.ReadInt32() |> enum
                             let value = reader.ReadInt32()
                             yield (key, value) ] |> Map.ofList
        MaxHealth = reader.ReadInt32()
        MaxWorkers = reader.ReadInt32()
        WorkResources = [for _ in 1 .. reader.ReadInt32() do
                            let key = reader.ReadInt32() |> enum
                            let value = reader.ReadInt32()
                            yield (key, value) ] |> Map.ofList
        ProduceWorker = reader.ReadBoolean()
        ProduceResource = match reader.ReadBoolean() with
                              | true -> Some(reader.ReadInt32() |> enum)
                              | false -> None
        ProduceAmount = reader.ReadInt32()
        ProduceScore = reader.ReadInt32()
        Harvest = reader.ReadBoolean()
        WorkAmount = reader.ReadInt32()
    }