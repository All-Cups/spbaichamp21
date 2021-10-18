const Resource = require.main.require('./model/resource');
/**
 * Movement order
 */
class MoveAction {
    /**
     * Id of the planet where workers need to be sent from
     */
    startPlanet;
    /**
     * Id of the target planet
     */
    targetPlanet;
    /**
     * Number of workers to send
     */
    workerNumber;
    /**
     * Resource workers should carry
     */
    takeResource;

    constructor(startPlanet, targetPlanet, workerNumber, takeResource) {
        this.startPlanet = startPlanet;
        this.targetPlanet = targetPlanet;
        this.workerNumber = workerNumber;
        this.takeResource = takeResource;
    }

    /**
     * Read MoveAction from input stream
     */
    static async readFrom(stream) {
        let startPlanet;
        startPlanet = await stream.readInt();
        let targetPlanet;
        targetPlanet = await stream.readInt();
        let workerNumber;
        workerNumber = await stream.readInt();
        let takeResource;
        if (await stream.readBool()) {
            takeResource = await Resource.readFrom(stream);
        } else {
            takeResource = null;
        }
        return new MoveAction(startPlanet, targetPlanet, workerNumber, takeResource);
    }

    /**
     * Write MoveAction to output stream
     */
    async writeTo(stream) {
        let startPlanet = this.startPlanet;
        await stream.writeInt(startPlanet);
        let targetPlanet = this.targetPlanet;
        await stream.writeInt(targetPlanet);
        let workerNumber = this.workerNumber;
        await stream.writeInt(workerNumber);
        let takeResource = this.takeResource;
        if (takeResource === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await takeResource.writeTo(stream);
        }
    }
}
module.exports = MoveAction