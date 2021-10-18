module model.game;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.building_properties;
import model.building_type;
import model.flying_worker_group;
import model.planet;
import model.player;

/// TODO - Document
struct Game {
    /// TODO - Document
    int myIndex;
    /// TODO - Document
    int currentTick;
    /// TODO - Document
    int maxTickCount;
    /// TODO - Document
    model.Player[] players;
    /// TODO - Document
    model.Planet[] planets;
    /// TODO - Document
    model.FlyingWorkerGroup[] flyingWorkerGroups;
    /// TODO - Document
    int maxFlyingWorkerGroups;
    /// TODO - Document
    int maxTravelDistance;
    /// TODO - Document
    int maxBuilders;
    /// TODO - Document
    model.BuildingProperties[model.BuildingType] buildingProperties;

    this(int myIndex, int currentTick, int maxTickCount, model.Player[] players, model.Planet[] planets, model.FlyingWorkerGroup[] flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int maxBuilders, model.BuildingProperties[model.BuildingType] buildingProperties) {
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
        return Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, maxBuilders, buildingProperties);
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
        writer.write(maxBuilders);
        writer.write(cast(int)(buildingProperties.length));
        foreach (buildingPropertiesKey, buildingPropertiesValue; buildingProperties) {
            writer.write(cast(int)(buildingPropertiesKey));
            buildingPropertiesValue.writeTo(writer);
        }
    }
}