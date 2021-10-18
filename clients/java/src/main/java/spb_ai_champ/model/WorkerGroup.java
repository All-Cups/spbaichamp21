package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class WorkerGroup {
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

    public WorkerGroup(int playerIndex, int number) {
        this.playerIndex = playerIndex;
        this.number = number;
    }

    /**
     * Read WorkerGroup from input stream
     */
    public static WorkerGroup readFrom(java.io.InputStream stream) throws java.io.IOException {
        int playerIndex;
        playerIndex = StreamUtil.readInt(stream);
        int number;
        number = StreamUtil.readInt(stream);
        return new WorkerGroup(playerIndex, number);
    }

    /**
     * Write WorkerGroup to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, playerIndex);
        StreamUtil.writeInt(stream, number);
    }

    /**
     * Get string representation of WorkerGroup
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("WorkerGroup { ");
        stringBuilder.append("playerIndex: ");
        stringBuilder.append(String.valueOf(playerIndex));
        stringBuilder.append(", ");
        stringBuilder.append("number: ");
        stringBuilder.append(String.valueOf(number));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}