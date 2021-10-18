#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type FlyingWorkerGroup = {
    /// TODO - Document
    PlayerIndex: int;
    /// TODO - Document
    Number: int;
    /// TODO - Document
    DepartureTick: int;
    /// TODO - Document
    DeparturePlanet: int;
    /// TODO - Document
    NextPlanetArrivalTick: int;
    /// TODO - Document
    NextPlanet: int;
    /// TODO - Document
    TargetPlanet: int;
    /// TODO - Document
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