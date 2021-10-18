#include "Building.hpp"

namespace model {

Building::Building(model::BuildingType buildingType, int health, int workDone, int lastTickTasksDone) : buildingType(buildingType), health(health), workDone(workDone), lastTickTasksDone(lastTickTasksDone) { }

// Read Building from input stream
Building Building::readFrom(InputStream& stream) {
    model::BuildingType buildingType = readBuildingType(stream);
    int health = stream.readInt();
    int workDone = stream.readInt();
    int lastTickTasksDone = stream.readInt();
    return Building(buildingType, health, workDone, lastTickTasksDone);
}

// Write Building to output stream
void Building::writeTo(OutputStream& stream) const {
    stream.write((int)(buildingType));
    stream.write(health);
    stream.write(workDone);
    stream.write(lastTickTasksDone);
}

// Get string representation of Building
std::string Building::toString() const {
    std::stringstream ss;
    ss << "Building { ";
    ss << "buildingType: ";
    ss << buildingTypeToString(buildingType);
    ss << ", ";
    ss << "health: ";
    ss << health;
    ss << ", ";
    ss << "workDone: ";
    ss << workDone;
    ss << ", ";
    ss << "lastTickTasksDone: ";
    ss << lastTickTasksDone;
    ss << " }";
    return ss.str();
}

bool Building::operator ==(const Building& other) const {
    return buildingType == other.buildingType && health == other.health && workDone == other.workDone && lastTickTasksDone == other.lastTickTasksDone;
}

}

size_t std::hash<model::Building>::operator ()(const model::Building& value) const {
    size_t result = 0;
    result ^= std::hash<model::BuildingType>{}(value.buildingType) + 0x9e3779b9 + (result << 6) + (result >> 2);
    result ^= std::hash<int>{}(value.health) + 0x9e3779b9 + (result << 6) + (result >> 2);
    result ^= std::hash<int>{}(value.workDone) + 0x9e3779b9 + (result << 6) + (result >> 2);
    result ^= std::hash<int>{}(value.lastTickTasksDone) + 0x9e3779b9 + (result << 6) + (result >> 2);
    return result;
}