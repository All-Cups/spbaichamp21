#nowarn "0058"

namespace SpbAiChamp
namespace SpbAiChamp.Codegame

open SpbAiChamp

/// Get action for next tick
type ServerMessageGetAction = {
    /// Player's view
    PlayerView: Model.Game;
    /// Whether app is running with debug interface available
    DebugAvailable: bool;
} with

    /// Write GetAction to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 0
        this.PlayerView.writeTo writer
        writer.Write this.DebugAvailable
        ()

    /// Read GetAction from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        PlayerView = Model.Game.readFrom reader;
        DebugAvailable = reader.ReadBoolean()
    }

/// Signifies end of the game
type ServerMessageFinish = struct end with

    /// Write Finish to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 1
        ()

    /// Read Finish from reader
    static member readFrom(reader: System.IO.BinaryReader) = new ServerMessageFinish()

/// Debug update
type ServerMessageDebugUpdate = {
    /// Player's view
    PlayerView: Model.Game;
} with

    /// Write DebugUpdate to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 2
        this.PlayerView.writeTo writer
        ()

    /// Read DebugUpdate from reader
    static member readFrom(reader: System.IO.BinaryReader) = {
        PlayerView = Model.Game.readFrom reader;
    }

/// Message sent from server
type ServerMessage =
    /// Get action for next tick
    | GetAction of ServerMessageGetAction
    /// Signifies end of the game
    | Finish of ServerMessageFinish
    /// Debug update
    | DebugUpdate of ServerMessageDebugUpdate
    with

    /// Write ServerMessage to writer
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | GetAction value -> value.writeTo writer
            | Finish value -> value.writeTo writer
            | DebugUpdate value -> value.writeTo writer

    /// Read ServerMessage from reader
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> GetAction (ServerMessageGetAction.readFrom reader)
            | 1 -> Finish (ServerMessageFinish.readFrom reader)
            | 2 -> DebugUpdate (ServerMessageDebugUpdate.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)