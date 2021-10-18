#ifndef __MODEL_BUILDING_TYPE_HPP__
#define __MODEL_BUILDING_TYPE_HPP__

#include "Stream.hpp"

namespace model {

// Building type
enum class BuildingType {
    // Quarry harvests stone
    QUARRY = 0,
    // Mines harvests ore
    MINES = 1,
    // Career harvest sand
    CAREER = 2,
    // Farm harvests organics
    FARM = 3,
    // Foundry produces metal
    FOUNDRY = 4,
    // Furnace produces silicon
    FURNACE = 5,
    // Bioreactor produces plastic
    BIOREACTOR = 6,
    // Chip factory produces chips
    CHIP_FACTORY = 7,
    // Accumulator factory produces accumulators
    ACCUMULATOR_FACTORY = 8,
    // Replicator produces new workers
    REPLICATOR = 9
};

// Read BuildingType from input stream
BuildingType readBuildingType(InputStream& stream);

// Get string representation of BuildingType
std::string buildingTypeToString(BuildingType value);

}

#endif