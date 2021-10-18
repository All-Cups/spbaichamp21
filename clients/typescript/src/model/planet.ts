import { Building } from "./building";
import { Resource } from "./resource";
import { WorkerGroup } from "./worker-group";
import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class Planet {
    /**
     * TODO - Document
     */
    x: number
    /**
     * TODO - Document
     */
    y: number
    /**
     * TODO - Document
     */
    harvestableResource: Resource | null
    /**
     * TODO - Document
     */
    workerGroups: Array<WorkerGroup>
    /**
     * TODO - Document
     */
    resources: Map<Resource, number>
    /**
     * TODO - Document
     */
    building: Building | null

    constructor(x: number, y: number, harvestableResource: Resource | null, workerGroups: Array<WorkerGroup>, resources: Map<Resource, number>, building: Building | null) {
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
    static async readFrom(stream: Stream): Promise<Planet> {
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
        return new Planet(x, y, harvestableResource, workerGroups, resources, building)
    }

    /**
     * Write Planet to output stream
     */
    async writeTo(stream: Stream) {
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