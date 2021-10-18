package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// A building
type Building struct {
    // Building's type
    BuildingType BuildingType
    // Current health
    Health int32
    // Amount of work done for current task
    WorkDone int32
    // Number of tasks finished since last tick
    LastTickTasksDone int32
}

func NewBuilding(buildingType BuildingType, health int32, workDone int32, lastTickTasksDone int32) Building {
    return Building {
        BuildingType: buildingType,
        Health: health,
        WorkDone: workDone,
        LastTickTasksDone: lastTickTasksDone,
    }
}

// Read Building from reader
func ReadBuilding(reader io.Reader) Building {
    var buildingType BuildingType
    buildingType = ReadBuildingType(reader)
    var health int32
    health = ReadInt32(reader)
    var workDone int32
    workDone = ReadInt32(reader)
    var lastTickTasksDone int32
    lastTickTasksDone = ReadInt32(reader)
    return Building {
        BuildingType: buildingType,
        Health: health,
        WorkDone: workDone,
        LastTickTasksDone: lastTickTasksDone,
    }
}

// Write Building to writer
func (building Building) Write(writer io.Writer) {
    buildingType := building.BuildingType
    WriteInt32(writer, int32(buildingType))
    health := building.Health
    WriteInt32(writer, health)
    workDone := building.WorkDone
    WriteInt32(writer, workDone)
    lastTickTasksDone := building.LastTickTasksDone
    WriteInt32(writer, lastTickTasksDone)
}

// Get string representation of Building
func (building Building) String() string {
    stringResult := "{ "
    stringResult += "BuildingType: "
    buildingType := building.BuildingType
    stringResult += BuildingTypeToString(buildingType)
    stringResult += ", "
    stringResult += "Health: "
    health := building.Health
    stringResult += fmt.Sprint(health)
    stringResult += ", "
    stringResult += "WorkDone: "
    workDone := building.WorkDone
    stringResult += fmt.Sprint(workDone)
    stringResult += ", "
    stringResult += "LastTickTasksDone: "
    lastTickTasksDone := building.LastTickTasksDone
    stringResult += fmt.Sprint(lastTickTasksDone)
    stringResult += " }"
    return stringResult
}