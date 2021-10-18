package model

import "io"
import . "spb_ai_champ/stream"

// Player's specialty
type Specialty int32

const (
    // Logistics. Increased travel distance
    SpecialtyLogistics Specialty = 0
    // Production. Increased work speed
    SpecialtyProduction Specialty = 1
    // Combat. Increased damage
    SpecialtyCombat Specialty = 2
)

// Read Specialty from reader
func ReadSpecialty(reader io.Reader) Specialty {
    switch ReadInt32(reader) {
    case 0:
        return SpecialtyLogistics
    case 1:
        return SpecialtyProduction
    case 2:
        return SpecialtyCombat
    }
    panic("Unexpected tag value")
}

// Get string representation of Specialty
func SpecialtyToString(specialty Specialty) string {
    switch specialty {
    case SpecialtyLogistics:
        return "Logistics"
    case SpecialtyProduction:
        return "Production"
    case SpecialtyCombat:
        return "Combat"
    }
    panic("Impossible happened")
}