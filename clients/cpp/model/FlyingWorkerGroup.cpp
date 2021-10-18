#include "FlyingWorkerGroup.hpp"

namespace model {

FlyingWorkerGroup::FlyingWorkerGroup(int playerIndex, int number, int departureTick, int departurePlanet, int nextPlanetArrivalTick, int nextPlanet, int targetPlanet, std::optional<model::Resource> resource) : playerIndex(playerIndex), number(number), departureTick(departureTick), departurePlanet(departurePlanet), nextPlanetArrivalTick(nextPlanetArrivalTick), nextPlanet(nextPlanet), targetPlanet(targetPlanet), resource(resource) { }

// Read FlyingWorkerGroup from input stream
FlyingWorkerGroup FlyingWorkerGroup::readFrom(InputStream& stream) {
    int playerIndex = stream.readInt();
    int number = stream.readInt();
    int departureTick = stream.readInt();
    int departurePlanet = stream.readInt();
    int nextPlanetArrivalTick = stream.readInt();
    int nextPlanet = stream.readInt();
    int targetPlanet = stream.readInt();
    std::optional<model::Resource> resource = std::optional<model::Resource>();
    if (stream.readBool()) {
        model::Resource resourceValue = readResource(stream);
        resource.emplace(resourceValue);
    }
    return FlyingWorkerGroup(playerIndex, number, departureTick, departurePlanet, nextPlanetArrivalTick, nextPlanet, targetPlanet, resource);
}

// Write FlyingWorkerGroup to output stream
void FlyingWorkerGroup::writeTo(OutputStream& stream) const {
    stream.write(playerIndex);
    stream.write(number);
    stream.write(departureTick);
    stream.write(departurePlanet);
    stream.write(nextPlanetArrivalTick);
    stream.write(nextPlanet);
    stream.write(targetPlanet);
    if (resource) {
        stream.write(true);
        const model::Resource& resourceValue = *resource;
        stream.write((int)(resourceValue));
    } else {
        stream.write(false);
    }
}

// Get string representation of FlyingWorkerGroup
std::string FlyingWorkerGroup::toString() const {
    std::stringstream ss;
    ss << "FlyingWorkerGroup { ";
    ss << "playerIndex: ";
    ss << playerIndex;
    ss << ", ";
    ss << "number: ";
    ss << number;
    ss << ", ";
    ss << "departureTick: ";
    ss << departureTick;
    ss << ", ";
    ss << "departurePlanet: ";
    ss << departurePlanet;
    ss << ", ";
    ss << "nextPlanetArrivalTick: ";
    ss << nextPlanetArrivalTick;
    ss << ", ";
    ss << "nextPlanet: ";
    ss << nextPlanet;
    ss << ", ";
    ss << "targetPlanet: ";
    ss << targetPlanet;
    ss << ", ";
    ss << "resource: ";
    if (resource) {
        const model::Resource& resourceValue = *resource;
        ss << resourceToString(resourceValue);
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}