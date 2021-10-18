#ifndef __MODEL_FLYING_WORKER_GROUP_HPP__
#define __MODEL_FLYING_WORKER_GROUP_HPP__

#include "Stream.hpp"
#include "model/Resource.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>

namespace model {

// TODO - Document
class FlyingWorkerGroup {
public:
    // TODO - Document
    int playerIndex;
    // TODO - Document
    int number;
    // TODO - Document
    int departureTick;
    // TODO - Document
    int departurePlanet;
    // TODO - Document
    int nextPlanetArrivalTick;
    // TODO - Document
    int nextPlanet;
    // TODO - Document
    int targetPlanet;
    // TODO - Document
    std::optional<model::Resource> resource;

    FlyingWorkerGroup(int playerIndex, int number, int departureTick, int departurePlanet, int nextPlanetArrivalTick, int nextPlanet, int targetPlanet, std::optional<model::Resource> resource);

    // Read FlyingWorkerGroup from input stream
    static FlyingWorkerGroup readFrom(InputStream& stream);

    // Write FlyingWorkerGroup to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of FlyingWorkerGroup
    std::string toString() const;
};

}

#endif