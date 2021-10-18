package model

import "io"
import . "spb_ai_champ/stream"

// TODO - Document
type BuildingType int32

const (
    // TODO - Document
    BuildingTypeQuarry BuildingType = 0
    // TODO - Document
    BuildingTypeMines BuildingType = 1
    // TODO - Document
    BuildingTypeCareer BuildingType = 2
    // TODO - Document
    BuildingTypeFarm BuildingType = 3
    // TODO - Document
    BuildingTypeFoundry BuildingType = 4
    // TODO - Document
    BuildingTypeFurnace BuildingType = 5
    // TODO - Document
    BuildingTypeBioreactor BuildingType = 6
    // TODO - Document
    BuildingTypeChipFactory BuildingType = 7
    // TODO - Document
    BuildingTypeAccumulatorFactory BuildingType = 8
    // TODO - Document
    BuildingTypeReplicator BuildingType = 9
)

// Read BuildingType from reader
func ReadBuildingType(reader io.Reader) BuildingType {
    switch ReadInt32(reader) {
    case 0:
        return BuildingTypeQuarry
    case 1:
        return BuildingTypeMines
    case 2:
        return BuildingTypeCareer
    case 3:
        return BuildingTypeFarm
    case 4:
        return BuildingTypeFoundry
    case 5:
        return BuildingTypeFurnace
    case 6:
        return BuildingTypeBioreactor
    case 7:
        return BuildingTypeChipFactory
    case 8:
        return BuildingTypeAccumulatorFactory
    case 9:
        return BuildingTypeReplicator
    }
    panic("Unexpected tag value")
}

// Get string representation of BuildingType
func BuildingTypeToString(buildingType BuildingType) string {
    switch buildingType {
    case BuildingTypeQuarry:
        return "Quarry"
    case BuildingTypeMines:
        return "Mines"
    case BuildingTypeCareer:
        return "Career"
    case BuildingTypeFarm:
        return "Farm"
    case BuildingTypeFoundry:
        return "Foundry"
    case BuildingTypeFurnace:
        return "Furnace"
    case BuildingTypeBioreactor:
        return "Bioreactor"
    case BuildingTypeChipFactory:
        return "ChipFactory"
    case BuildingTypeAccumulatorFactory:
        return "AccumulatorFactory"
    case BuildingTypeReplicator:
        return "Replicator"
    }
    panic("Impossible happened")
}