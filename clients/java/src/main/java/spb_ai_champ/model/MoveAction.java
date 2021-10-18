package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class MoveAction {
    /**
     * TODO - Document
     */
    private int startPlanet;

    /**
     * TODO - Document
     */
    public int getStartPlanet() {
        return startPlanet;
    }

    /**
     * TODO - Document
     */
    public void setStartPlanet(int value) {
        this.startPlanet = value;
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
    private int workerNumber;

    /**
     * TODO - Document
     */
    public int getWorkerNumber() {
        return workerNumber;
    }

    /**
     * TODO - Document
     */
    public void setWorkerNumber(int value) {
        this.workerNumber = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Resource takeResource;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Resource getTakeResource() {
        return takeResource;
    }

    /**
     * TODO - Document
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