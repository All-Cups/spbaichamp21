#ifndef __MODEL_FLYING_WORKER_GROUP_HPP__
#define __MODEL_FLYING_WORKER_GROUP_HPP__

#include "Stream.hpp"
#include "model/Resource.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>

namespace model {

// A flying worker group
class FlyingWorkerGroup {
public:
    // Index of player controlling workers
    int playerIndex;
    // Number of workers in the group
    int number;
    // Tick when workers left previous planet on their path
    int departureTick;
    // Id of the previous planet on the path
    int departurePlanet;
    // Tick when workers will arrive to the next planet in their path
    int nextPlanetArrivalTick;
    // Id of the next planet in the path
    int nextPlanet;
    // Id of the target planet
    int targetPlanet;
    // Resource that workers are carrying
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