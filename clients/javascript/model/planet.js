const Building = require.main.require('./model/building');
const Resource = require.main.require('./model/resource');
const WorkerGroup = require.main.require('./model/worker-group');
/**
 * A planet
 */
class Planet {
    /**
     * Unique identifier of the planet
     */
    id;
    /**
     * X coordinate
     */
    x;
    /**
     * Y coordinate
     */
    y;
    /**
     * Resource that can be harvested on the planet
     */
    harvestableResource;
    /**
     * List of worker groups
     */
    workerGroups;
    /**
     * Resources stored on the planet
     */
    resources;
    /**
     * Building on the planet
     */
    building;

    constructor(id, x, y, harvestableResource, workerGroups, resources, building) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.harvestableResource = harvestableResource;
        this.workerGroups = workerGroups;
        this.resources = resources;
        this.building = building;
    }

    /**
     * Read Planet from input stream
     */
    static async readFrom(stream) {
        let id;
        id = await stream.readInt();
        let x;
        x = await stream.readInt();
        let y;
        y = await stream.readInt();
        let harvestableResource;
        if (await stream.readBool()) {
            harvestableResource = await Resource.readFrom(stream);
        } else {
            harvestableResource = null;
        }
        let workerGroups;
        workerGroups = [];
        for (let workerGroupsCount = await stream.readInt(); workerGroupsCount > 0; workerGroupsCount--) {
            let workerGroupsElement;
            workerGroupsElement = await WorkerGroup.readFrom(stream);
            workerGroups.push(workerGroupsElement);
        }
        let resources;
        resources = new Map();
        for (let resourcesCount = await stream.readInt(); resourcesCount > 0; resourcesCount--) {
            let resourcesKey;
            let resourcesValue;
            resourcesKey = await Resource.readFrom(stream);
            resourcesValue = await stream.readInt();
            resources.set(resourcesKey, resourcesValue)
        }
        let building;
        if (await stream.readBool()) {
            building = await Building.readFrom(stream);
        } else {
            building = null;
        }
        return new Planet(id, x, y, harvestableResource, workerGroups, resources, building);
    }

    /**
     * Write Planet to output stream
     */
    async writeTo(stream) {
        let id = this.id;
        await stream.writeInt(id);
        let x = this.x;
        await stream.writeInt(x);
        let y = this.y;
        await stream.writeInt(y);
        let harvestableResource = this.harvestableResource;
        if (harvestableResource === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await harvestableResource.writeTo(stream);
        }
        let workerGroups = this.workerGroups;
        await stream.writeInt(workerGroups.length);
        for (let workerGroupsElement of workerGroups) {
            await workerGroupsElement.writeTo(stream);
        }
        let resources = this.resources;
        await stream.writeInt(resources.size);
        for (let [resourcesKey, resourcesValue] of resources) {
            await resourcesKey.writeTo(stream);
            await stream.writeInt(resourcesValue);
        }
        let building = this.building;
        if (building === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await building.writeTo(stream);
        }
    }
}
module.exports = Planet