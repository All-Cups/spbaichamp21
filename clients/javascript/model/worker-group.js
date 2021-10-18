/**
 * Group of workers on a planet
 */
class WorkerGroup {
    /**
     * Index of player controlling the workers
     */
    playerIndex;
    /**
     * Number of workers in the group
     */
    number;

    constructor(playerIndex, number) {
        this.playerIndex = playerIndex;
        this.number = number;
    }

    /**
     * Read WorkerGroup from input stream
     */
    static async readFrom(stream) {
        let playerIndex;
        playerIndex = await stream.readInt();
        let number;
        number = await stream.readInt();
        return new WorkerGroup(playerIndex, number);
    }

    /**
     * Write WorkerGroup to output stream
     */
    async writeTo(stream) {
        let playerIndex = this.playerIndex;
        await stream.writeInt(playerIndex);
        let number = this.number;
        await stream.writeInt(number);
    }
}
module.exports = WorkerGroup