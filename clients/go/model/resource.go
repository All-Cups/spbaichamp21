package model

import "io"
import . "spb_ai_champ/stream"

// Resource type
type Resource int32

const (
    // Stone
    ResourceStone Resource = 0
    // Ore
    ResourceOre Resource = 1
    // Sand
    ResourceSand Resource = 2
    // Organics
    ResourceOrganics Resource = 3
    // Metal
    ResourceMetal Resource = 4
    // Silicon
    ResourceSilicon Resource = 5
    // Plastic
    ResourcePlastic Resource = 6
    // Chip
    ResourceChip Resource = 7
    // Accumulator
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