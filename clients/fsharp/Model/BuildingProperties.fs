#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Building properties
type BuildingProperties = {
    /// Resources required for building
    BuildResources: Map<Model.Resource, int>;
    /// Max health points of the building
    MaxHealth: int;
    /// Max number of workers in the building
    MaxWorkers: int;
    /// Resources required to start another task
    WorkResources: Map<Model.Resource, int>;
    /// Whether performing a task spawn new workers
    ProduceWorker: bool;
    /// Resource produced when performing a task
    ProduceResource: option<Model.Resource>;
    /// Amount of resources/workers produced when performing one task
    ProduceAmount: int;
    /// Score points given for performing one task
    ProduceScore: int;
    /// Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
    Harvest: bool;
    /// Amount of work needed to finish one task
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