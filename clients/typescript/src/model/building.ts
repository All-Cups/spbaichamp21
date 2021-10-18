import { BuildingType } from "./building-type";
import { Stream } from "../stream";

/**
 * A building
 */
export class Building {
    /**
     * Building's type
     */
    buildingType: BuildingType
    /**
     * Current health
     */
    health: number
    /**
     * Amount of work done for current task
     */
    workDone: number
    /**
     * Number of tasks finished since last tick
     */
    lastTickTasksDone: number

    constructor(buildingType: BuildingType, health: number, workDone: number, lastTickTasksDone: number) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
        this.lastTickTasksDone = lastTickTasksDone;
    }

    /**
     * Read Building from input stream
     */
    static async readFrom(stream: Stream): Promise<Building> {
        let buildingType;
        buildingType = await BuildingType.readFrom(stream);
        let health;
        health = await stream.readInt();
        let workDone;
        workDone = await stream.readInt();
        let lastTickTasksDone;
        lastTickTasksDone = await stream.readInt();
        return new Building(buildingType, health, workDone, lastTickTasksDone)
    }

    /**
     * Write Building to output stream
     */
    async writeTo(stream: Stream) {
        let buildingType = this.buildingType;
        await buildingType.writeTo(stream);
        let health = this.health;
        await stream.writeInt(health);
        let workDone = this.workDone;
        await stream.writeInt(workDone);
        let lastTickTasksDone = this.lastTickTasksDone;
        await stream.writeInt(lastTickTasksDone);
    }
}