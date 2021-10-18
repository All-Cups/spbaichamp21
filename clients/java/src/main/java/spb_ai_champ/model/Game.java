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
     * Additional distance of direct travel between planets for player with Logistics specialty
     */
    private int logisticsUpgrade;

    /**
     * Additional distance of direct travel between planets for player with Logistics specialty
     */
    public int getLogisticsUpgrade() {
        return logisticsUpgrade;
    }

    /**
     * Additional distance of direct travel between planets for player with Logistics specialty
     */
    public void setLogisticsUpgrade(int value) {
        this.logisticsUpgrade = value;
    }
    /**
     * Additional work done by player with Production specialty (in percent)
     */
    private int productionUpgrade;

    /**
     * Additional work done by player with Production specialty (in percent)
     */
    public int getProductionUpgrade() {
        return productionUpgrade;
    }

    /**
     * Additional work done by player with Production specialty (in percent)
     */
    public void setProductionUpgrade(int value) {
        this.productionUpgrade = value;
    }
    /**
     * Additional strength workers for player with Combat specialty (in percent)
     */
    private int combatUpgrade;

    /**
     * Additional strength workers for player with Combat specialty (in percent)
     */
    public int getCombatUpgrade() {
        return combatUpgrade;
    }

    /**
     * Additional strength workers for player with Combat specialty (in percent)
     */
    public void setCombatUpgrade(int value) {
        this.combatUpgrade = value;
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
    /**
     * Whether choosing specialties is allowed
     */
    private boolean specialtiesAllowed;

    /**
     * Whether choosing specialties is allowed
     */
    public boolean isSpecialtiesAllowed() {
        return specialtiesAllowed;
    }

    /**
     * Whether choosing specialties is allowed
     */
    public void setSpecialtiesAllowed(boolean value) {
        this.specialtiesAllowed = value;
    }
    /**
     * View distance
     */
    private Integer viewDistance;

    /**
     * View distance
     */
    public Integer getViewDistance() {
        return viewDistance;
    }

    /**
     * View distance
     */
    public void setViewDistance(Integer value) {
        this.viewDistance = value;
    }

    public Game(int myIndex, int currentTick, int maxTickCount, spb_ai_champ.model.Player[] players, spb_ai_champ.model.Planet[] planets, spb_ai_champ.model.FlyingWorkerGroup[] flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int logisticsUpgrade, int productionUpgrade, int combatUpgrade, int maxBuilders, java.util.Map<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingProperties, boolean specialtiesAllowed, Integer viewDistance) {
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
        int logisticsUpgrade;
        logisticsUpgrade = StreamUtil.readInt(stream);
        int productionUpgrade;
        productionUpgrade = StreamUtil.readInt(stream);
        int combatUpgrade;
        combatUpgrade = StreamUtil.readInt(stream);
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
        boolean specialtiesAllowed;
        specialtiesAllowed = StreamUtil.readBoolean(stream);
        Integer viewDistance;
        if (StreamUtil.readBoolean(stream)) {
            viewDistance = StreamUtil.readInt(stream);
        } else {
            viewDistance = null;
        }
        return new Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, logisticsUpgrade, productionUpgrade, combatUpgrade, maxBuilders, buildingProperties, specialtiesAllowed, viewDistance);
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
        StreamUtil.writeInt(stream, logisticsUpgrade);
        StreamUtil.writeInt(stream, productionUpgrade);
        StreamUtil.writeInt(stream, combatUpgrade);
        StreamUtil.writeInt(stream, maxBuilders);
        StreamUtil.writeInt(stream, buildingProperties.size());
        for (java.util.Map.Entry<spb_ai_champ.model.BuildingType, spb_ai_champ.model.BuildingProperties> buildingPropertiesEntry : buildingProperties.entrySet()) {
            spb_ai_champ.model.BuildingType buildingPropertiesKey = buildingPropertiesEntry.getKey();
            StreamUtil.writeInt(stream, buildingPropertiesKey.tag);
            spb_ai_champ.model.BuildingProperties buildingPropertiesValue = buildingPropertiesEntry.getValue();
            buildingPropertiesValue.writeTo(stream);
        }
        StreamUtil.writeBoolean(stream, specialtiesAllowed);
        if (viewDistance == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, viewDistance);
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
        stringBuilder.append("logisticsUpgrade: ");
        stringBuilder.append(String.valueOf(logisticsUpgrade));
        stringBuilder.append(", ");
        stringBuilder.append("productionUpgrade: ");
        stringBuilder.append(String.valueOf(productionUpgrade));
        stringBuilder.append(", ");
        stringBuilder.append("combatUpgrade: ");
        stringBuilder.append(String.valueOf(combatUpgrade));
        stringBuilder.append(", ");
        stringBuilder.append("maxBuilders: ");
        stringBuilder.append(String.valueOf(maxBuilders));
        stringBuilder.append(", ");
        stringBuilder.append("buildingProperties: ");
        stringBuilder.append(String.valueOf(buildingProperties));
        stringBuilder.append(", ");
        stringBuilder.append("specialtiesAllowed: ");
        stringBuilder.append(String.valueOf(specialtiesAllowed));
        stringBuilder.append(", ");
        stringBuilder.append("viewDistance: ");
        stringBuilder.append(String.valueOf(viewDistance));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}