#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Model

open SpbAiChamp

/// Player's actions
type Action = {
    /// List of movement orders
    Moves: Model.MoveAction[];
    /// List of building orders
    Buildings: Model.BuildingAction[];
    /// Choosing specialty
    ChooseSpecialty: option<Model.Specialty>;
} with

    /// Write Action to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Moves.Length
        this.Moves |> Array.iter (fun value ->
            value.writeTo writer )
        writer.Write this.Buildings.Length
        this.Buildings |> Array.iter (fun value ->
            value.writeTo writer )
        match this.ChooseSpecialty with
            | Some value ->
                writer.Write true
                writer.Write (int value)
            | None -> writer.Write false
        ()

    /// Read Action from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Moves = [|for _ in 1 .. reader.ReadInt32() do
                    yield Model.MoveAction.readFrom reader; |]
        Buildings = [|for _ in 1 .. reader.ReadInt32() do
                        yield Model.BuildingAction.readFrom reader; |]
        ChooseSpecialty = match reader.ReadBoolean() with
                              | true -> Some(reader.ReadInt32() |> enum)
                              | false -> None
    }