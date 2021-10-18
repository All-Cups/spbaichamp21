#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Current game's state
type Game = {
    /// Your player's index
    MyIndex: int;
    /// Current tick number
    CurrentTick: int;
    /// Max number of ticks in the game
    MaxTickCount: int;
    /// List of players
    Players: Model.Player[];
    /// List of planets
    Planets: Model.Planet[];
    /// List of flying worker groups
    FlyingWorkerGroups: Model.FlyingWorkerGroup[];
    /// Max number of flying worker groups for one player
    MaxFlyingWorkerGroups: int;
    /// Max distance of direct travel between planets
    MaxTravelDistance: int;
    /// Max number of workers performing building on one planet
    MaxBuilders: int;
    /// Properties of every building type
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