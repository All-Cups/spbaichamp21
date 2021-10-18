import { Resource } from "./resource";
import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class MoveAction {
    /**
     * TODO - Document
     */
    startPlanet: number
    /**
     * TODO - Document
     */
    targetPlanet: number
    /**
     * TODO - Document
     */
    workerNumber: number
    /**
     * TODO - Document
     */
    takeResource: Resource | null

    constructor(startPlanet: number, targetPlanet: number, workerNumber: number, takeResource: Resource | null) {
        this.startPlanet = startPlanet;
        this.targetPlanet = targetPlanet;
        this.workerNumber = workerNumber;
        this.takeResource = takeResource;
    }

    /**
     * Read MoveAction from input stream
     */
    static async readFrom(stream: Stream): Promise<MoveAction> {
        let startPlanet;
        startPlanet = await stream.readInt();
        let targetPlanet;
        targetPlanet = await stream.readInt();
        let workerNumber;
        workerNumber = await stream.readInt();
        let takeResource;
        if (await stream.readBool()) {
            takeResource = await Resource.readFrom(stream);
        } else {
            takeResource = null;
        }
        return new MoveAction(startPlanet, targetPlanet, workerNumber, takeResource)
    }

    /**
     * Write MoveAction to output stream
     */
    async writeTo(stream: Stream) {
        let startPlanet = this.startPlanet;
        await stream.writeInt(startPlanet);
        let targetPlanet = this.targetPlanet;
        await stream.writeInt(targetPlanet);
        let workerNumber = this.workerNumber;
        await stream.writeInt(workerNumber);
        let takeResource = this.takeResource;
        if (takeResource === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await takeResource.writeTo(stream);
        }
    }
}