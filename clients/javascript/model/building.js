const BuildingType = require.main.require('./model/building-type');
/**
 * A building
 */
class Building {
    /**
     * Building's type
     */
    buildingType;
    /**
     * Current health
     */
    health;
    /**
     * Amount of work done for current task
     */
    workDone;
    /**
     * Number of tasks finished since last tick
     */
    lastTickTasksDone;

    constructor(buildingType, health, workDone, lastTickTasksDone) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
        this.lastTickTasksDone = lastTickTasksDone;
    }

    /**
     * Read Building from input stream
     */
    static async readFrom(stream) {
        let buildingType;
        buildingType = await BuildingType.readFrom(stream);
        let health;
        health = await stream.readInt();
        let workDone;
        workDone = await stream.readInt();
        let lastTickTasksDone;
        lastTickTasksDone = await stream.readInt();
        return new Building(buildingType, health, workDone, lastTickTasksDone);
    }

    /**
     * Write Building to output stream
     */
    async writeTo(stream) {
        let buildingType = this.buildingType;
        await buildingType.writeTo(stream);
        let health = this.health;
        await stream.writeInt(health);
        let workDone = this.workDone;
        await stream.writeInt(workDone);
        let lastTickTasksDone = this.lastTickTasksDone;
        await stream.writeInt(lastTickTasksDone);
    }
}
module.exports = Building