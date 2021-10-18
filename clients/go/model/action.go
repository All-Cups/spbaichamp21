package model

import "io"
import . "spb_ai_champ/stream"

// TODO - Document
type Action struct {
    // TODO - Document
    Moves []MoveAction
    // TODO - Document
    Buildings []BuildingAction
}

func NewAction(moves []MoveAction, buildings []BuildingAction) Action {
    return Action {
        Moves: moves,
        Buildings: buildings,
    }
}

// Read Action from reader
func ReadAction(reader io.Reader) Action {
    var moves []MoveAction
    moves = make([]MoveAction, ReadInt32(reader))
    for movesIndex := range moves {
        var movesElement MoveAction
        movesElement = ReadMoveAction(reader)
        moves[movesIndex] = movesElement
    }
    var buildings []BuildingAction
    buildings = make([]BuildingAction, ReadInt32(reader))
    for buildingsIndex := range buildings {
        var buildingsElement BuildingAction
        buildingsElement = ReadBuildingAction(reader)
        buildings[buildingsIndex] = buildingsElement
    }
    return Action {
        Moves: moves,
        Buildings: buildings,
    }
}

// Write Action to writer
func (action Action) Write(writer io.Writer) {
    moves := action.Moves
    WriteInt32(writer, int32(len(moves)))
    for _, movesElement := range moves {
        movesElement.Write(writer)
    }
    buildings := action.Buildings
    WriteInt32(writer, int32(len(buildings)))
    for _, buildingsElement := range buildings {
        buildingsElement.Write(writer)
    }
}

// Get string representation of Action
func (action Action) String() string {
    stringResult := "{ "
    stringResult += "Moves: "
    moves := action.Moves
    stringResult += "[ "
    for movesIndex, movesElement := range moves {
        if movesIndex != 0 {
            stringResult += ", "
        }
        stringResult += movesElement.String()
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "Buildings: "
    buildings := action.Buildings
    stringResult += "[ "
    for buildingsIndex, buildingsElement := range buildings {
        if buildingsIndex != 0 {
            stringResult += ", "
        }
        stringResult += buildingsElement.String()
    }
    stringResult += " ]"
    stringResult += " }"
    return stringResult
}