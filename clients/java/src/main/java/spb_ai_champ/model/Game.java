package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class Game {
    /**
     * TODO - Document
     */
    private int myIndex;

    /**
     * TODO - Document
     */
    public int getMyIndex() {
        return myIndex;
    }

    /**
     * TODO - Document
     */
    public void setMyIndex(int value) {
        this.myIndex = value;
    }
    /**
     * TODO - Document
     */
    private int currentTick;

    /**
     * TODO - Document
     */
    public int getCurrentTick() {
        return currentTick;
    }

    /**
     * TODO - Document
     */
    public void setCurrentTick(int value) {
        this.currentTick = value;
    }
    /**
     * TODO - Document
     */
    private int maxTickCount;

    /**
     * TODO - Document
     */
    public int getMaxTickCount() {
        return maxTickCount;
    }

    /**
     * TODO - Document
     */
    public void setMaxTickCount(int value) {
        this.maxTickCount = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Player[] players;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Player[] getPlayers() {
        return players;
    }

    /**
     * TODO - Document
     */
    public void setPlayers(spb_ai_champ.model.Player[] value) {
        this.players = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Planet[] planets;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Planet[] getPlanets() {
        return planets;
    }

    /**
     * TODO - Document
     */
    public void setPlanets(spb_ai_champ.model.Planet[] value) {
        this.planets = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.FlyingWorkerGroup[] flyingWorkerGroups;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.FlyingWorkerGroup[] getFlyingWorkerGroups() {
        return flyingWorkerGroups;
    }

    /**
     * TODO - Document
     */
    public void setFlyingWorkerGroups(spb_ai_champ.model.FlyingWorkerGroup[] value) {
        this.flyingWorkerGroups = value;
    }
    /**
     * TODO - Document
     */
    private int maxFlyingWorkerGroups;

    /**
     * TODO - Document
     */
    public int getMaxFlyingWorkerGroups() {
        return maxFlyingWorkerGroups;
    }

    /**
     * TODO - Document
     */
    public void setMaxFlyingWorkerGroups(int value) {
        this.maxFlyingWorkerGroups = value;
    }
    /**
     * TODO - Document
     */
    private int maxTravelDistance;

    /**
     * TODO - Document
     */
    public int getMaxTravelDistance() {
        return maxTravelDistance;
    }

    /**
     * TODO - Document
     */
    public void setMaxTravelDistance(int value) {
        this.maxTravelDistance = value;
    }
    /**
     * TODO - Document
     */
    private int maxBuilders;

    /**
     * TODO - Document
     */
    public int getMaxBuilders() {
        return maxBuilders;
    }

    /**
     * TODO - Document
     */
    public void setMaxBuilders(int value) {
        this.maxBuilders = value;
    }
    /**
     * TODO - Document
     */
    private java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingProperties;

    /**
     * TODO - Document
     */
    public java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> getBuildingProperties() {
        return buildingProperties;
    }

    /**
     * TODO - Document
     */
    public void setBuildingProperties(java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> value) {
        this.buildingProperties = value;
    }

    public Game(int myIndex, int currentTick, int maxTickCount, spb_ai_champ.model.Player[] players, spb_ai_champ.model.Planet[] planets, spb_ai_champ.model.FlyingWorkerGroup[] flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int maxBuilders, java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingProperties) {
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
    public static Game readFrom(java.io.InputStream stream) throws java.io.IOException {
        int myIndex;
        myIndex = StreamUtil.readInt(stream);
        int currentTick;
        currentTick = StreamUtil.readInt(stream);
        int maxTickCount;
        maxTickCount = StreamUtil.readInt(stream);
        spb_ai_champ.model.Player[] players;
        players = new spb_ai_champ.model.Player[StreamUtil.readInt(stream)];
        for (int playersIndex = 0; playersIndex < players.length; playersIndex++) {
            spb_ai_champ.model.Player playersElement;
            playersElement = spb_ai_champ.model.Player.readFrom(stream);
            players[playersIndex] = playersElement;
        }
        spb_ai_champ.model.Planet[] planets;
        planets = new spb_ai_champ.model.Planet[StreamUtil.readInt(stream)];
        for (int planetsIndex = 0; planetsIndex < planets.length; planetsIndex++) {
            spb_ai_champ.model.Planet planetsElement;
            planetsElement = spb_ai_champ.model.Planet.readFrom(stream);
            planets[planetsIndex] = planetsElement;
        }
        spb_ai_champ.model.FlyingWorkerGroup[] flyingWorkerGroups;
        flyingWorkerGroups = new spb_ai_champ.model.FlyingWorkerGroup[StreamUtil.readInt(stream)];
        for (int flyingWorkerGroupsIndex = 0; flyingWorkerGroupsIndex < flyingWorkerGroups.length; flyingWorkerGroupsIndex++) {
            spb_ai_champ.model.FlyingWorkerGroup flyingWorkerGroupsElement;
            flyingWorkerGroupsElement = spb_ai_champ.model.FlyingWorkerGroup.readFrom(stream);
            flyingWorkerGroups[flyingWorkerGroupsIndex] = flyingWorkerGroupsElement;
        }
        int maxFlyingWorkerGroups;
        maxFlyingWorkerGroups = StreamUtil.readInt(stream);
        int maxTravelDistance;
        maxTravelDistance = StreamUtil.readInt(stream);
        int maxBuilders;
        maxBuilders = StreamUtil.readInt(stream);
        java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingProperties;
        int buildingPropertiesSize = StreamUtil.readInt(stream);
        buildingProperties = new java.util.HashMap<>(buildingPropertiesSize);
        for (int buildingPropertiesIndex = 0; buildingPropertiesIndex < buildingPropertiesSize; buildingPropertiesIndex++) {
            spb_ai_champ.model.BuildingType buildingPropertiesKey;
            buildingPropertiesKey = spb_ai_champ.model.BuildingType.readFrom(stream);
            spb_ai_champ.model.BuildingProperties buildingPropertiesValue;
            buildingPropertiesValue = spb_ai_champ.model.BuildingProperties.readFrom(stream);
            buildingProperties.put(buildingPropertiesKey, buildingPropertiesValue);
        }
        return new Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, maxBuilders, buildingProperties);
    }

    /**
     * Write Game to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, myIndex);
        StreamUtil.writeInt(stream, currentTick);
        StreamUtil.writeInt(stream, maxTickCount);
        StreamUtil.writeInt(stream, players.length);
        for (spb_ai_champ.model.Player playersElement : players) {
            playersElement.writeTo(stream);
        }
        StreamUtil.writeInt(stream, planets.length);
        for (spb_ai_champ.model.Planet planetsElement : planets) {
            planetsElement.writeTo(stream);
        }
        StreamUtil.writeInt(stream, flyingWorkerGroups.length);
        for (spb_ai_champ.model.FlyingWorkerGroup flyingWorkerGroupsElement : flyingWorkerGroups) {
            flyingWorkerGroupsElement.writeTo(stream);
        }
        StreamUtil.writeInt(stream, maxFlyingWorkerGroups);
        StreamUtil.writeInt(stream, maxTravelDistance);
        StreamUtil.writeInt(stream, maxBuilders);
        StreamUtil.writeInt(stream, buildingProperties.size());
        for (java.util.Map.Entry<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingPropertiesEntry : buildingProperties.entrySet()) {
            spb_ai_champ.model.BuildingType buildingPropertiesKey = buildingPropertiesEntry.getKey();
            StreamUtil.writeInt(stream, buildingPropertiesKey.tag);
            spb_ai_champ.model.BuildingProperties buildingPropertiesValue = buildingPropertiesEntry.getValue();
            buildingPropertiesValue.writeTo(stream);
        }
    }

    /**
     * Get string representation of Game
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Game { ");
        stringBuilder.append("myIndex: ");
        stringBuilder.append(String.valueOf(myIndex));
        stringBuilder.append(", ");
        stringBuilder.append("currentTick: ");
        stringBuilder.append(String.valueOf(currentTick));
        stringBuilder.append(", ");
        stringBuilder.append("maxTickCount: ");
        stringBuilder.append(String.valueOf(maxTickCount));
        stringBuilder.append(", ");
        stringBuilder.append("players: ");
        stringBuilder.append("[ ");
        for (int playersIndex = 0; playersIndex < players.length; playersIndex++) {
            if (playersIndex != 0) {
                stringBuilder.append(", ");
            }
            spb_ai_champ.model.Player playersElement = players[playersIndex];
            stringBuilder.append(String.valueOf(playersElement));
        }
        stringBuilder.append(" ]");
        stringBuilder.append(", ");
        stringBuilder.append("planets: ");
        stringBuilder.append("[ ");
        for (int planetsIndex = 0; planetsIndex < planets.length; planetsIndex++) {
            if (planetsIndex != 0) {
                stringBuilder.append(", ");
            }
            spb_ai_champ.model.Planet planetsElement = planets[planetsIndex];
            stringBuilder.append(String.valueOf(planetsElement));
        }
        stringBuilder.append(" ]");
        stringBuilder.append(", ");
        stringBuilder.append("flyingWorkerGroups: ");
        stringBuilder.append("[ ");
        for (int flyingWorkerGroupsIndex = 0; flyingWorkerGroupsIndex < flyingWorkerGroups.length; flyingWorkerGroupsIndex++) {
            if (flyingWorkerGroupsIndex != 0) {
                stringBuilder.append(", ");
            }
            spb_ai_champ.model.FlyingWorkerGroup flyingWorkerGroupsElement = flyingWorkerGroups[flyingWorkerGroupsIndex];
            stringBuilder.append(String.valueOf(flyingWorkerGroupsElement));
        }
        stringBuilder.append(" ]");
        stringBuilder.append(", ");
        stringBuilder.append("maxFlyingWorkerGroups: ");
        stringBuilder.append(String.valueOf(maxFlyingWorkerGroups));
        stringBuilder.append(", ");
        stringBuilder.append("maxTravelDistance: ");
        stringBuilder.append(String.valueOf(maxTravelDistance));
        stringBuilder.append(", ");
        stringBuilder.append("maxBuilders: ");
        stringBuilder.append(String.valueOf(maxBuilders));
        stringBuilder.append(", ");
        stringBuilder.append("buildingProperties: ");
        stringBuilder.append(String.valueOf(buildingProperties));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}