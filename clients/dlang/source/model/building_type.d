module model.building_type;

import stream;

/// Building type
enum BuildingType : int {
    /// Quarry harvests stone
    Quarry = 0,
    /// Mines harvests ore
    Mines = 1,
    /// Career harvest sand
    Career = 2,
    /// Farm harvests organics
    Farm = 3,
    /// Foundry produces metal
    Foundry = 4,
    /// Furnace produces silicon
    Furnace = 5,
    /// Bioreactor produces plastic
    Bioreactor = 6,
    /// Chip factory produces chips
    ChipFactory = 7,
    /// Accumulator factory produces accumulators
    AccumulatorFactory = 8,
    /// Replicator produces new workers
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