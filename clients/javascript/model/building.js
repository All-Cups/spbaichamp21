const BuildingType = require.main.require('./model/building-type');
/**
 * TODO - Document
 */
class Building {
    /**
     * TODO - Document
     */
    buildingType;
    /**
     * TODO - Document
     */
    health;
    /**
     * TODO - Document
     */
    workDone;

    constructor(buildingType, health, workDone) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
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
        return new Building(buildingType, health, workDone);
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
    }
}
module.exports = Building