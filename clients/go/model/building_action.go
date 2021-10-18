package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// Building order
type BuildingAction struct {
    // Id of the planet where the action needs to be performed
    Planet int32
    // Type of a building to build. If absent, current building will be destroyed
    BuildingType *BuildingType
}

func NewBuildingAction(planet int32, buildingType *BuildingType) BuildingAction {
    return BuildingAction {
        Planet: planet,
        BuildingType: buildingType,
    }
}

// Read BuildingAction from reader
func ReadBuildingAction(reader io.Reader) BuildingAction {
    var planet int32
    planet = ReadInt32(reader)
    var buildingType *BuildingType
    if ReadBool(reader) {
        var buildingTypeValue BuildingType
        buildingTypeValue = ReadBuildingType(reader)
        buildingType = &buildingTypeValue
    } else {
        buildingType = nil
    }
    return BuildingAction {
        Planet: planet,
        BuildingType: buildingType,
    }
}

// Write BuildingAction to writer
func (buildingAction BuildingAction) Write(writer io.Writer) {
    planet := buildingAction.Planet
    WriteInt32(writer, planet)
    buildingType := buildingAction.BuildingType
    if buildingType == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        buildingTypeValue := *buildingType
        WriteInt32(writer, int32(buildingTypeValue))
    }
}

// Get string representation of BuildingAction
func (buildingAction BuildingAction) String() string {
    stringResult := "{ "
    stringResult += "Planet: "
    planet := buildingAction.Planet
    stringResult += fmt.Sprint(planet)
    stringResult += ", "
    stringResult += "BuildingType: "
    buildingType := buildingAction.BuildingType
    if buildingType == nil {
        stringResult += "nil"
    } else {
        buildingTypeValue := *buildingType
        stringResult += BuildingTypeToString(buildingTypeValue)
    }
    stringResult += " }"
    return stringResult
}