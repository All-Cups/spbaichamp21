import { Stream } from "../stream";

/**
 * Player (game participant)
 */
export class Player {
    /**
     * Current score points
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