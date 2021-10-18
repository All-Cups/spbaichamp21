import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class DebugData {

    constructor() {
    }

    /**
     * Read DebugData from input stream
     */
    static async readFrom(stream: Stream): Promise<DebugData> {
        return new DebugData()
    }

    /**
     * Write DebugData to output stream
     */
    async writeTo(stream: Stream) {
    }
}