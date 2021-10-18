package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// TODO - Document
type Planet struct {
    // TODO - Document
    Id int32
    // TODO - Document
    X int32
    // TODO - Document
    Y int32
    // TODO - Document
    HarvestableResource *Resource
    // TODO - Document
    WorkerGroups []WorkerGroup
    // TODO - Document
    Resources map[Resource]int32
    // TODO - Document
    Building *Building
}

func NewPlanet(id int32, x int32, y int32, harvestableResource *Resource, workerGroups []WorkerGroup, resources map[Resource]int32, building *Building) Planet {
    return Planet {
        Id: id,
        X: x,
        Y: y,
        HarvestableResource: harvestableResource,
        WorkerGroups: workerGroups,
        Resources: resources,
        Building: building,
    }
}

// Read Planet from reader
func ReadPlanet(reader io.Reader) Planet {
    var id int32
    id = ReadInt32(reader)
    var x int32
    x = ReadInt32(reader)
    var y int32
    y = ReadInt32(reader)
    var harvestableResource *Resource
    if ReadBool(reader) {
        var harvestableResourceValue Resource
        harvestableResourceValue = ReadResource(reader)
        harvestableResource = &harvestableResourceValue
    } else {
        harvestableResource = nil
    }
    var workerGroups []WorkerGroup
    workerGroups = make([]WorkerGroup, ReadInt32(reader))
    for workerGroupsIndex := range workerGroups {
        var workerGroupsElement WorkerGroup
        workerGroupsElement = ReadWorkerGroup(reader)
        workerGroups[workerGroupsIndex] = workerGroupsElement
    }
    var resources map[Resource]int32
    resourcesSize := ReadInt32(reader)
    resources = make(map[Resource]int32)
    for resourcesIndex := int32(0); resourcesIndex < resourcesSize; resourcesIndex++ {
        var resourcesKey Resource
        resourcesKey = ReadResource(reader)
        var resourcesValue int32
        resourcesValue = ReadInt32(reader)
        resources[resourcesKey] = resourcesValue
    }
    var building *Building
    if ReadBool(reader) {
        var buildingValue Building
        buildingValue = ReadBuilding(reader)
        building = &buildingValue
    } else {
        building = nil
    }
    return Planet {
        Id: id,
        X: x,
        Y: y,
        HarvestableResource: harvestableResource,
        WorkerGroups: workerGroups,
        Resources: resources,
        Building: building,
    }
}

// Write Planet to writer
func (planet Planet) Write(writer io.Writer) {
    id := planet.Id
    WriteInt32(writer, id)
    x := planet.X
    WriteInt32(writer, x)
    y := planet.Y
    WriteInt32(writer, y)
    harvestableResource := planet.HarvestableResource
    if harvestableResource == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        harvestableResourceValue := *harvestableResource
        WriteInt32(writer, int32(harvestableResourceValue))
    }
    workerGroups := planet.WorkerGroups
    WriteInt32(writer, int32(len(workerGroups)))
    for _, workerGroupsElement := range workerGroups {
        workerGroupsElement.Write(writer)
    }
    resources := planet.Resources
    WriteInt32(writer, int32(len(resources)))
    for resourcesKey, resourcesValue := range resources {
        WriteInt32(writer, int32(resourcesKey))
        WriteInt32(writer, resourcesValue)
    }
    building := planet.Building
    if building == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        buildingValue := *building
        buildingValue.Write(writer)
    }
}

// Get string representation of Planet
func (planet Planet) String() string {
    stringResult := "{ "
    stringResult += "Id: "
    id := planet.Id
    stringResult += fmt.Sprint(id)
    stringResult += ", "
    stringResult += "X: "
    x := planet.X
    stringResult += fmt.Sprint(x)
    stringResult += ", "
    stringResult += "Y: "
    y := planet.Y
    stringResult += fmt.Sprint(y)
    stringResult += ", "
    stringResult += "HarvestableResource: "
    harvestableResource := planet.HarvestableResource
    if harvestableResource == nil {
        stringResult += "nil"
    } else {
        harvestableResourceValue := *harvestableResource
        stringResult += ResourceToString(harvestableResourceValue)
    }
    stringResult += ", "
    stringResult += "WorkerGroups: "
    workerGroups := planet.WorkerGroups
    stringResult += "[ "
    for workerGroupsIndex, workerGroupsElement := range workerGroups {
        if workerGroupsIndex != 0 {
            stringResult += ", "
        }
        stringResult += workerGroupsElement.String()
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "Resources: "
    resources := planet.Resources
    stringResult += "map[ "
    resourcesIndex := 0
    for resourcesKey, resourcesValue := range resources {
        if resourcesIndex != 0 {
            stringResult += ", "
        }
        stringResult += ResourceToString(resourcesKey)
        stringResult += ": "
        stringResult += fmt.Sprint(resourcesValue)
        resourcesIndex++
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "Building: "
    building := planet.Building
    if building == nil {
        stringResult += "nil"
    } else {
        buildingValue := *building
        stringResult += buildingValue.String()
    }
    stringResult += " }"
    return stringResult
}