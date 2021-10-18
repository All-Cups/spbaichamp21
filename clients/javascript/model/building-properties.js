const Resource = require.main.require('./model/resource');
/**
 * TODO - Document
 */
class BuildingProperties {
    /**
     * TODO - Document
     */
    buildResources;
    /**
     * TODO - Document
     */
    maxHealth;
    /**
     * TODO - Document
     */
    maxWorkers;
    /**
     * TODO - Document
     */
    workResources;
    /**
     * TODO - Document
     */
    produceWorker;
    /**
     * TODO - Document
     */
    produceResource;
    /**
     * TODO - Document
     */
    produceAmount;
    /**
     * TODO - Document
     */
    produceScore;
    /**
     * TODO - Document
     */
    harvest;
    /**
     * TODO - Document
     */
    workAmount;

    constructor(buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount) {
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
        return new BuildingProperties(buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount);
    }

    /**
     * Write BuildingProperties to output stream
     */
    async writeTo(stream) {
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