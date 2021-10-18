package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Player's specialty
 */
public enum Specialty {
    /**
     * Logistics. Increased travel distance
     */
    LOGISTICS(0),
    /**
     * Production. Increased work speed
     */
    PRODUCTION(1),
    /**
     * Combat. Increased damage
     */
    COMBAT(2);

    public int tag;

    Specialty(int tag) {
        this.tag = tag;
    }

    /**
     * Read Specialty from input stream
     */
    public static Specialty readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
        case 0:
            return LOGISTICS;
        case 1:
            return PRODUCTION;
        case 2:
            return COMBAT;
        default:
            throw new java.io.IOException("Unexpected tag value");
        }
    }
}