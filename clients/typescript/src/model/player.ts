import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class Player {
    /**
     * TODO - Document
     */
    score: number

    constructor(score: number) {
        this.score = score;
    }

    /**
     * Read Player from input stream
     */
    static async readFrom(stream: Stream): Promise<Player> {
        let score;
        score = await stream.readInt();
        return new Player(score)
    }

    /**
     * Write Player to output stream
     */
    async writeTo(stream: Stream) {
        let score = this.score;
        await stream.writeInt(score);
    }
}