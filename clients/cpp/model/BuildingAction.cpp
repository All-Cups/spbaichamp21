#include "BuildingAction.hpp"

namespace model {

BuildingAction::BuildingAction(int planet, std::optional<model::BuildingType> buildingType) : planet(planet), buildingType(buildingType) { }

// Read BuildingAction from input stream
BuildingAction BuildingAction::readFrom(InputStream& stream) {
    int planet = stream.readInt();
    std::optional<model::BuildingType> buildingType = std::optional<model::BuildingType>();
    if (stream.readBool()) {
        model::BuildingType buildingTypeValue = readBuildingType(stream);
        buildingType.emplace(buildingTypeValue);
    }
    return BuildingAction(planet, buildingType);
}

// Write BuildingAction to output stream
void BuildingAction::writeTo(OutputStream& stream) const {
    stream.write(planet);
    if (buildingType) {
        stream.write(true);
        const model::BuildingType& buildingTypeValue = *buildingType;
        stream.write((int)(buildingTypeValue));
    } else {
        stream.write(false);
    }
}

// Get string representation of BuildingAction
std::string BuildingAction::toString() const {
    std::stringstream ss;
    ss << "BuildingAction { ";
    ss << "planet: ";
    ss << planet;
    ss << ", ";
    ss << "buildingType: ";
    if (buildingType) {
        const model::BuildingType& buildingTypeValue = *buildingType;
        ss << buildingTypeToString(buildingTypeValue);
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}