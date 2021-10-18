#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Codegame

open SpbAiChamp

/// Ask app to perform new debug command
type ClientMessageDebugMessage = {
    /// Command to perform
    Command: Debugging.DebugCommand;
} with

    /// Write DebugMessage to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 0
        this.Command.writeTo writer
        ()

    /// Read DebugMessage from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Command = Debugging.DebugCommand.readFrom reader;
    }

/// Reply for ServerMessage::GetAction
type ClientMessageActionMessage = {
    /// Player's action
    Action: Model.Action;
} with

    /// Write ActionMessage to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 1
        this.Action.writeTo writer
        ()

    /// Read ActionMessage from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        Action = Model.Action.readFrom reader;
    }

/// Signifies finish of the debug update
type ClientMessageDebugUpdateDone = struct end with

    /// Write DebugUpdateDone to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 2
        ()

    /// Read DebugUpdateDone from reader
    static member readFrom(reader: System.IO.BinaryReader) = new ClientMessageDebugUpdateDone()

/// Request debug state from the app
type ClientMessageRequestDebugState = struct end with

    /// Write RequestDebugState to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 3
        ()

    /// Read RequestDebugState from reader
    static member readFrom(reader: System.IO.BinaryReader) = new ClientMessageRequestDebugState()

/// Message sent from client
type ClientMessage =
    /// Ask app to perform new debug command
    | DebugMessage of ClientMessageDebugMessage
    /// Reply for ServerMessage::GetAction
    | ActionMessage of ClientMessageActionMessage
    /// Signifies finish of the debug update
    | DebugUpdateDone of ClientMessageDebugUpdateDone
    /// Request debug state from the app
    | RequestDebugState of ClientMessageRequestDebugState
    with

    /// Write ClientMessage to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | DebugMessage value -> value.writeTo writer
            | ActionMessage value -> value.writeTo writer
            | DebugUpdateDone value -> value.writeTo writer
            | RequestDebugState value -> value.writeTo writer

    /// Read ClientMessage from reader
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> DebugMessage (ClientMessageDebugMessage.readFrom reader)
            | 1 -> ActionMessage (ClientMessageActionMessage.readFrom reader)
            | 2 -> DebugUpdateDone (ClientMessageDebugUpdateDone.readFrom reader)
            | 3 -> RequestDebugState (ClientMessageRequestDebugState.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)