package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Movement order
 */
public class MoveAction {
    /**
     * Id of the planet where workers need to be sent from
     */
    private int startPlanet;

    /**
     * Id of the planet where workers need to be sent from
     */
    public int getStartPlanet() {
        return startPlanet;
    }

    /**
     * Id of the planet where workers need to be sent from
     */
    public void setStartPlanet(int value) {
        this.startPlanet = value;
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
     * Number of workers to send
     */
    private int workerNumber;

    /**
     * Number of workers to send
     */
    public int getWorkerNumber() {
        return workerNumber;
    }

    /**
     * Number of workers to send
     */
    public void setWorkerNumber(int value) {
        this.workerNumber = value;
    }
    /**
     * Resource workers should carry
     */
    private spb_ai_champ.model.Resource takeResource;

    /**
     * Resource workers should carry
     */
    public spb_ai_champ.model.Resource getTakeResource() {
        return takeResource;
    }

    /**
     * Resource workers should carry
     */
    public void setTakeResource(spb_ai_champ.model.Resource value) {
        this.takeResource = value;
    }

    public MoveAction(int startPlanet, int targetPlanet, int workerNumber, spb_ai_champ.model.Resource takeResource) {
        this.startPlanet = startPlanet;
        this.targetPlanet = targetPlanet;
        this.workerNumber = workerNumber;
        this.takeResource = takeResource;
    }

    /**
     * Read MoveAction from input stream
     */
    public static MoveAction readFrom(java.io.InputStream stream) throws java.io.IOException {
        int startPlanet;
        startPlanet = StreamUtil.readInt(stream);
        int targetPlanet;
        targetPlanet = StreamUtil.readInt(stream);
        int workerNumber;
        workerNumber = StreamUtil.readInt(stream);
        spb_ai_champ.model.Resource takeResource;
        if (StreamUtil.readBoolean(stream)) {
            takeResource = spb_ai_champ.model.Resource.readFrom(stream);
        } else {
            takeResource = null;
        }
        return new MoveAction(startPlanet, targetPlanet, workerNumber, takeResource);
    }

    /**
     * Write MoveAction to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, startPlanet);
        StreamUtil.writeInt(stream, targetPlanet);
        StreamUtil.writeInt(stream, workerNumber);
        if (takeResource == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, takeResource.tag);
        }
    }

    /**
     * Get string representation of MoveAction
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MoveAction { ");
        stringBuilder.append("startPlanet: ");
        stringBuilder.append(String.valueOf(startPlanet));
        stringBuilder.append(", ");
        stringBuilder.append("targetPlanet: ");
        stringBuilder.append(String.valueOf(targetPlanet));
        stringBuilder.append(", ");
        stringBuilder.append("workerNumber: ");
        stringBuilder.append(String.valueOf(workerNumber));
        stringBuilder.append(", ");
        stringBuilder.append("takeResource: ");
        stringBuilder.append(String.valueOf(takeResource));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}