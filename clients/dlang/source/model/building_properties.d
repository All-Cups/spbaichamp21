module model.building_properties;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.resource;

/// Building properties
struct BuildingProperties {
    /// Resources required for building
    int[model.Resource] buildResources;
    /// Max health points of the building
    int maxHealth;
    /// Max number of workers in the building
    int maxWorkers;
    /// Resources required to start another task
    int[model.Resource] workResources;
    /// Whether performing a task spawn new workers
    bool produceWorker;
    /// Resource produced when performing a task
    Nullable!(model.Resource) produceResource;
    /// Amount of resources/workers produced when performing one task
    int produceAmount;
    /// Score points given for performing one task
    int produceScore;
    /// Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
    bool harvest;
    /// Amount of work needed to finish one task
    int workAmount;

    this(int[model.Resource] buildResources, int maxHealth, int maxWorkers, int[model.Resource] workResources, bool produceWorker, Nullable!(model.Resource) produceResource, int produceAmount, int produceScore, bool harvest, int workAmount) {
        this.buildResources = buildResources;
        this.maxHealth = maxHealth;
        this.maxWorkers = maxWorkers;
        this.workResources = workResources;
        this.produceWorker = produceWorker;
        this.produceResource = produceResource;
        this.produceAmount = produceAmount;
        this.produceScore = produceScore;
        this.harvest = harvest;
        this.workAmount = workAmount;
    }

    /// Read BuildingProperties from reader
    static BuildingProperties readFrom(Stream reader) {
        int[model.Resource] buildResources;
        int buildResourcesSize = reader.readInt();
        buildResources.clear();
        for (int buildResourcesIndex = 0; buildResourcesIndex < buildResourcesSize; buildResourcesIndex++) {
            model.Resource buildResourcesKey;
            int buildResourcesValue;
            buildResourcesKey = readResource(reader);
            buildResourcesValue = reader.readInt();
            buildResources[buildResourcesKey] = buildResourcesValue;
        }
        int maxHealth;
        maxHealth = reader.readInt();
        int maxWorkers;
        maxWorkers = reader.readInt();
        int[model.Resource] workResources;
        int workResourcesSize = reader.readInt();
        workResources.clear();
        for (int workResourcesIndex = 0; workResourcesIndex < workResourcesSize; workResourcesIndex++) {
            model.Resource workResourcesKey;
            int workResourcesValue;
            workResourcesKey = readResource(reader);
            workResourcesValue = reader.readInt();
            workResources[workResourcesKey] = workResourcesValue;
        }
        bool produceWorker;
        produceWorker = reader.readBool();
        Nullable!(model.Resource) produceResource;
        if (reader.readBool()) {
            produceResource = readResource(reader);
        } else {
            produceResource.nullify();
        }
        int produceAmount;
        produceAmount = reader.readInt();
        int produceScore;
        produceScore = reader.readInt();
        bool harvest;
        harvest = reader.readBool();
        int workAmount;
        workAmount = reader.readInt();
        return BuildingProperties(buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount);
    }

    /// Write BuildingProperties to writer
    void writeTo(Stream writer) const {
        writer.write(cast(int)(buildResources.length));
        foreach (buildResourcesKey, buildResourcesValue; buildResources) {
            writer.write(cast(int)(buildResourcesKey));
            writer.write(buildResourcesValue);
        }
        writer.write(maxHealth);
        writer.write(maxWorkers);
        writer.write(cast(int)(workResources.length));
        foreach (workResourcesKey, workResourcesValue; workResources) {
            writer.write(cast(int)(workResourcesKey));
            writer.write(workResourcesValue);
        }
        writer.write(produceWorker);
        if (produceResource.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto produceResourceValue = produceResource.get;
            writer.write(cast(int)(produceResourceValue));
        }
        writer.write(produceAmount);
        writer.write(produceScore);
        writer.write(harvest);
        writer.write(workAmount);
    }
}