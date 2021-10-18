#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type Game = {
    /// TODO - Document
    MyIndex: int;
    /// TODO - Document
    CurrentTick: int;
    /// TODO - Document
    MaxTickCount: int;
    /// TODO - Document
    Players: Model.Player[];
    /// TODO - Document
    Planets: Model.Planet[];
    /// TODO - Document
    FlyingWorkerGroups: Model.FlyingWorkerGroup[];
    /// TODO - Document
    MaxFlyingWorkerGroups: int;
    /// TODO - Document
    MaxTravelDistance: int;
    /// TODO - Document
    MaxBuilders: int;
    /// TODO - Document
    BuildingProperties: Map<Model.BuildingType, Model.BuildingProperties>;
} with

    /// Write Game to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.MyIndex
        writer.Write this.CurrentTick
        writer.Write this.MaxTickCount
        writer.Write this.Players.Length
        this.Players |> Array.iter (fun value ->
            value.writeTo writer )
        writer.Write this.Planets.Length
        this.Planets |> Array.iter (fun value ->
            value.writeTo writer )
        writer.Write this.FlyingWorkerGroups.Length
        this.FlyingWorkerGroups |> Array.iter (fun value ->
            value.writeTo writer )
        writer.Write this.MaxFlyingWorkerGroups
        writer.Write this.MaxTravelDistance
        writer.Write this.MaxBuilders
        writer.Write this.BuildingProperties.Count
        this.BuildingProperties |> Map.iter (fun key value ->
            writer.Write (int key)
            value.writeTo writer )
        ()

    /// Read Game from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        MyIndex = reader.ReadInt32()
        CurrentTick = reader.ReadInt32()
        MaxTickCount = reader.ReadInt32()
        Players = [|for _ in 1 .. reader.ReadInt32() do
                      yield Model.Player.readFrom reader; |]
        Planets = [|for _ in 1 .. reader.ReadInt32() do
                      yield Model.Planet.readFrom reader; |]
        FlyingWorkerGroups = [|for _ in 1 .. reader.ReadInt32() do
                                 yield Model.FlyingWorkerGroup.readFrom reader; |]
        MaxFlyingWorkerGroups = reader.ReadInt32()
        MaxTravelDistance = reader.ReadInt32()
        MaxBuilders = reader.ReadInt32()
        BuildingProperties = [for _ in 1 .. reader.ReadInt32() do
                                 let key = reader.ReadInt32() |> enum
                                 let value = Model.BuildingProperties.readFrom reader;
                                 yield (key, value) ] |> Map.ofList
    }