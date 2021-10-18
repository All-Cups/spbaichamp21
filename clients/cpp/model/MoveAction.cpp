#include "MoveAction.hpp"

namespace model {

MoveAction::MoveAction(int startPlanet, int targetPlanet, int workerNumber, std::optional<model::Resource> takeResource) : startPlanet(startPlanet), targetPlanet(targetPlanet), workerNumber(workerNumber), takeResource(takeResource) { }

// Read MoveAction from input stream
MoveAction MoveAction::readFrom(InputStream& stream) {
    int startPlanet = stream.readInt();
    int targetPlanet = stream.readInt();
    int workerNumber = stream.readInt();
    std::optional<model::Resource> takeResource = std::optional<model::Resource>();
    if (stream.readBool()) {
        model::Resource takeResourceValue = readResource(stream);
        takeResource.emplace(takeResourceValue);
    }
    return MoveAction(startPlanet, targetPlanet, workerNumber, takeResource);
}

// Write MoveAction to output stream
void MoveAction::writeTo(OutputStream& stream) const {
    stream.write(startPlanet);
    stream.write(targetPlanet);
    stream.write(workerNumber);
    if (takeResource) {
        stream.write(true);
        const model::Resource& takeResourceValue = *takeResource;
        stream.write((int)(takeResourceValue));
    } else {
        stream.write(false);
    }
}

// Get string representation of MoveAction
std::string MoveAction::toString() const {
    std::stringstream ss;
    ss << "MoveAction { ";
    ss << "startPlanet: ";
    ss << startPlanet;
    ss << ", ";
    ss << "targetPlanet: ";
    ss << targetPlanet;
    ss << ", ";
    ss << "workerNumber: ";
    ss << workerNumber;
    ss << ", ";
    ss << "takeResource: ";
    if (takeResource) {
        const model::Resource& takeResourceValue = *takeResource;
        ss << resourceToString(takeResourceValue);
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}