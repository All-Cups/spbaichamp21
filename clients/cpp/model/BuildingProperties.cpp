#include "BuildingProperties.hpp"

namespace model {

BuildingProperties::BuildingProperties(std::unordered_map<model::Resource, int> buildResources, int maxHealth, int maxWorkers, std::unordered_map<model::Resource, int> workResources, bool produceWorker, std::optional<model::Resource> produceResource, int produceAmount, int produceScore, bool harvest, int workAmount) : buildResources(buildResources), maxHealth(maxHealth), maxWorkers(maxWorkers), workResources(workResources), produceWorker(produceWorker), produceResource(produceResource), produceAmount(produceAmount), produceScore(produceScore), harvest(harvest), workAmount(workAmount) { }

// Read BuildingProperties from input stream
BuildingProperties BuildingProperties::readFrom(InputStream& stream) {
    size_t buildResourcesSize = stream.readInt();
    std::unordered_map<model::Resource, int> buildResources = std::unordered_map<model::Resource, int>();
    buildResources.reserve(buildResourcesSize);
    for (size_t buildResourcesIndex = 0; buildResourcesIndex < buildResourcesSize; buildResourcesIndex++) {
        model::Resource buildResourcesKey = readResource(stream);
        int buildResourcesValue = stream.readInt();
        buildResources.emplace(std::make_pair(buildResourcesKey, buildResourcesValue));
    }
    int maxHealth = stream.readInt();
    int maxWorkers = stream.readInt();
    size_t workResourcesSize = stream.readInt();
    std::unordered_map<model::Resource, int> workResources = std::unordered_map<model::Resource, int>();
    workResources.reserve(workResourcesSize);
    for (size_t workResourcesIndex = 0; workResourcesIndex < workResourcesSize; workResourcesIndex++) {
        model::Resource workResourcesKey = readResource(stream);
        int workResourcesValue = stream.readInt();
        workResources.emplace(std::make_pair(workResourcesKey, workResourcesValue));
    }
    bool produceWorker = stream.readBool();
    std::optional<model::Resource> produceResource = std::optional<model::Resource>();
    if (stream.readBool()) {
        model::Resource produceResourceValue = readResource(stream);
        produceResource.emplace(produceResourceValue);
    }
    int produceAmount = stream.readInt();
    int produceScore = stream.readInt();
    bool harvest = stream.readBool();
    int workAmount = stream.readInt();
    return BuildingProperties(buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount);
}

// Write BuildingProperties to output stream
void BuildingProperties::writeTo(OutputStream& stream) const {
    stream.write((int)(buildResources.size()));
    for (const auto& buildResourcesEntry : buildResources) {
        const model::Resource& buildResourcesKey = buildResourcesEntry.first;
        const int& buildResourcesValue = buildResourcesEntry.second;
        stream.write((int)(buildResourcesKey));
        stream.write(buildResourcesValue);
    }
    stream.write(maxHealth);
    stream.write(maxWorkers);
    stream.write((int)(workResources.size()));
    for (const auto& workResourcesEntry : workResources) {
        const model::Resource& workResourcesKey = workResourcesEntry.first;
        const int& workResourcesValue = workResourcesEntry.second;
        stream.write((int)(workResourcesKey));
        stream.write(workResourcesValue);
    }
    stream.write(produceWorker);
    if (produceResource) {
        stream.write(true);
        const model::Resource& produceResourceValue = *produceResource;
        stream.write((int)(produceResourceValue));
    } else {
        stream.write(false);
    }
    stream.write(produceAmount);
    stream.write(produceScore);
    stream.write(harvest);
    stream.write(workAmount);
}

// Get string representation of BuildingProperties
std::string BuildingProperties::toString() const {
    std::stringstream ss;
    ss << "BuildingProperties { ";
    ss << "buildResources: ";
    ss << "{ ";
    size_t buildResourcesIndex = 0;
    for (const auto& buildResourcesEntry : buildResources) {
        if (buildResourcesIndex != 0) {
            ss << ", ";
        }
        const model::Resource& buildResourcesKey = buildResourcesEntry.first;
        const int& buildResourcesValue = buildResourcesEntry.second;
        ss << resourceToString(buildResourcesKey);
        ss << ": ";
        ss << buildResourcesValue;
        buildResourcesIndex++;
    }
    ss << " }";
    ss << ", ";
    ss << "maxHealth: ";
    ss << maxHealth;
    ss << ", ";
    ss << "maxWorkers: ";
    ss << maxWorkers;
    ss << ", ";
    ss << "workResources: ";
    ss << "{ ";
    size_t workResourcesIndex = 0;
    for (const auto& workResourcesEntry : workResources) {
        if (workResourcesIndex != 0) {
            ss << ", ";
        }
        const model::Resource& workResourcesKey = workResourcesEntry.first;
        const int& workResourcesValue = workResourcesEntry.second;
        ss << resourceToString(workResourcesKey);
        ss << ": ";
        ss << workResourcesValue;
        workResourcesIndex++;
    }
    ss << " }";
    ss << ", ";
    ss << "produceWorker: ";
    ss << produceWorker;
    ss << ", ";
    ss << "produceResource: ";
    if (produceResource) {
        const model::Resource& produceResourceValue = *produceResource;
        ss << resourceToString(produceResourceValue);
    } else {
        ss << "none";
    }
    ss << ", ";
    ss << "produceAmount: ";
    ss << produceAmount;
    ss << ", ";
    ss << "produceScore: ";
    ss << produceScore;
    ss << ", ";
    ss << "harvest: ";
    ss << harvest;
    ss << ", ";
    ss << "workAmount: ";
    ss << workAmount;
    ss << " }";
    return ss.str();
}

}