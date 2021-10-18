package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class FlyingWorkerGroup {
    /**
     * TODO - Document
     */
    private int playerIndex;

    /**
     * TODO - Document
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * TODO - Document
     */
    public void setPlayerIndex(int value) {
        this.playerIndex = value;
    }
    /**
     * TODO - Document
     */
    private int number;

    /**
     * TODO - Document
     */
    public int getNumber() {
        return number;
    }

    /**
     * TODO - Document
     */
    public void setNumber(int value) {
        this.number = value;
    }
    /**
     * TODO - Document
     */
    private int departureTick;

    /**
     * TODO - Document
     */
    public int getDepartureTick() {
        return departureTick;
    }

    /**
     * TODO - Document
     */
    public void setDepartureTick(int value) {
        this.departureTick = value;
    }
    /**
     * TODO - Document
     */
    private int departurePlanet;

    /**
     * TODO - Document
     */
    public int getDeparturePlanet() {
        return departurePlanet;
    }

    /**
     * TODO - Document
     */
    public void setDeparturePlanet(int value) {
        this.departurePlanet = value;
    }
    /**
     * TODO - Document
     */
    private int nextPlanetArrivalTick;

    /**
     * TODO - Document
     */
    public int getNextPlanetArrivalTick() {
        return nextPlanetArrivalTick;
    }

    /**
     * TODO - Document
     */
    public void setNextPlanetArrivalTick(int value) {
        this.nextPlanetArrivalTick = value;
    }
    /**
     * TODO - Document
     */
    private int nextPlanet;

    /**
     * TODO - Document
     */
    public int getNextPlanet() {
        return nextPlanet;
    }

    /**
     * TODO - Document
     */
    public void setNextPlanet(int value) {
        this.nextPlanet = value;
    }
    /**
     * TODO - Document
     */
    private int targetPlanet;

    /**
     * TODO - Document
     */
    public int getTargetPlanet() {
        return targetPlanet;
    }

    /**
     * TODO - Document
     */
    public void setTargetPlanet(int value) {
        this.targetPlanet = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Resource resource;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Resource getResource() {
        return resource;
    }

    /**
     * TODO - Document
     */
    public void setResource(spb_ai_champ.model.Resource value) {
        this.resource = value;
    }

    public FlyingWorkerGroup(int playerIndex, int number, int departureTick, int departurePlanet, int nextPlanetArrivalTick, int nextPlanet, int targetPlanet, spb_ai_champ.model.Resource resource) {
        this.playerIndex = playerIndex;
        this.number = number;
        this.departureTick = departureTick;
        this.departurePlanet = departurePlanet;
        this.nextPlanetArrivalTick = nextPlanetArrivalTick;
        this.nextPlanet = nextPlanet;
        this.targetPlanet = targetPlanet;
        this.resource = resource;
    }

    /**
     * Read FlyingWorkerGroup from input stream
     */
    public static FlyingWorkerGroup readFrom(java.io.InputStream stream) throws java.io.IOException {
        int playerIndex;
        playerIndex = StreamUtil.readInt(stream);
        int number;
        number = StreamUtil.readInt(stream);
        int departureTick;
        departureTick = StreamUtil.readInt(stream);
        int departurePlanet;
        departurePlanet = StreamUtil.readInt(stream);
        int nextPlanetArrivalTick;
        nextPlanetArrivalTick = StreamUtil.readInt(stream);
        int nextPlanet;
        nextPlanet = StreamUtil.readInt(stream);
        int targetPlanet;
        targetPlanet = StreamUtil.readInt(stream);
        spb_ai_champ.model.Resource resource;
        if (StreamUtil.readBoolean(stream)) {
            resource = spb_ai_champ.model.Resource.readFrom(stream);
        } else {
            resource = null;
        }
        return new FlyingWorkerGroup(playerIndex, number, departureTick, departurePlanet, nextPlanetArrivalTick, nextPlanet, targetPlanet, resource);
    }

    /**
     * Write FlyingWorkerGroup to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, playerIndex);
        StreamUtil.writeInt(stream, number);
        StreamUtil.writeInt(stream, departureTick);
        StreamUtil.writeInt(stream, departurePlanet);
        StreamUtil.writeInt(stream, nextPlanetArrivalTick);
        StreamUtil.writeInt(stream, nextPlanet);
        StreamUtil.writeInt(stream, targetPlanet);
        if (resource == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, resource.tag);
        }
    }

    /**
     * Get string representation of FlyingWorkerGroup
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("FlyingWorkerGroup { ");
        stringBuilder.append("playerIndex: ");
        stringBuilder.append(String.valueOf(playerIndex));
        stringBuilder.append(", ");
        stringBuilder.append("number: ");
        stringBuilder.append(String.valueOf(number));
        stringBuilder.append(", ");
        stringBuilder.append("departureTick: ");
        stringBuilder.append(String.valueOf(departureTick));
        stringBuilder.append(", ");
        stringBuilder.append("departurePlanet: ");
        stringBuilder.append(String.valueOf(departurePlanet));
        stringBuilder.append(", ");
        stringBuilder.append("nextPlanetArrivalTick: ");
        stringBuilder.append(String.valueOf(nextPlanetArrivalTick));
        stringBuilder.append(", ");
        stringBuilder.append("nextPlanet: ");
        stringBuilder.append(String.valueOf(nextPlanet));
        stringBuilder.append(", ");
        stringBuilder.append("targetPlanet: ");
        stringBuilder.append(String.valueOf(targetPlanet));
        stringBuilder.append(", ");
        stringBuilder.append("resource: ");
        stringBuilder.append(String.valueOf(resource));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}