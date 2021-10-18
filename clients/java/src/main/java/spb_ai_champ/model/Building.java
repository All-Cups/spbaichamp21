package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class Building {
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.BuildingType buildingType;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     * TODO - Document
     */
    public void setBuildingType(spb_ai_champ.model.BuildingType value) {
        this.buildingType = value;
    }
    /**
     * TODO - Document
     */
    private int health;

    /**
     * TODO - Document
     */
    public int getHealth() {
        return health;
    }

    /**
     * TODO - Document
     */
    public void setHealth(int value) {
        this.health = value;
    }
    /**
     * TODO - Document
     */
    private int workDone;

    /**
     * TODO - Document
     */
    public int getWorkDone() {
        return workDone;
    }

    /**
     * TODO - Document
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