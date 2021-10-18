#ifndef __MODEL_BUILDING_TYPE_HPP__
#define __MODEL_BUILDING_TYPE_HPP__

#include "Stream.hpp"

namespace model {

// TODO - Document
enum class BuildingType {
    // TODO - Document
    QUARRY = 0,
    // TODO - Document
    MINES = 1,
    // TODO - Document
    CAREER = 2,
    // TODO - Document
    FARM = 3,
    // TODO - Document
    FOUNDRY = 4,
    // TODO - Document
    FURNACE = 5,
    // TODO - Document
    BIOREACTOR = 6,
    // TODO - Document
    CHIP_FACTORY = 7,
    // TODO - Document
    ACCUMULATOR_FACTORY = 8,
    // TODO - Document
    REPLICATOR = 9
};

// Read BuildingType from input stream
BuildingType readBuildingType(InputStream& stream);

// Get string representation of BuildingType
std::string buildingTypeToString(BuildingType value);

}

#endif