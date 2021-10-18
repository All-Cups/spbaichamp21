package model

import "io"
import . "spb_ai_champ/stream"

// Player's actions
type Action struct {
    // List of movement orders
    Moves []MoveAction
    // List of building orders
    Buildings []BuildingAction
    // Choosing specialty
    ChooseSpecialty *Specialty
}

func NewAction(moves []MoveAction, buildings []BuildingAction, chooseSpecialty *Specialty) Action {
    return Action {
        Moves: moves,
        Buildings: buildings,
        ChooseSpecialty: chooseSpecialty,
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
    var chooseSpecialty *Specialty
    if ReadBool(reader) {
        var chooseSpecialtyValue Specialty
        chooseSpecialtyValue = ReadSpecialty(reader)
        chooseSpecialty = &chooseSpecialtyValue
    } else {
        chooseSpecialty = nil
    }
    return Action {
        Moves: moves,
        Buildings: buildings,
        ChooseSpecialty: chooseSpecialty,
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
    chooseSpecialty := action.ChooseSpecialty
    if chooseSpecialty == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        chooseSpecialtyValue := *chooseSpecialty
        WriteInt32(writer, int32(chooseSpecialtyValue))
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
    stringResult += ", "
    stringResult += "ChooseSpecialty: "
    chooseSpecialty := action.ChooseSpecialty
    if chooseSpecialty == nil {
        stringResult += "nil"
    } else {
        chooseSpecialtyValue := *chooseSpecialty
        stringResult += SpecialtyToString(chooseSpecialtyValue)
    }
    stringResult += " }"
    return stringResult
}