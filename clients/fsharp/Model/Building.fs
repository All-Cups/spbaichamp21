#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type Building = {
    /// TODO - Document
    BuildingType: Model.BuildingType;
    /// TODO - Document
    Health: int;
    /// TODO - Document
    WorkDone: int;
} with

    /// Write Building to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write (int this.BuildingType)
        writer.Write this.Health
        writer.Write this.WorkDone
        ()

    /// Read Building from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        BuildingType = reader.ReadInt32() |> enum
        Health = reader.ReadInt32()
        WorkDone = reader.ReadInt32()
    }