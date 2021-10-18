package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Current game's state
 */
public class Game {
    /**
     * Your player's index
     */
    private int myIndex;

    /**
     * Your player's index
     */
    public int getMyIndex() {
        return myIndex;
    }

    /**
     * Your player's index
     */
    public void setMyIndex(int value) {
        this.myIndex = value;
    }
    /**
     * Current tick number
     */
    private int currentTick;

    /**
     * Current tick number
     */
    public int getCurrentTick() {
        return currentTick;
    }

    /**
     * Current tick number
     */
    public void setCurrentTick(int value) {
        this.currentTick = value;
    }
    /**
     * Max number of ticks in the game
     */
    private int maxTickCount;

    /**
     * Max number of ticks in the game
     */
    public int getMaxTickCount() {
        return maxTickCount;
    }

    /**
     * Max number of ticks in the game
     */
    public void setMaxTickCount(int value) {
        this.maxTickCount = value;
    }
    /**
     * List of players
     */
    private spb_ai_champ.model.Player[] players;

    /**
     * List of players
     */
    public spb_ai_champ.model.Player[] getPlayers() {
        return players;
    }

    /**
     * List of players
     */
    public void setPlayers(spb_ai_champ.model.Player[] value) {
        this.players = value;
    }
    /**
     * List of planets
     */
    private spb_ai_champ.model.Planet[] planets;

    /**
     * List of planets
     */
    public spb_ai_champ.model.Planet[] getPlanets() {
        return planets;
    }

    /**
     * List of planets
     */
    public void setPlanets(spb_ai_champ.model.Planet[] value) {
        this.planets = value;
    }
    /**
     * List of flying worker groups
     */
    private spb_ai_champ.model.FlyingWorkerGroup[] flyingWorkerGroups;

    /**
     * List of flying worker groups
     */
    public spb_ai_champ.model.FlyingWorkerGroup[] getFlyingWorkerGroups() {
        return flyingWorkerGroups;
    }

    /**
     * List of flying worker groups
     */
    public void setFlyingWorkerGroups(spb_ai_champ.model.FlyingWorkerGroup[] value) {
        this.flyingWorkerGroups = value;
    }
    /**
     * Max number of flying worker groups for one player
     */
    private int maxFlyingWorkerGroups;

    /**
     * Max number of flying worker groups for one player
     */
    public int getMaxFlyingWorkerGroups() {
        return maxFlyingWorkerGroups;
    }

    /**
     * Max number of flying worker groups for one player
     */
    public void setMaxFlyingWorkerGroups(int value) {
        this.maxFlyingWorkerGroups = value;
    }
    /**
     * Max distance of direct travel between planets
     */
    private int maxTravelDistance;

    /**
     * Max distance of direct travel between planets
     */
    public int getMaxTravelDistance() {
        return maxTravelDistance;
    }

    /**
     * Max distance of direct travel between planets
     */
    public void setMaxTravelDistance(int value) {
        this.maxTravelDistance = value;
    }
    /**
     * Max number of workers performing building on one planet
     */
    private int maxBuilders;

    /**
     * Max number of workers performing building on one planet
     */
    public int getMaxBuilders() {
        return maxBuilders;
    }

    /**
     * Max number of workers performing building on one planet
     */
    public void setMaxBuilders(int value) {
        this.maxBuilders = value;
    }
    /**
     * Properties of every building type
     */
    private java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingProperties;

    /**
     * Properties of every building type
     */
    public java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> getBuildingProperties() {
        return buildingProperties;
    }

    /**
     * Properties of every building type
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