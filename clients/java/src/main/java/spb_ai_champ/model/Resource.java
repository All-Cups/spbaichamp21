package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Resource type
 */
public enum Resource {
    /**
     * Stone
     */
    STONE(0),
    /**
     * Ore
     */
    ORE(1),
    /**
     * Sand
     */
    SAND(2),
    /**
     * Organics
     */
    ORGANICS(3),
    /**
     * Metal
     */
    METAL(4),
    /**
     * Silicon
     */
    SILICON(5),
    /**
     * Plastic
     */
    PLASTIC(6),
    /**
     * Chip
     */
    CHIP(7),
    /**
     * Accumulator
     */
    ACCUMULATOR(8);

    public int tag;

    Resource(int tag) {
        this.tag = tag;
    }

    /**
     * Read Resource from input stream
     */
    public static Resource readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
        case 0:
            return STONE;
        case 1:
            return ORE;
        case 2:
            return SAND;
        case 3:
            return ORGANICS;
        case 4:
            return METAL;
        case 5:
            return SILICON;
        case 6:
            return PLASTIC;
        case 7:
            return CHIP;
        case 8:
            return ACCUMULATOR;
        default:
            throw new java.io.IOException("Unexpected tag value");
        }
    }
}