const BuildingType = require.main.require('./model/building-type');
const Resource = require.main.require('./model/resource');
/**
 * Building properties
 */
class BuildingProperties {
    /**
     * Building type that this building can be upgraded from
     */
    baseBuilding;
    /**
     * Resources required for building
     */
    buildResources;
    /**
     * Max health points of the building
     */
    maxHealth;
    /**
     * Max number of workers in the building
     */
    maxWorkers;
    /**
     * Resources required to start another task
     */
    workResources;
    /**
     * Whether performing a task spawn new workers
     */
    produceWorker;
    /**
     * Resource produced when performing a task
     */
    produceResource;
    /**
     * Amount of resources/workers produced when performing one task
     */
    produceAmount;
    /**
     * Score points given for performing one task
     */
    produceScore;
    /**
     * Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
     */
    harvest;
    /**
     * Amount of work needed to finish one task
     */
    workAmount;

    constructor(baseBuilding, buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount) {
        this.baseBuilding = baseBuilding;
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

    /**
     * Read BuildingProperties from input stream
     */
    static async readFrom(stream) {
        let baseBuilding;
        if (await stream.readBool()) {
            baseBuilding = await BuildingType.readFrom(stream);
        } else {
            baseBuilding = null;
        }
        let buildResources;
        buildResources = new Map();
        for (let buildResourcesCount = await stream.readInt(); buildResourcesCount > 0; buildResourcesCount--) {
            let buildResourcesKey;
            let buildResourcesValue;
            buildResourcesKey = await Resource.readFrom(stream);
            buildResourcesValue = await stream.readInt();
            buildResources.set(buildResourcesKey, buildResourcesValue)
        }
        let maxHealth;
        maxHealth = await stream.readInt();
        let maxWorkers;
        maxWorkers = await stream.readInt();
        let workResources;
        workResources = new Map();
        for (let workResourcesCount = await stream.readInt(); workResourcesCount > 0; workResourcesCount--) {
            let workResourcesKey;
            let workResourcesValue;
            workResourcesKey = await Resource.readFrom(stream);
            workResourcesValue = await stream.readInt();
            workResources.set(workResourcesKey, workResourcesValue)
        }
        let produceWorker;
        produceWorker = await stream.readBool();
        let produceResource;
        if (await stream.readBool()) {
            produceResource = await Resource.readFrom(stream);
        } else {
            produceResource = null;
        }
        let produceAmount;
        produceAmount = await stream.readInt();
        let produceScore;
        produceScore = await stream.readInt();
        let harvest;
        harvest = await stream.readBool();
        let workAmount;
        workAmount = await stream.readInt();
        return new BuildingProperties(baseBuilding, buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount);
    }

    /**
     * Write BuildingProperties to output stream
     */
    async writeTo(stream) {
        let baseBuilding = this.baseBuilding;
        if (baseBuilding === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await baseBuilding.writeTo(stream);
        }
        let buildResources = this.buildResources;
        await stream.writeInt(buildResources.size);
        for (let [buildResourcesKey, buildResourcesValue] of buildResources) {
            await buildResourcesKey.writeTo(stream);
            await stream.writeInt(buildResourcesValue);
        }
        let maxHealth = this.maxHealth;
        await stream.writeInt(maxHealth);
        let maxWorkers = this.maxWorkers;
        await stream.writeInt(maxWorkers);
        let workResources = this.workResources;
        await stream.writeInt(workResources.size);
        for (let [workResourcesKey, workResourcesValue] of workResources) {
            await workResourcesKey.writeTo(stream);
            await stream.writeInt(workResourcesValue);
        }
        let produceWorker = this.produceWorker;
        await stream.writeBool(produceWorker);
        let produceResource = this.produceResource;
        if (produceResource === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await produceResource.writeTo(stream);
        }
        let produceAmount = this.produceAmount;
        await stream.writeInt(produceAmount);
        let produceScore = this.produceScore;
        await stream.writeInt(produceScore);
        let harvest = this.harvest;
        await stream.writeBool(harvest);
        let workAmount = this.workAmount;
        await stream.writeInt(workAmount);
    }
}
module.exports = BuildingProperties