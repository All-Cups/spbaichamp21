#include "BuildingType.hpp"
#include <stdexcept>

namespace model {

// Read BuildingType from input stream
BuildingType readBuildingType(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return BuildingType::QUARRY;
    case 1:
        return BuildingType::MINES;
    case 2:
        return BuildingType::CAREER;
    case 3:
        return BuildingType::FARM;
    case 4:
        return BuildingType::FOUNDRY;
    case 5:
        return BuildingType::FURNACE;
    case 6:
        return BuildingType::BIOREACTOR;
    case 7:
        return BuildingType::CHIP_FACTORY;
    case 8:
        return BuildingType::ACCUMULATOR_FACTORY;
    case 9:
        return BuildingType::REPLICATOR;
    case 10:
        return BuildingType::REPLICATOR2;
    case 11:
        return BuildingType::REPLICATOR3;
    default:
        throw std::runtime_error("Unexpected tag value");
    }
}

// Get string representation of BuildingType
std::string buildingTypeToString(BuildingType value) {
    switch (value) {
    case BuildingType::QUARRY:
        return "QUARRY";
    case BuildingType::MINES:
        return "MINES";
    case BuildingType::CAREER:
        return "CAREER";
    case BuildingType::FARM:
        return "FARM";
    case BuildingType::FOUNDRY:
        return "FOUNDRY";
    case BuildingType::FURNACE:
        return "FURNACE";
    case BuildingType::BIOREACTOR:
        return "BIOREACTOR";
    case BuildingType::CHIP_FACTORY:
        return "CHIP_FACTORY";
    case BuildingType::ACCUMULATOR_FACTORY:
        return "ACCUMULATOR_FACTORY";
    case BuildingType::REPLICATOR:
        return "REPLICATOR";
    case BuildingType::REPLICATOR2:
        return "REPLICATOR2";
    case BuildingType::REPLICATOR3:
        return "REPLICATOR3";
    default:
        throw std::runtime_error("Impossible happened");
    }
}

}