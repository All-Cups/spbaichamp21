#ifndef __MODEL_PLANET_HPP__
#define __MODEL_PLANET_HPP__

#include "Stream.hpp"
#include "model/Building.hpp"
#include "model/BuildingType.hpp"
#include "model/Resource.hpp"
#include "model/WorkerGroup.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

namespace model {

// TODO - Document
class Planet {
public:
    // TODO - Document
    int id;
    // TODO - Document
    int x;
    // TODO - Document
    int y;
    // TODO - Document
    std::optional<model::Resource> harvestableResource;
    // TODO - Document
    std::vector<model::WorkerGroup> workerGroups;
    // TODO - Document
    std::unordered_map<model::Resource, int> resources;
    // TODO - Document
    std::optional<model::Building> building;

    Planet(int id, int x, int y, std::optional<model::Resource> harvestableResource, std::vector<model::WorkerGroup> workerGroups, std::unordered_map<model::Resource, int> resources, std::optional<model::Building> building);

    // Read Planet from input stream
    static Planet readFrom(InputStream& stream);

    // Write Planet to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of Planet
    std::string toString() const;
};

}

#endif