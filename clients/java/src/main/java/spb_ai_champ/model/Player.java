package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Player (game participant)
 */
public class Player {
    /**
     * Team index
     */
    private int teamIndex;

    /**
     * Team index
     */
    public int getTeamIndex() {
        return teamIndex;
    }

    /**
     * Team index
     */
    public void setTeamIndex(int value) {
        this.teamIndex = value;
    }
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
    /**
     * Player's specialty
     */
    private spb_ai_champ.model.Specialty specialty;

    /**
     * Player's specialty
     */
    public spb_ai_champ.model.Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Player's specialty
     */
    public void setSpecialty(spb_ai_champ.model.Specialty value) {
        this.specialty = value;
    }

    public Player(int teamIndex, int score, spb_ai_champ.model.Specialty specialty) {
        this.teamIndex = teamIndex;
        this.score = score;
        this.specialty = specialty;
    }

    /**
     * Read Player from input stream
     */
    public static Player readFrom(java.io.InputStream stream) throws java.io.IOException {
        int teamIndex;
        teamIndex = StreamUtil.readInt(stream);
        int score;
        score = StreamUtil.readInt(stream);
        spb_ai_champ.model.Specialty specialty;
        if (StreamUtil.readBoolean(stream)) {
            specialty = spb_ai_champ.model.Specialty.readFrom(stream);
        } else {
            specialty = null;
        }
        return new Player(teamIndex, score, specialty);
    }

    /**
     * Write Player to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, teamIndex);
        StreamUtil.writeInt(stream, score);
        if (specialty == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, specialty.tag);
        }
    }

    /**
     * Get string representation of Player
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Player { ");
        stringBuilder.append("teamIndex: ");
        stringBuilder.append(String.valueOf(teamIndex));
        stringBuilder.append(", ");
        stringBuilder.append("score: ");
        stringBuilder.append(String.valueOf(score));
        stringBuilder.append(", ");
        stringBuilder.append("specialty: ");
        stringBuilder.append(String.valueOf(specialty));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}