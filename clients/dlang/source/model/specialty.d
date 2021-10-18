module model.specialty;

import stream;

/// Player's specialty
enum Specialty : int {
    /// Logistics. Increased travel distance
    Logistics = 0,
    /// Production. Increased work speed
    Production = 1,
    /// Combat. Increased damage
    Combat = 2,
}

/// Read Specialty from reader
Specialty readSpecialty(Stream reader) {
    switch (reader.readInt()) {
        case Specialty.Logistics:
            return Specialty.Logistics;
        case Specialty.Production:
            return Specialty.Production;
        case Specialty.Combat:
            return Specialty.Combat;
        default:
            throw new Exception("Unexpected tag value");
    }
}