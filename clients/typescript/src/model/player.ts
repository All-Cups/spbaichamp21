import { Specialty } from "./specialty";
import { Stream } from "../stream";

/**
 * Player (game participant)
 */
export class Player {
    /**
     * Team index
     */
    teamIndex: number
    /**
     * Current score points
     */
    score: number
    /**
     * Player's specialty
     */
    specialty: Specialty | null

    constructor(teamIndex: number, score: number, specialty: Specialty | null) {
        this.teamIndex = teamIndex;
        this.score = score;
        this.specialty = specialty;
    }

    /**
     * Read Player from input stream
     */
    static async readFrom(stream: Stream): Promise<Player> {
        let teamIndex;
        teamIndex = await stream.readInt();
        let score;
        score = await stream.readInt();
        let specialty;
        if (await stream.readBool()) {
            specialty = await Specialty.readFrom(stream);
        } else {
            specialty = null;
        }
        return new Player(teamIndex, score, specialty)
    }

    /**
     * Write Player to output stream
     */
    async writeTo(stream: Stream) {
        let teamIndex = this.teamIndex;
        await stream.writeInt(teamIndex);
        let score = this.score;
        await stream.writeInt(score);
        let specialty = this.specialty;
        if (specialty === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await specialty.writeTo(stream);
        }
    }
}