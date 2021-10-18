package codegame

import "fmt"
import "io"
import . "spb_ai_champ/model"
import . "spb_ai_champ/stream"

// Message sent from server
type ServerMessage interface {
    // Write ServerMessage to writer
    Write(writer io.Writer)

    // Get string representation of ServerMessage
    String() string
}

// Read ServerMessage from reader
func ReadServerMessage(reader io.Reader) ServerMessage {
    switch ReadInt32(reader) {
    case 0:
        return ReadServerMessageGetAction(reader)
    case 1:
        return ReadServerMessageFinish(reader)
    case 2:
        return ReadServerMessageDebugUpdate(reader)
    }
    panic("Unexpected tag value")
}

// Get action for next tick
type ServerMessageGetAction struct {
    // Player's view
    PlayerView Game
    // Whether app is running with debug interface available
    DebugAvailable bool
}

func NewServerMessageGetAction(playerView Game, debugAvailable bool) ServerMessageGetAction {
    return ServerMessageGetAction {
        PlayerView: playerView,
        DebugAvailable: debugAvailable,
    }
}

// Read GetAction from reader
func ReadServerMessageGetAction(reader io.Reader) ServerMessageGetAction {
    var playerView Game
    playerView = ReadGame(reader)
    var debugAvailable bool
    debugAvailable = ReadBool(reader)
    return ServerMessageGetAction {
        PlayerView: playerView,
        DebugAvailable: debugAvailable,
    }
}

// Write GetAction to writer
func (serverMessageGetAction ServerMessageGetAction) Write(writer io.Writer) {
    WriteInt32(writer, 0)
    playerView := serverMessageGetAction.PlayerView
    playerView.Write(writer)
    debugAvailable := serverMessageGetAction.DebugAvailable
    WriteBool(writer, debugAvailable)
}

// Get string representation of GetAction
func (serverMessageGetAction ServerMessageGetAction) String() string {
    stringResult := "{ "
    stringResult += "PlayerView: "
    playerView := serverMessageGetAction.PlayerView
    stringResult += playerView.String()
    stringResult += ", "
    stringResult += "DebugAvailable: "
    debugAvailable := serverMessageGetAction.DebugAvailable
    stringResult += fmt.Sprint(debugAvailable)
    stringResult += " }"
    return stringResult
}

// Signifies end of the game
type ServerMessageFinish struct {
}

func NewServerMessageFinish() ServerMessageFinish {
    return ServerMessageFinish {
    }
}

// Read Finish from reader
func ReadServerMessageFinish(reader io.Reader) ServerMessageFinish {
    return ServerMessageFinish {
    }
}

// Write Finish to writer
func (serverMessageFinish ServerMessageFinish) Write(writer io.Writer) {
    WriteInt32(writer, 1)
}

// Get string representation of Finish
func (serverMessageFinish ServerMessageFinish) String() string {
    stringResult := "{ "
    stringResult += " }"
    return stringResult
}

// Debug update
type ServerMessageDebugUpdate struct {
    // Player's view
    PlayerView Game
}

func NewServerMessageDebugUpdate(playerView Game) ServerMessageDebugUpdate {
    return ServerMessageDebugUpdate {
        PlayerView: playerView,
    }
}

// Read DebugUpdate from reader
func ReadServerMessageDebugUpdate(reader io.Reader) ServerMessageDebugUpdate {
    var playerView Game
    playerView = ReadGame(reader)
    return ServerMessageDebugUpdate {
        PlayerView: playerView,
    }
}

// Write DebugUpdate to writer
func (serverMessageDebugUpdate ServerMessageDebugUpdate) Write(writer io.Writer) {
    WriteInt32(writer, 2)
    playerView := serverMessageDebugUpdate.PlayerView
    playerView.Write(writer)
}

// Get string representation of DebugUpdate
func (serverMessageDebugUpdate ServerMessageDebugUpdate) String() string {
    stringResult := "{ "
    stringResult += "PlayerView: "
    playerView := serverMessageDebugUpdate.PlayerView
    stringResult += playerView.String()
    stringResult += " }"
    return stringResult
}