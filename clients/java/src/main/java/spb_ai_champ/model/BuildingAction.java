package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Building order
 */
public class BuildingAction {
    /**
     * Id of the planet where the action needs to be performed
     */
    private int planet;

    /**
     * Id of the planet where the action needs to be performed
     */
    public int getPlanet() {
        return planet;
    }

    /**
     * Id of the planet where the action needs to be performed
     */
    public void setPlanet(int value) {
        this.planet = value;
    }
    /**
     * Type of a building to build. If absent, current building will be destroyed
     */
    private spb_ai_champ.model.BuildingType buildingType;

    /**
     * Type of a building to build. If absent, current building will be destroyed
     */
    public spb_ai_champ.model.BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     * Type of a building to build. If absent, current building will be destroyed
     */
    public void setBuildingType(spb_ai_champ.model.BuildingType value) {
        this.buildingType = value;
    }

    public BuildingAction(int planet, spb_ai_champ.model.BuildingType buildingType) {
        this.planet = planet;
        this.buildingType = buildingType;
    }

    /**
     * Read BuildingAction from input stream
     */
    public static BuildingAction readFrom(java.io.InputStream stream) throws java.io.IOException {
        int planet;
        planet = StreamUtil.readInt(stream);
        spb_ai_champ.model.BuildingType buildingType;
        if (StreamUtil.readBoolean(stream)) {
            buildingType = spb_ai_champ.model.BuildingType.readFrom(stream);
        } else {
            buildingType = null;
        }
        return new BuildingAction(planet, buildingType);
    }

    /**
     * Write BuildingAction to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, planet);
        if (buildingType == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, buildingType.tag);
        }
    }

    /**
     * Get string representation of BuildingAction
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("BuildingAction { ");
        stringBuilder.append("planet: ");
        stringBuilder.append(String.valueOf(planet));
        stringBuilder.append(", ");
        stringBuilder.append("buildingType: ");
        stringBuilder.append(String.valueOf(buildingType));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}