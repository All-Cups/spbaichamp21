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

// TODO - Document
class BuildingProperties {
public:
    // TODO - Document
    std::unordered_map<model::Resource, int> buildResources;
    // TODO - Document
    int maxHealth;
    // TODO - Document
    int maxWorkers;
    // TODO - Document
    std::unordered_map<model::Resource, int> workResources;
    // TODO - Document
    bool produceWorker;
    // TODO - Document
    std::optional<model::Resource> produceResource;
    // TODO - Document
    int produceAmount;
    // TODO - Document
    int produceScore;
    // TODO - Document
    bool harvest;
    // TODO - Document
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