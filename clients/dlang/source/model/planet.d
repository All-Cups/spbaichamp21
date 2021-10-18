module model.planet;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building;
import model.resource;
import model.worker_group;

/// TODO - Document
struct Planet {
    /// TODO - Document
    int id;
    /// TODO - Document
    int x;
    /// TODO - Document
    int y;
    /// TODO - Document
    Nullable!(model.Resource) harvestableResource;
    /// TODO - Document
    model.WorkerGroup[] workerGroups;
    /// TODO - Document
    int[model.Resource] resources;
    /// TODO - Document
    Nullable!(model.Building) building;

    this(int id, int x, int y, Nullable!(model.Resource) harvestableResource, model.WorkerGroup[] workerGroups, int[model.Resource] resources, Nullable!(model.Building) building) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.harvestableResource = harvestableResource;
        this.workerGroups = workerGroups;
        this.resources = resources;
        this.building = building;
    }

    /// Read Planet from reader
    static Planet readFrom(Stream reader) {
        int id;
        id = reader.readInt();
        int x;
        x = reader.readInt();
        int y;
        y = reader.readInt();
        Nullable!(model.Resource) harvestableResource;
        if (reader.readBool()) {
            harvestableResource = readResource(reader);
        } else {
            harvestableResource.nullify();
        }
        model.WorkerGroup[] workerGroups;
        workerGroups = new model.WorkerGroup[reader.readInt()];
        for (int workerGroupsIndex = 0; workerGroupsIndex < workerGroups.length; workerGroupsIndex++) {
            model.WorkerGroup workerGroupsKey;
            workerGroupsKey = model.WorkerGroup.readFrom(reader);
            workerGroups[workerGroupsIndex] = workerGroupsKey;
        }
        int[model.Resource] resources;
        int resourcesSize = reader.readInt();
        resources.clear();
        for (int resourcesIndex = 0; resourcesIndex < resourcesSize; resourcesIndex++) {
            model.Resource resourcesKey;
            int resourcesValue;
            resourcesKey = readResource(reader);
            resourcesValue = reader.readInt();
            resources[resourcesKey] = resourcesValue;
        }
        Nullable!(model.Building) building;
        if (reader.readBool()) {
            building = model.Building.readFrom(reader);
        } else {
            building.nullify();
        }
        return Planet(id, x, y, harvestableResource, workerGroups, resources, building);
    }

    /// Write Planet to writer
    void writeTo(Stream writer) const {
        writer.write(id);
        writer.write(x);
        writer.write(y);
        if (harvestableResource.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto harvestableResourceValue = harvestableResource.get;
            writer.write(cast(int)(harvestableResourceValue));
        }
        writer.write(cast(int)(workerGroups.length));
        foreach (workerGroupsElement; workerGroups) {
            workerGroupsElement.writeTo(writer);
        }
        writer.write(cast(int)(resources.length));
        foreach (resourcesKey, resourcesValue; resources) {
            writer.write(cast(int)(resourcesKey));
            writer.write(resourcesValue);
        }
        if (building.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto buildingValue = building.get;
            buildingValue.writeTo(writer);
        }
    }
}