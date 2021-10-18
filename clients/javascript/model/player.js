const Specialty = require.main.require('./model/specialty');
/**
 * Player (game participant)
 */
class Player {
    /**
     * Team index
     */
    teamIndex;
    /**
     * Current score points
     */
    score;
    /**
     * Player's specialty
     */
    specialty;

    constructor(teamIndex, score, specialty) {
        this.teamIndex = teamIndex;
        this.score = score;
        this.specialty = specialty;
    }

    /**
     * Read Player from input stream
     */
    static async readFrom(stream) {
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
        return new Player(teamIndex, score, specialty);
    }

    /**
     * Write Player to output stream
     */
    async writeTo(stream) {
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
module.exports = Player