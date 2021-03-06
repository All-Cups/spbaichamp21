const Resource = require.main.require('./model/resource');
/**
 * A flying worker group
 */
class FlyingWorkerGroup {
    /**
     * Index of player controlling workers
     */
    playerIndex;
    /**
     * Number of workers in the group
     */
    number;
    /**
     * Tick when workers left previous planet on their path
     */
    departureTick;
    /**
     * Id of the previous planet on the path
     */
    departurePlanet;
    /**
     * Tick when workers will arrive to the next planet in their path
     */
    nextPlanetArrivalTick;
    /**
     * Id of the next planet in the path
     */
    nextPlanet;
    /**
     * Id of the target planet
     */
    targetPlanet;
    /**
     * Resource that workers are carrying
     */
    resource;

    constructor(playerIndex, number, departureTick, departurePlanet, nextPlanetArrivalTick, nextPlanet, targetPlanet, resource) {
        this.playerIndex = playerIndex;
        this.number = number;
        this.departureTick = departureTick;
        this.departurePlanet = departurePlanet;
        this.nextPlanetArrivalTick = nextPlanetArrivalTick;
        this.nextPlanet = nextPlanet;
        this.targetPlanet = targetPlanet;
        this.resource = resource;
    }

    /**
     * Read FlyingWorkerGroup from input stream
     */
    static async readFrom(stream) {
        let playerIndex;
        playerIndex = await stream.readInt();
        let number;
        number = await stream.readInt();
        let departureTick;
        departureTick = await stream.readInt();
        let departurePlanet;
        departurePlanet = await stream.readInt();
        let nextPlanetArrivalTick;
        nextPlanetArrivalTick = await stream.readInt();
        let nextPlanet;
        nextPlanet = await stream.readInt();
        let targetPlanet;
        targetPlanet = await stream.readInt();
        let resource;
        if (await stream.readBool()) {
            resource = await Resource.readFrom(stream);
        } else {
            resource = null;
        }
        return new FlyingWorkerGroup(playerIndex, number, departureTick, departurePlanet, nextPlanetArrivalTick, nextPlanet, targetPlanet, resource);
    }

    /**
     * Write FlyingWorkerGroup to output stream
     */
    async writeTo(stream) {
        let playerIndex = this.playerIndex;
        await stream.writeInt(playerIndex);
        let number = this.number;
        await stream.writeInt(number);
        let departureTick = this.departureTick;
        await stream.writeInt(departureTick);
        let departurePlanet = this.departurePlanet;
        await stream.writeInt(departurePlanet);
        let nextPlanetArrivalTick = this.nextPlanetArrivalTick;
        await stream.writeInt(nextPlanetArrivalTick);
        let nextPlanet = this.nextPlanet;
        await stream.writeInt(nextPlanet);
        let targetPlanet = this.targetPlanet;
        await stream.writeInt(targetPlanet);
        let resource = this.resource;
        if (resource === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await resource.writeTo(stream);
        }
    }
}
module.exports = FlyingWorkerGroup