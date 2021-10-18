#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// TODO - Document
type Action = {
    /// TODO - Document
    Moves: Model.MoveAction[];
    /// TODO - Document
    Buildings: Model.BuildingAction[];
} with

    /// Write Action to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Moves.Length
        this.Moves |> Array.iter (fun value ->
            value.writeTo writer )
        writer.Write this.Buildings.Length
        this.Buildings |> Array.iter (fun value ->
            value.writeTo writer )
        ()

    /// Read Action from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Moves = [|for _ in 1 .. reader.ReadInt32() do
                    yield Model.MoveAction.readFrom reader; |]
        Buildings = [|for _ in 1 .. reader.ReadInt32() do
                        yield Model.BuildingAction.readFrom reader; |]
    }