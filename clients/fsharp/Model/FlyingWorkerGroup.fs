#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// A flying worker group
type FlyingWorkerGroup = {
    /// Index of player controlling workers
    PlayerIndex: int;
    /// Number of workers in the group
    Number: int;
    /// Tick when workers left previous planet on their path
    DepartureTick: int;
    /// Id of the previous planet on the path
    DeparturePlanet: int;
    /// Tick when workers will arrive to the next planet in their path
    NextPlanetArrivalTick: int;
    /// Id of the next planet in the path
    NextPlanet: int;
    /// Id of the target planet
    TargetPlanet: int;
    /// Resource that workers are carrying
    Resource: option<Model.Resource>;
} with

    /// Write FlyingWorkerGroup to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.PlayerIndex
        writer.Write this.Number
        writer.Write this.DepartureTick
        writer.Write this.DeparturePlanet
        writer.Write this.NextPlanetArrivalTick
        writer.Write this.NextPlanet
        writer.Write this.TargetPlanet
        match this.Resource with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        ()

    /// Read FlyingWorkerGroup from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        PlayerIndex = reader.ReadInt32()
        Number = reader.ReadInt32()
        DepartureTick = reader.ReadInt32()
        DeparturePlanet = reader.ReadInt32()
        NextPlanetArrivalTick = reader.ReadInt32()
        NextPlanet = reader.ReadInt32()
        TargetPlanet = reader.ReadInt32()
        Resource = match reader.ReadBoolean() with
                       | true -> Some(reader.ReadInt32() |> enum)
                       | false -> None
    }