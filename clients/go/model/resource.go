package model

import "io"
import . "spb_ai_champ/stream"

// TODO - Document
type Resource int32

const (
    // TODO - Document
    ResourceStone Resource = 0
    // TODO - Document
    ResourceOre Resource = 1
    // TODO - Document
    ResourceSand Resource = 2
    // TODO - Document
    ResourceOrganics Resource = 3
    // TODO - Document
    ResourceMetal Resource = 4
    // TODO - Document
    ResourceSilicon Resource = 5
    // TODO - Document
    ResourcePlastic Resource = 6
    // TODO - Document
    ResourceChip Resource = 7
    // TODO - Document
    ResourceAccumulator Resource = 8
)

// Read Resource from reader
func ReadResource(reader io.Reader) Resource {
    switch ReadInt32(reader) {
    case 0:
        return ResourceStone
    case 1:
        return ResourceOre
    case 2:
        return ResourceSand
    case 3:
        return ResourceOrganics
    case 4:
        return ResourceMetal
    case 5:
        return ResourceSilicon
    case 6:
        return ResourcePlastic
    case 7:
        return ResourceChip
    case 8:
        return ResourceAccumulator
    }
    panic("Unexpected tag value")
}

// Get string representation of Resource
func ResourceToString(resource Resource) string {
    switch resource {
    case ResourceStone:
        return "Stone"
    case ResourceOre:
        return "Ore"
    case ResourceSand:
        return "Sand"
    case ResourceOrganics:
        return "Organics"
    case ResourceMetal:
        return "Metal"
    case ResourceSilicon:
        return "Silicon"
    case ResourcePlastic:
        return "Plastic"
    case ResourceChip:
        return "Chip"
    case ResourceAccumulator:
        return "Accumulator"
    }
    panic("Impossible happened")
}