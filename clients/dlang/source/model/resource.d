module model.resource;

import stream;

/// TODO - Document
enum Resource : int {
    /// TODO - Document
    Stone = 0,
    /// TODO - Document
    Ore = 1,
    /// TODO - Document
    Sand = 2,
    /// TODO - Document
    Organics = 3,
    /// TODO - Document
    Metal = 4,
    /// TODO - Document
    Silicon = 5,
    /// TODO - Document
    Plastic = 6,
    /// TODO - Document
    Chip = 7,
    /// TODO - Document
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