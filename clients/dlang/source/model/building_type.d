module model.building_type;

import stream;

/// TODO - Document
enum BuildingType : int {
    /// TODO - Document
    Quarry = 0,
    /// TODO - Document
    Mines = 1,
    /// TODO - Document
    Career = 2,
    /// TODO - Document
    Farm = 3,
    /// TODO - Document
    Foundry = 4,
    /// TODO - Document
    Furnace = 5,
    /// TODO - Document
    Bioreactor = 6,
    /// TODO - Document
    ChipFactory = 7,
    /// TODO - Document
    AccumulatorFactory = 8,
    /// TODO - Document
    Replicator = 9,
}

/// Read BuildingType from reader
BuildingType readBuildingType(Stream reader) {
    switch (reader.readInt()) {
        case BuildingType.Quarry:
            return BuildingType.Quarry;
        case BuildingType.Mines:
            return BuildingType.Mines;
        case BuildingType.Career:
            return BuildingType.Career;
        case BuildingType.Farm:
            return BuildingType.Farm;
        case BuildingType.Foundry:
            return BuildingType.Foundry;
        case BuildingType.Furnace:
            return BuildingType.Furnace;
        case BuildingType.Bioreactor:
            return BuildingType.Bioreactor;
        case BuildingType.ChipFactory:
            return BuildingType.ChipFactory;
        case BuildingType.AccumulatorFactory:
            return BuildingType.AccumulatorFactory;
        case BuildingType.Replicator:
            return BuildingType.Replicator;
        default:
            throw new Exception("Unexpected tag value");
    }
}