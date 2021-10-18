/**
 * TODO - Document
 */
class Player {
    /**
     * TODO - Document
     */
    score;

    constructor(score) {
        this.score = score;
    }

    /**
     * Read Player from input stream
     */
    static async readFrom(stream) {
        let score;
        score = await stream.readInt();
        return new Player(score);
    }

    /**
     * Write Player to output stream
     */
    async writeTo(stream) {
        let score = this.score;
        await stream.writeInt(score);
    }
}
module.exports = Player