package model

import "io"
import . "spb_ai_champ/stream"

// Building type
type BuildingType int32

const (
    // Quarry harvests stone
    BuildingTypeQuarry BuildingType = 0
    // Mines harvests ore
    BuildingTypeMines BuildingType = 1
    // Career harvest sand
    BuildingTypeCareer BuildingType = 2
    // Farm harvests organics
    BuildingTypeFarm BuildingType = 3
    // Foundry produces metal
    BuildingTypeFoundry BuildingType = 4
    // Furnace produces silicon
    BuildingTypeFurnace BuildingType = 5
    // Bioreactor produces plastic
    BuildingTypeBioreactor BuildingType = 6
    // Chip factory produces chips
    BuildingTypeChipFactory BuildingType = 7
    // Accumulator factory produces accumulators
    BuildingTypeAccumulatorFactory BuildingType = 8
    // Replicator produces new workers
    BuildingTypeReplicator BuildingType = 9
    // Second level replicator
    BuildingTypeReplicator2 BuildingType = 10
    // Third level replicator
    BuildingTypeReplicator3 BuildingType = 11
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
    case 10:
        return BuildingTypeReplicator2
    case 11:
        return BuildingTypeReplicator3
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
    case BuildingTypeReplicator2:
        return "Replicator2"
    case BuildingTypeReplicator3:
        return "Replicator3"
    }
    panic("Impossible happened")
}