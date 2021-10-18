package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Group of workers on a planet
 */
public class WorkerGroup {
    /**
     * Index of player controlling the workers
     */
    private int playerIndex;

    /**
     * Index of player controlling the workers
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Index of player controlling the workers
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