import { BuildingType } from "./building-type";
import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class Building {
    /**
     * TODO - Document
     */
    buildingType: BuildingType
    /**
     * TODO - Document
     */
    health: number
    /**
     * TODO - Document
     */
    workDone: number

    constructor(buildingType: BuildingType, health: number, workDone: number) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
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
        return new Building(buildingType, health, workDone)
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
    }
}