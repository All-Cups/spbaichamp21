#include "Planet.hpp"

namespace model {

Planet::Planet(int id, int x, int y, std::optional<model::Resource> harvestableResource, std::vector<model::WorkerGroup> workerGroups, std::unordered_map<model::Resource, int> resources, std::optional<model::Building> building) : id(id), x(x), y(y), harvestableResource(harvestableResource), workerGroups(workerGroups), resources(resources), building(building) { }

// Read Planet from input stream
Planet Planet::readFrom(InputStream& stream) {
    int id = stream.readInt();
    int x = stream.readInt();
    int y = stream.readInt();
    std::optional<model::Resource> harvestableResource = std::optional<model::Resource>();
    if (stream.readBool()) {
        model::Resource harvestableResourceValue = readResource(stream);
        harvestableResource.emplace(harvestableResourceValue);
    }
    std::vector<model::WorkerGroup> workerGroups = std::vector<model::WorkerGroup>();
    size_t workerGroupsSize = stream.readInt();
    workerGroups.reserve(workerGroupsSize);
    for (size_t workerGroupsIndex = 0; workerGroupsIndex < workerGroupsSize; workerGroupsIndex++) {
        model::WorkerGroup workerGroupsElement = model::WorkerGroup::readFrom(stream);
        workerGroups.emplace_back(workerGroupsElement);
    }
    size_t resourcesSize = stream.readInt();
    std::unordered_map<model::Resource, int> resources = std::unordered_map<model::Resource, int>();
    resources.reserve(resourcesSize);
    for (size_t resourcesIndex = 0; resourcesIndex < resourcesSize; resourcesIndex++) {
        model::Resource resourcesKey = readResource(stream);
        int resourcesValue = stream.readInt();
        resources.emplace(std::make_pair(resourcesKey, resourcesValue));
    }
    std::optional<model::Building> building = std::optional<model::Building>();
    if (stream.readBool()) {
        model::Building buildingValue = model::Building::readFrom(stream);
        building.emplace(buildingValue);
    }
    return Planet(id, x, y, harvestableResource, workerGroups, resources, building);
}

// Write Planet to output stream
void Planet::writeTo(OutputStream& stream) const {
    stream.write(id);
    stream.write(x);
    stream.write(y);
    if (harvestableResource) {
        stream.write(true);
        const model::Resource& harvestableResourceValue = *harvestableResource;
        stream.write((int)(harvestableResourceValue));
    } else {
        stream.write(false);
    }
    stream.write((int)(workerGroups.size()));
    for (const model::WorkerGroup& workerGroupsElement : workerGroups) {
        workerGroupsElement.writeTo(stream);
    }
    stream.write((int)(resources.size()));
    for (const auto& resourcesEntry : resources) {
        const model::Resource& resourcesKey = resourcesEntry.first;
        const int& resourcesValue = resourcesEntry.second;
        stream.write((int)(resourcesKey));
        stream.write(resourcesValue);
    }
    if (building) {
        stream.write(true);
        const model::Building& buildingValue = *building;
        buildingValue.writeTo(stream);
    } else {
        stream.write(false);
    }
}

// Get string representation of Planet
std::string Planet::toString() const {
    std::stringstream ss;
    ss << "Planet { ";
    ss << "id: ";
    ss << id;
    ss << ", ";
    ss << "x: ";
    ss << x;
    ss << ", ";
    ss << "y: ";
    ss << y;
    ss << ", ";
    ss << "harvestableResource: ";
    if (harvestableResource) {
        const model::Resource& harvestableResourceValue = *harvestableResource;
        ss << resourceToString(harvestableResourceValue);
    } else {
        ss << "none";
    }
    ss << ", ";
    ss << "workerGroups: ";
    ss << "[ ";
    for (size_t workerGroupsIndex = 0; workerGroupsIndex < workerGroups.size(); workerGroupsIndex++) {
        const model::WorkerGroup& workerGroupsElement = workerGroups[workerGroupsIndex];
        if (workerGroupsIndex != 0) {
            ss << ", ";
        }
        ss << workerGroupsElement.toString();
    }
    ss << " ]";
    ss << ", ";
    ss << "resources: ";
    ss << "{ ";
    size_t resourcesIndex = 0;
    for (const auto& resourcesEntry : resources) {
        if (resourcesIndex != 0) {
            ss << ", ";
        }
        const model::Resource& resourcesKey = resourcesEntry.first;
        const int& resourcesValue = resourcesEntry.second;
        ss << resourceToString(resourcesKey);
        ss << ": ";
        ss << resourcesValue;
        resourcesIndex++;
    }
    ss << " }";
    ss << ", ";
    ss << "building: ";
    if (building) {
        const model::Building& buildingValue = *building;
        ss << buildingValue.toString();
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}