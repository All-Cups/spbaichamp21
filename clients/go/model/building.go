package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// TODO - Document
type Building struct {
    // TODO - Document
    BuildingType BuildingType
    // TODO - Document
    Health int32
    // TODO - Document
    WorkDone int32
}

func NewBuilding(buildingType BuildingType, health int32, workDone int32) Building {
    return Building {
        BuildingType: buildingType,
        Health: health,
        WorkDone: workDone,
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
    return Building {
        BuildingType: buildingType,
        Health: health,
        WorkDone: workDone,
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
    stringResult += " }"
    return stringResult
}