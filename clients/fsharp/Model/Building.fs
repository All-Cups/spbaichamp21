#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// A building
type Building = {
    /// Building's type
    BuildingType: Model.BuildingType;
    /// Current health
    Health: int;
    /// Amount of work done for current task
    WorkDone: int;
    /// Number of tasks finished since last tick
    LastTickTasksDone: int;
} with

    /// Write Building to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write (int this.BuildingType)
        writer.Write this.Health
        writer.Write this.WorkDone
        writer.Write this.LastTickTasksDone
        ()

    /// Read Building from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        BuildingType = reader.ReadInt32() |> enum
        Health = reader.ReadInt32()
        WorkDone = reader.ReadInt32()
        LastTickTasksDone = reader.ReadInt32()
    }