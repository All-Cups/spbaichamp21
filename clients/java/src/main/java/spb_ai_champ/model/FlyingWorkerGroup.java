package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * A flying worker group
 */
public class FlyingWorkerGroup {
    /**
     * Index of player controlling workers
     */
    private int playerIndex;

    /**
     * Index of player controlling workers
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Index of player controlling workers
     */
    public void setPlayerIndex(int value) {
        this.playerIndex = value;
    }
    /**
     * Number of workers in the group
     */
    private int number;

    /**
     * Number of workers in the group
     */
    public int getNumber() {
        return number;
    }

    /**
     * Number of workers in the group
     */
    public void setNumber(int value) {
        this.number = value;
    }
    /**
     * Tick when workers left previous planet on their path
     */
    private int departureTick;

    /**
     * Tick when workers left previous planet on their path
     */
    public int getDepartureTick() {
        return departureTick;
    }

    /**
     * Tick when workers left previous planet on their path
     */
    public void setDepartureTick(int value) {
        this.departureTick = value;
    }
    /**
     * Id of the previous planet on the path
     */
    private int departurePlanet;

    /**
     * Id of the previous planet on the path
     */
    public int getDeparturePlanet() {
        return departurePlanet;
    }

    /**
     * Id of the previous planet on the path
     */
    public void setDeparturePlanet(int value) {
        this.departurePlanet = value;
    }
    /**
     * Tick when workers will arrive to the next planet in their path
     */
    private int nextPlanetArrivalTick;

    /**
     * Tick when workers will arrive to the next planet in their path
     */
    public int getNextPlanetArrivalTick() {
        return nextPlanetArrivalTick;
    }

    /**
     * Tick when workers will arrive to the next planet in their path
     */
    public void setNextPlanetArrivalTick(int value) {
        this.nextPlanetArrivalTick = value;
    }
    /**
     * Id of the next planet in the path
     */
    private int nextPlanet;

    /**
     * Id of the next planet in the path
     */
    public int getNextPlanet() {
        return nextPlanet;
    }

    /**
     * Id of the next planet in the path
     */
    public void setNextPlanet(int value) {
        this.nextPlanet = value;
    }
    /**
     * Id of the target planet
     */
    private int targetPlanet;

    /**
     * Id of the target planet
     */
    public int getTargetPlanet() {
        return targetPlanet;
    }

    /**
     * Id of the target planet
     */
    public void setTargetPlanet(int value) {
        this.targetPlanet = value;
    }
    /**
     * Resource that workers are carrying
     */
    private spb_ai_champ.model.Resource resource;

    /**
     * Resource that workers are carrying
     */
    public spb_ai_champ.model.Resource getResource() {
        return resource;
    }

    /**
     * Resource that workers are carrying
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