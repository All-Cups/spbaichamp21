package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// Movement order
type MoveAction struct {
    // Id of the planet where workers need to be sent from
    StartPlanet int32
    // Id of the target planet
    TargetPlanet int32
    // Number of workers to send
    WorkerNumber int32
    // Resource workers should carry
    TakeResource *Resource
}

func NewMoveAction(startPlanet int32, targetPlanet int32, workerNumber int32, takeResource *Resource) MoveAction {
    return MoveAction {
        StartPlanet: startPlanet,
        TargetPlanet: targetPlanet,
        WorkerNumber: workerNumber,
        TakeResource: takeResource,
    }
}

// Read MoveAction from reader
func ReadMoveAction(reader io.Reader) MoveAction {
    var startPlanet int32
    startPlanet = ReadInt32(reader)
    var targetPlanet int32
    targetPlanet = ReadInt32(reader)
    var workerNumber int32
    workerNumber = ReadInt32(reader)
    var takeResource *Resource
    if ReadBool(reader) {
        var takeResourceValue Resource
        takeResourceValue = ReadResource(reader)
        takeResource = &takeResourceValue
    } else {
        takeResource = nil
    }
    return MoveAction {
        StartPlanet: startPlanet,
        TargetPlanet: targetPlanet,
        WorkerNumber: workerNumber,
        TakeResource: takeResource,
    }
}

// Write MoveAction to writer
func (moveAction MoveAction) Write(writer io.Writer) {
    startPlanet := moveAction.StartPlanet
    WriteInt32(writer, startPlanet)
    targetPlanet := moveAction.TargetPlanet
    WriteInt32(writer, targetPlanet)
    workerNumber := moveAction.WorkerNumber
    WriteInt32(writer, workerNumber)
    takeResource := moveAction.TakeResource
    if takeResource == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        takeResourceValue := *takeResource
        WriteInt32(writer, int32(takeResourceValue))
    }
}

// Get string representation of MoveAction
func (moveAction MoveAction) String() string {
    stringResult := "{ "
    stringResult += "StartPlanet: "
    startPlanet := moveAction.StartPlanet
    stringResult += fmt.Sprint(startPlanet)
    stringResult += ", "
    stringResult += "TargetPlanet: "
    targetPlanet := moveAction.TargetPlanet
    stringResult += fmt.Sprint(targetPlanet)
    stringResult += ", "
    stringResult += "WorkerNumber: "
    workerNumber := moveAction.WorkerNumber
    stringResult += fmt.Sprint(workerNumber)
    stringResult += ", "
    stringResult += "TakeResource: "
    takeResource := moveAction.TakeResource
    if takeResource == nil {
        stringResult += "nil"
    } else {
        takeResourceValue := *takeResource
        stringResult += ResourceToString(takeResourceValue)
    }
    stringResult += " }"
    return stringResult
}