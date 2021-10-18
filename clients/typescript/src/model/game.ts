import { BuildingProperties } from "./building-properties";
import { BuildingType } from "./building-type";
import { FlyingWorkerGroup } from "./flying-worker-group";
import { Planet } from "./planet";
import { Player } from "./player";
import { Stream } from "../stream";

/**
 * Current game's state
 */
export class Game {
    /**
     * Your player's index
     */
    myIndex: number
    /**
     * Current tick number
     */
    currentTick: number
    /**
     * Max number of ticks in the game
     */
    maxTickCount: number
    /**
     * List of players
     */
    players: Array<Player>
    /**
     * List of planets
     */
    planets: Array<Planet>
    /**
     * List of flying worker groups
     */
    flyingWorkerGroups: Array<FlyingWorkerGroup>
    /**
     * Max number of flying worker groups for one player
     */
    maxFlyingWorkerGroups: number
    /**
     * Max distance of direct travel between planets
     */
    maxTravelDistance: number
    /**
     * Additional distance of direct travel between planets for player with Logistics specialty
     */
    logisticsUpgrade: number
    /**
     * Additional work done by player with Production specialty (in percent)
     */
    productionUpgrade: number
    /**
     * Additional strength workers for player with Combat specialty (in percent)
     */
    combatUpgrade: number
    /**
     * Max number of workers performing building on one planet
     */
    maxBuilders: number
    /**
     * Properties of every building type
     */
    buildingProperties: Map<BuildingType, BuildingProperties>
    /**
     * Whether choosing specialties is allowed
     */
    specialtiesAllowed: boolean
    /**
     * View distance
     */
    viewDistance: number | null

    constructor(myIndex: number, currentTick: number, maxTickCount: number, players: Array<Player>, planets: Array<Planet>, flyingWorkerGroups: Array<FlyingWorkerGroup>, maxFlyingWorkerGroups: number, maxTravelDistance: number, logisticsUpgrade: number, productionUpgrade: number, combatUpgrade: number, maxBuilders: number, buildingProperties: Map<BuildingType, BuildingProperties>, specialtiesAllowed: boolean, viewDistance: number | null) {
        this.myIndex = myIndex;
        this.currentTick = currentTick;
        this.maxTickCount = maxTickCount;
        this.players = players;
        this.planets = planets;
        this.flyingWorkerGroups = flyingWorkerGroups;
        this.maxFlyingWorkerGroups = maxFlyingWorkerGroups;
        this.maxTravelDistance = maxTravelDistance;
        this.logisticsUpgrade = logisticsUpgrade;
        this.productionUpgrade = productionUpgrade;
        this.combatUpgrade = combatUpgrade;
        this.maxBuilders = maxBuilders;
        this.buildingProperties = buildingProperties;
        this.specialtiesAllowed = specialtiesAllowed;
        this.viewDistance = viewDistance;
    }

    /**
     * Read Game from input stream
     */
    static async readFrom(stream: Stream): Promise<Game> {
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
        let logisticsUpgrade;
        logisticsUpgrade = await stream.readInt();
        let productionUpgrade;
        productionUpgrade = await stream.readInt();
        let combatUpgrade;
        combatUpgrade = await stream.readInt();
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
        let specialtiesAllowed;
        specialtiesAllowed = await stream.readBool();
        let viewDistance;
        if (await stream.readBool()) {
            viewDistance = await stream.readInt();
        } else {
            viewDistance = null;
        }
        return new Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, logisticsUpgrade, productionUpgrade, combatUpgrade, maxBuilders, buildingProperties, specialtiesAllowed, viewDistance)
    }

    /**
     * Write Game to output stream
     */
    async writeTo(stream: Stream) {
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
        let logisticsUpgrade = this.logisticsUpgrade;
        await stream.writeInt(logisticsUpgrade);
        let productionUpgrade = this.productionUpgrade;
        await stream.writeInt(productionUpgrade);
        let combatUpgrade = this.combatUpgrade;
        await stream.writeInt(combatUpgrade);
        let maxBuilders = this.maxBuilders;
        await stream.writeInt(maxBuilders);
        let buildingProperties = this.buildingProperties;
        await stream.writeInt(buildingProperties.size);
        for (let [buildingPropertiesKey, buildingPropertiesValue] of buildingProperties) {
            await buildingPropertiesKey.writeTo(stream);
            await buildingPropertiesValue.writeTo(stream);
        }
        let specialtiesAllowed = this.specialtiesAllowed;
        await stream.writeBool(specialtiesAllowed);
        let viewDistance = this.viewDistance;
        if (viewDistance === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await stream.writeInt(viewDistance);
        }
    }
}