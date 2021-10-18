package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Player (game participant)
 */
public class Player {
    /**
     * Current score points
     */
    private int score;

    /**
     * Current score points
     */
    public int getScore() {
        return score;
    }

    /**
     * Current score points
     */
    public void setScore(int value) {
        this.score = value;
    }

    public Player(int score) {
        this.score = score;
    }

    /**
     * Read Player from input stream
     */
    public static Player readFrom(java.io.InputStream stream) throws java.io.IOException {
        int score;
        score = StreamUtil.readInt(stream);
        return new Player(score);
    }

    /**
     * Write Player to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, score);
    }

    /**
     * Get string representation of Player
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Player { ");
        stringBuilder.append("score: ");
        stringBuilder.append(String.valueOf(score));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}