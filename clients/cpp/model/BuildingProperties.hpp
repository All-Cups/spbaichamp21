#ifndef __MODEL_BUILDING_PROPERTIES_HPP__
#define __MODEL_BUILDING_PROPERTIES_HPP__

#include "Stream.hpp"
#include "model/Resource.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>
#include <unordered_map>

namespace model {

// Building properties
class BuildingProperties {
public:
    // Resources required for building
    std::unordered_map<model::Resource, int> buildResources;
    // Max health points of the building
    int maxHealth;
    // Max number of workers in the building
    int maxWorkers;
    // Resources required to start another task
    std::unordered_map<model::Resource, int> workResources;
    // Whether performing a task spawn new workers
    bool produceWorker;
    // Resource produced when performing a task
    std::optional<model::Resource> produceResource;
    // Amount of resources/workers produced when performing one task
    int produceAmount;
    // Score points given for performing one task
    int produceScore;
    // Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
    bool harvest;
    // Amount of work needed to finish one task
    int workAmount;

    BuildingProperties(std::unordered_map<model::Resource, int> buildResources, int maxHealth, int maxWorkers, std::unordered_map<model::Resource, int> workResources, bool produceWorker, std::optional<model::Resource> produceResource, int produceAmount, int produceScore, bool harvest, int workAmount);

    // Read BuildingProperties from input stream
    static BuildingProperties readFrom(InputStream& stream);

    // Write BuildingProperties to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of BuildingProperties
    std::string toString() const;
};

}

#endif