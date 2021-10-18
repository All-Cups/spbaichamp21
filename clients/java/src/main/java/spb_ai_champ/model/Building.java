package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * A building
 */
public class Building {
    /**
     * Building's type
     */
    private spb_ai_champ.model.BuildingType buildingType;

    /**
     * Building's type
     */
    public spb_ai_champ.model.BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     * Building's type
     */
    public void setBuildingType(spb_ai_champ.model.BuildingType value) {
        this.buildingType = value;
    }
    /**
     * Current health
     */
    private int health;

    /**
     * Current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Current health
     */
    public void setHealth(int value) {
        this.health = value;
    }
    /**
     * Amount of work done for current task
     */
    private int workDone;

    /**
     * Amount of work done for current task
     */
    public int getWorkDone() {
        return workDone;
    }

    /**
     * Amount of work done for current task
     */
    public void setWorkDone(int value) {
        this.workDone = value;
    }

    public Building(spb_ai_champ.model.BuildingType buildingType, int health, int workDone) {
        this.buildingType = buildingType;
        this.health = health;
        this.workDone = workDone;
    }

    /**
     * Read Building from input stream
     */
    public static Building readFrom(java.io.InputStream stream) throws java.io.IOException {
        spb_ai_champ.model.BuildingType buildingType;
        buildingType = spb_ai_champ.model.BuildingType.readFrom(stream);
        int health;
        health = StreamUtil.readInt(stream);
        int workDone;
        workDone = StreamUtil.readInt(stream);
        return new Building(buildingType, health, workDone);
    }

    /**
     * Write Building to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, buildingType.tag);
        StreamUtil.writeInt(stream, health);
        StreamUtil.writeInt(stream, workDone);
    }

    /**
     * Get string representation of Building
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Building { ");
        stringBuilder.append("buildingType: ");
        stringBuilder.append(String.valueOf(buildingType));
        stringBuilder.append(", ");
        stringBuilder.append("health: ");
        stringBuilder.append(String.valueOf(health));
        stringBuilder.append(", ");
        stringBuilder.append("workDone: ");
        stringBuilder.append(String.valueOf(workDone));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}