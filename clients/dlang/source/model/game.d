module model.game;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_properties;
import model.building_type;
import model.flying_worker_group;
import model.planet;
import model.player;

/// Current game's state
struct Game {
    /// Your player's index
    int myIndex;
    /// Current tick number
    int currentTick;
    /// Max number of ticks in the game
    int maxTickCount;
    /// List of players
    model.Player[] players;
    /// List of planets
    model.Planet[] planets;
    /// List of flying worker groups
    model.FlyingWorkerGroup[] flyingWorkerGroups;
    /// Max number of flying worker groups for one player
    int maxFlyingWorkerGroups;
    /// Max distance of direct travel between planets
    int maxTravelDistance;
    /// Additional distance of direct travel between planets for player with Logistics specialty
    int logisticsUpgrade;
    /// Additional work done by player with Production specialty (in percent)
    int productionUpgrade;
    /// Additional strength workers for player with Combat specialty (in percent)
    int combatUpgrade;
    /// Max number of workers performing building on one planet
    int maxBuilders;
    /// Properties of every building type
    model.BuildingProperties[model.BuildingType] buildingProperties;
    /// Whether choosing specialties is allowed
    bool specialtiesAllowed;
    /// View distance
    Nullable!(int) viewDistance;

    this(int myIndex, int currentTick, int maxTickCount, model.Player[] players, model.Planet[] planets, model.FlyingWorkerGroup[] flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int logisticsUpgrade, int productionUpgrade, int combatUpgrade, int maxBuilders, model.BuildingProperties[model.BuildingType] buildingProperties, bool specialtiesAllowed, Nullable!(int) viewDistance) {
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

    /// Read Game from reader
    static Game readFrom(Stream reader) {
        int myIndex;
        myIndex = reader.readInt();
        int currentTick;
        currentTick = reader.readInt();
        int maxTickCount;
        maxTickCount = reader.readInt();
        model.Player[] players;
        players = new model.Player[reader.readInt()];
        for (int playersIndex = 0; playersIndex < players.length; playersIndex++) {
            model.Player playersKey;
            playersKey = model.Player.readFrom(reader);
            players[playersIndex] = playersKey;
        }
        model.Planet[] planets;
        planets = new model.Planet[reader.readInt()];
        for (int planetsIndex = 0; planetsIndex < planets.length; planetsIndex++) {
            model.Planet planetsKey;
            planetsKey = model.Planet.readFrom(reader);
            planets[planetsIndex] = planetsKey;
        }
        model.FlyingWorkerGroup[] flyingWorkerGroups;
        flyingWorkerGroups = new model.FlyingWorkerGroup[reader.readInt()];
        for (int flyingWorkerGroupsIndex = 0; flyingWorkerGroupsIndex < flyingWorkerGroups.length; flyingWorkerGroupsIndex++) {
            model.FlyingWorkerGroup flyingWorkerGroupsKey;
            flyingWorkerGroupsKey = model.FlyingWorkerGroup.readFrom(reader);
            flyingWorkerGroups[flyingWorkerGroupsIndex] = flyingWorkerGroupsKey;
        }
        int maxFlyingWorkerGroups;
        maxFlyingWorkerGroups = reader.readInt();
        int maxTravelDistance;
        maxTravelDistance = reader.readInt();
        int logisticsUpgrade;
        logisticsUpgrade = reader.readInt();
        int productionUpgrade;
        productionUpgrade = reader.readInt();
        int combatUpgrade;
        combatUpgrade = reader.readInt();
        int maxBuilders;
        maxBuilders = reader.readInt();
        model.BuildingProperties[model.BuildingType] buildingProperties;
        int buildingPropertiesSize = reader.readInt();
        buildingProperties.clear();
        for (int buildingPropertiesIndex = 0; buildingPropertiesIndex < buildingPropertiesSize; buildingPropertiesIndex++) {
            model.BuildingType buildingPropertiesKey;
            model.BuildingProperties buildingPropertiesValue;
            buildingPropertiesKey = readBuildingType(reader);
            buildingPropertiesValue = model.BuildingProperties.readFrom(reader);
            buildingProperties[buildingPropertiesKey] = buildingPropertiesValue;
        }
        bool specialtiesAllowed;
        specialtiesAllowed = reader.readBool();
        Nullable!(int) viewDistance;
        if (reader.readBool()) {
            viewDistance = reader.readInt();
        } else {
            viewDistance.nullify();
        }
        return Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, logisticsUpgrade, productionUpgrade, combatUpgrade, maxBuilders, buildingProperties, specialtiesAllowed, viewDistance);
    }

    /// Write Game to writer
    void writeTo(Stream writer) const {
        writer.write(myIndex);
        writer.write(currentTick);
        writer.write(maxTickCount);
        writer.write(cast(int)(players.length));
        foreach (playersElement; players) {
            playersElement.writeTo(writer);
        }
        writer.write(cast(int)(planets.length));
        foreach (planetsElement; planets) {
            planetsElement.writeTo(writer);
        }
        writer.write(cast(int)(flyingWorkerGroups.length));
        foreach (flyingWorkerGroupsElement; flyingWorkerGroups) {
            flyingWorkerGroupsElement.writeTo(writer);
        }
        writer.write(maxFlyingWorkerGroups);
        writer.write(maxTravelDistance);
        writer.write(logisticsUpgrade);
        writer.write(productionUpgrade);
        writer.write(combatUpgrade);
        writer.write(maxBuilders);
        writer.write(cast(int)(buildingProperties.length));
        foreach (buildingPropertiesKey, buildingPropertiesValue; buildingProperties) {
            writer.write(cast(int)(buildingPropertiesKey));
            buildingPropertiesValue.writeTo(writer);
        }
        writer.write(specialtiesAllowed);
        if (viewDistance.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto viewDistanceValue = viewDistance.get;
            writer.write(viewDistanceValue);
        }
    }
}