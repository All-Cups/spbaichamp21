import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class WorkerGroup {
    /**
     * TODO - Document
     */
    playerIndex: number
    /**
     * TODO - Document
     */
    number: number

    constructor(playerIndex: number, number: number) {
        this.playerIndex = playerIndex;
        this.number = number;
    }

    /**
     * Read WorkerGroup from input stream
     */
    static async readFrom(stream: Stream): Promise<WorkerGroup> {
        let playerIndex;
        playerIndex = await stream.readInt();
        let number;
        number = await stream.readInt();
        return new WorkerGroup(playerIndex, number)
    }

    /**
     * Write WorkerGroup to output stream
     */
    async writeTo(stream: Stream) {
        let playerIndex = this.playerIndex;
        await stream.writeInt(playerIndex);
        let number = this.number;
        await stream.writeInt(number);
    }
}