package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// Building properties
type BuildingProperties struct {
    // Building type that this building can be upgraded from
    BaseBuilding *BuildingType
    // Resources required for building
    BuildResources map[Resource]int32
    // Max health points of the building
    MaxHealth int32
    // Max number of workers in the building
    MaxWorkers int32
    // Resources required to start another task
    WorkResources map[Resource]int32
    // Whether performing a task spawn new workers
    ProduceWorker bool
    // Resource produced when performing a task
    ProduceResource *Resource
    // Amount of resources/workers produced when performing one task
    ProduceAmount int32
    // Score points given for performing one task
    ProduceScore int32
    // Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
    Harvest bool
    // Amount of work needed to finish one task
    WorkAmount int32
}

func NewBuildingProperties(baseBuilding *BuildingType, buildResources map[Resource]int32, maxHealth int32, maxWorkers int32, workResources map[Resource]int32, produceWorker bool, produceResource *Resource, produceAmount int32, produceScore int32, harvest bool, workAmount int32) BuildingProperties {
    return BuildingProperties {
        BaseBuilding: baseBuilding,
        BuildResources: buildResources,
        MaxHealth: maxHealth,
        MaxWorkers: maxWorkers,
        WorkResources: workResources,
        ProduceWorker: produceWorker,
        ProduceResource: produceResource,
        ProduceAmount: produceAmount,
        ProduceScore: produceScore,
        Harvest: harvest,
        WorkAmount: workAmount,
    }
}

// Read BuildingProperties from reader
func ReadBuildingProperties(reader io.Reader) BuildingProperties {
    var baseBuilding *BuildingType
    if ReadBool(reader) {
        var baseBuildingValue BuildingType
        baseBuildingValue = ReadBuildingType(reader)
        baseBuilding = &baseBuildingValue
    } else {
        baseBuilding = nil
    }
    var buildResources map[Resource]int32
    buildResourcesSize := ReadInt32(reader)
    buildResources = make(map[Resource]int32)
    for buildResourcesIndex := int32(0); buildResourcesIndex < buildResourcesSize; buildResourcesIndex++ {
        var buildResourcesKey Resource
        buildResourcesKey = ReadResource(reader)
        var buildResourcesValue int32
        buildResourcesValue = ReadInt32(reader)
        buildResources[buildResourcesKey] = buildResourcesValue
    }
    var maxHealth int32
    maxHealth = ReadInt32(reader)
    var maxWorkers int32
    maxWorkers = ReadInt32(reader)
    var workResources map[Resource]int32
    workResourcesSize := ReadInt32(reader)
    workResources = make(map[Resource]int32)
    for workResourcesIndex := int32(0); workResourcesIndex < workResourcesSize; workResourcesIndex++ {
        var workResourcesKey Resource
        workResourcesKey = ReadResource(reader)
        var workResourcesValue int32
        workResourcesValue = ReadInt32(reader)
        workResources[workResourcesKey] = workResourcesValue
    }
    var produceWorker bool
    produceWorker = ReadBool(reader)
    var produceResource *Resource
    if ReadBool(reader) {
        var produceResourceValue Resource
        produceResourceValue = ReadResource(reader)
        produceResource = &produceResourceValue
    } else {
        produceResource = nil
    }
    var produceAmount int32
    produceAmount = ReadInt32(reader)
    var produceScore int32
    produceScore = ReadInt32(reader)
    var harvest bool
    harvest = ReadBool(reader)
    var workAmount int32
    workAmount = ReadInt32(reader)
    return BuildingProperties {
        BaseBuilding: baseBuilding,
        BuildResources: buildResources,
        MaxHealth: maxHealth,
        MaxWorkers: maxWorkers,
        WorkResources: workResources,
        ProduceWorker: produceWorker,
        ProduceResource: produceResource,
        ProduceAmount: produceAmount,
        ProduceScore: produceScore,
        Harvest: harvest,
        WorkAmount: workAmount,
    }
}

// Write BuildingProperties to writer
func (buildingProperties BuildingProperties) Write(writer io.Writer) {
    baseBuilding := buildingProperties.BaseBuilding
    if baseBuilding == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        baseBuildingValue := *baseBuilding
        WriteInt32(writer, int32(baseBuildingValue))
    }
    buildResources := buildingProperties.BuildResources
    WriteInt32(writer, int32(len(buildResources)))
    for buildResourcesKey, buildResourcesValue := range buildResources {
        WriteInt32(writer, int32(buildResourcesKey))
        WriteInt32(writer, buildResourcesValue)
    }
    maxHealth := buildingProperties.MaxHealth
    WriteInt32(writer, maxHealth)
    maxWorkers := buildingProperties.MaxWorkers
    WriteInt32(writer, maxWorkers)
    workResources := buildingProperties.WorkResources
    WriteInt32(writer, int32(len(workResources)))
    for workResourcesKey, workResourcesValue := range workResources {
        WriteInt32(writer, int32(workResourcesKey))
        WriteInt32(writer, workResourcesValue)
    }
    produceWorker := buildingProperties.ProduceWorker
    WriteBool(writer, produceWorker)
    produceResource := buildingProperties.ProduceResource
    if produceResource == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        produceResourceValue := *produceResource
        WriteInt32(writer, int32(produceResourceValue))
    }
    produceAmount := buildingProperties.ProduceAmount
    WriteInt32(writer, produceAmount)
    produceScore := buildingProperties.ProduceScore
    WriteInt32(writer, produceScore)
    harvest := buildingProperties.Harvest
    WriteBool(writer, harvest)
    workAmount := buildingProperties.WorkAmount
    WriteInt32(writer, workAmount)
}

// Get string representation of BuildingProperties
func (buildingProperties BuildingProperties) String() string {
    stringResult := "{ "
    stringResult += "BaseBuilding: "
    baseBuilding := buildingProperties.BaseBuilding
    if baseBuilding == nil {
        stringResult += "nil"
    } else {
        baseBuildingValue := *baseBuilding
        stringResult += BuildingTypeToString(baseBuildingValue)
    }
    stringResult += ", "
    stringResult += "BuildResources: "
    buildResources := buildingProperties.BuildResources
    stringResult += "map[ "
    buildResourcesIndex := 0
    for buildResourcesKey, buildResourcesValue := range buildResources {
        if buildResourcesIndex != 0 {
            stringResult += ", "
        }
        stringResult += ResourceToString(buildResourcesKey)
        stringResult += ": "
        stringResult += fmt.Sprint(buildResourcesValue)
        buildResourcesIndex++
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "MaxHealth: "
    maxHealth := buildingProperties.MaxHealth
    stringResult += fmt.Sprint(maxHealth)
    stringResult += ", "
    stringResult += "MaxWorkers: "
    maxWorkers := buildingProperties.MaxWorkers
    stringResult += fmt.Sprint(maxWorkers)
    stringResult += ", "
    stringResult += "WorkResources: "
    workResources := buildingProperties.WorkResources
    stringResult += "map[ "
    workResourcesIndex := 0
    for workResourcesKey, workResourcesValue := range workResources {
        if workResourcesIndex != 0 {
            stringResult += ", "
        }
        stringResult += ResourceToString(workResourcesKey)
        stringResult += ": "
        stringResult += fmt.Sprint(workResourcesValue)
        workResourcesIndex++
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "ProduceWorker: "
    produceWorker := buildingProperties.ProduceWorker
    stringResult += fmt.Sprint(produceWorker)
    stringResult += ", "
    stringResult += "ProduceResource: "
    produceResource := buildingProperties.ProduceResource
    if produceResource == nil {
        stringResult += "nil"
    } else {
        produceResourceValue := *produceResource
        stringResult += ResourceToString(produceResourceValue)
    }
    stringResult += ", "
    stringResult += "ProduceAmount: "
    produceAmount := buildingProperties.ProduceAmount
    stringResult += fmt.Sprint(produceAmount)
    stringResult += ", "
    stringResult += "ProduceScore: "
    produceScore := buildingProperties.ProduceScore
    stringResult += fmt.Sprint(produceScore)
    stringResult += ", "
    stringResult += "Harvest: "
    harvest := buildingProperties.Harvest
    stringResult += fmt.Sprint(harvest)
    stringResult += ", "
    stringResult += "WorkAmount: "
    workAmount := buildingProperties.WorkAmount
    stringResult += fmt.Sprint(workAmount)
    stringResult += " }"
    return stringResult
}