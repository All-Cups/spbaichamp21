const BuildingProperties = require.main.require('./model/building-properties');
const BuildingType = require.main.require('./model/building-type');
const FlyingWorkerGroup = require.main.require('./model/flying-worker-group');
const Planet = require.main.require('./model/planet');
const Player = require.main.require('./model/player');
/**
 * TODO - Document
 */
class Game {
    /**
     * TODO - Document
     */
    myIndex;
    /**
     * TODO - Document
     */
    currentTick;
    /**
     * TODO - Document
     */
    maxTickCount;
    /**
     * TODO - Document
     */
    players;
    /**
     * TODO - Document
     */
    planets;
    /**
     * TODO - Document
     */
    flyingWorkerGroups;
    /**
     * TODO - Document
     */
    maxFlyingWorkerGroups;
    /**
     * TODO - Document
     */
    maxTravelDistance;
    /**
     * TODO - Document
     */
    maxBuilders;
    /**
     * TODO - Document
     */
    buildingProperties;

    constructor(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, maxBuilders, buildingProperties) {
        this.myIndex = myIndex;
        this.currentTick = currentTick;
        this.maxTickCount = maxTickCount;
        this.players = players;
        this.planets = planets;
        this.flyingWorkerGroups = flyingWorkerGroups;
        this.maxFlyingWorkerGroups = maxFlyingWorkerGroups;
        this.maxTravelDistance = maxTravelDistance;
        this.maxBuilders = maxBuilders;
        this.buildingProperties = buildingProperties;
    }

    /**
     * Read Game from input stream
     */
    static async readFrom(stream) {
        let myIndex;
        myIndex = await stream.readInt();
        let currentTick;
        currentTick = await stream.readInt();
        let maxTickCount;
        maxTickCount = await stream.readInt();
        let players;
        players = [];
        for (let playersCount = await stream.readInt(); playersCount > 0; playersCount--) {
            let playersElement;
            playersElement = await Player.readFrom(stream);
            players.push(playersElement);
        }
        let planets;
        planets = [];
        for (let planetsCount = await stream.readInt(); planetsCount > 0; planetsCount--) {
            let planetsElement;
            planetsElement = await Planet.readFrom(stream);
            planets.push(planetsElement);
        }
        let flyingWorkerGroups;
        flyingWorkerGroups = [];
        for (let flyingWorkerGroupsCount = await stream.readInt(); flyingWorkerGroupsCount > 0; flyingWorkerGroupsCount--) {
            let flyingWorkerGroupsElement;
            flyingWorkerGroupsElement = await FlyingWorkerGroup.readFrom(stream);
            flyingWorkerGroups.push(flyingWorkerGroupsElement);
        }
        let maxFlyingWorkerGroups;
        maxFlyingWorkerGroups = await stream.readInt();
        let maxTravelDistance;
        maxTravelDistance = await stream.readInt();
        let maxBuilders;
        maxBuilders = await stream.readInt();
        let buildingProperties;
        buildingProperties = new Map();
        for (let buildingPropertiesCount = await stream.readInt(); buildingPropertiesCount > 0; buildingPropertiesCount--) {
            let buildingPropertiesKey;
            let buildingPropertiesValue;
            buildingPropertiesKey = await BuildingType.readFrom(stream);
            buildingPropertiesValue = await BuildingProperties.readFrom(stream);
            buildingProperties.set(buildingPropertiesKey, buildingPropertiesValue)
        }
        return new Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, maxBuilders, buildingProperties);
    }

    /**
     * Write Game to output stream
     */
    async writeTo(stream) {
        let myIndex = this.myIndex;
        await stream.writeInt(myIndex);
        let currentTick = this.currentTick;
        await stream.writeInt(currentTick);
        let maxTickCount = this.maxTickCount;
        await stream.writeInt(maxTickCount);
        let players = this.players;
        await stream.writeInt(players.length);
        for (let playersElement of players) {
            await playersElement.writeTo(stream);
        }
        let planets = this.planets;
        await stream.writeInt(planets.length);
        for (let planetsElement of planets) {
            await planetsElement.writeTo(stream);
        }
        let flyingWorkerGroups = this.flyingWorkerGroups;
        await stream.writeInt(flyingWorkerGroups.length);
        for (let flyingWorkerGroupsElement of flyingWorkerGroups) {
            await flyingWorkerGroupsElement.writeTo(stream);
        }
        let maxFlyingWorkerGroups = this.maxFlyingWorkerGroups;
        await stream.writeInt(maxFlyingWorkerGroups);
        let maxTravelDistance = this.maxTravelDistance;
        await stream.writeInt(maxTravelDistance);
        let maxBuilders = this.maxBuilders;
        await stream.writeInt(maxBuilders);
        let buildingProperties = this.buildingProperties;
        await stream.writeInt(buildingProperties.size);
        for (let [buildingPropertiesKey, buildingPropertiesValue] of buildingProperties) {
            await buildingPropertiesKey.writeTo(stream);
            await buildingPropertiesValue.writeTo(stream);
        }
    }
}
module.exports = Game