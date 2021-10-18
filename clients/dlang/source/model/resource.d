module model.resource;

import stream;

/// Resource type
enum Resource : int {
    /// Stone
    Stone = 0,
    /// Ore
    Ore = 1,
    /// Sand
    Sand = 2,
    /// Organics
    Organics = 3,
    /// Metal
    Metal = 4,
    /// Silicon
    Silicon = 5,
    /// Plastic
    Plastic = 6,
    /// Chip
    Chip = 7,
    /// Accumulator
    Accumulator = 8,
}

/// Read Resource from reader
Resource readResource(Stream reader) {
    switch (reader.readInt()) {
        case Resource.Stone:
            return Resource.Stone;
        case Resource.Ore:
            return Resource.Ore;
        case Resource.Sand:
            return Resource.Sand;
        case Resource.Organics:
            return Resource.Organics;
        case Resource.Metal:
            return Resource.Metal;
        case Resource.Silicon:
            return Resource.Silicon;
        case Resource.Plastic:
            return Resource.Plastic;
        case Resource.Chip:
            return Resource.Chip;
        case Resource.Accumulator:
            return Resource.Accumulator;
        default:
            throw new Exception("Unexpected tag value");
    }
}