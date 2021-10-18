const BuildingType = require.main.require('./model/building-type');
/**
 * Building order
 */
class BuildingAction {
    /**
     * Id of the planet where the action needs to be performed
     */
    planet;
    /**
     * Type of a building to build. If absent, current building will be destroyed
     */
    buildingType;

    constructor(planet, buildingType) {
        this.planet = planet;
        this.buildingType = buildingType;
    }

    /**
     * Read BuildingAction from input stream
     */
    static async readFrom(stream) {
        let planet;
        planet = await stream.readInt();
        let buildingType;
        if (await stream.readBool()) {
            buildingType = await BuildingType.readFrom(stream);
        } else {
            buildingType = null;
        }
        return new BuildingAction(planet, buildingType);
    }

    /**
     * Write BuildingAction to output stream
     */
    async writeTo(stream) {
        let planet = this.planet;
        await stream.writeInt(planet);
        let buildingType = this.buildingType;
        if (buildingType === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await buildingType.writeTo(stream);
        }
    }
}
module.exports = BuildingAction