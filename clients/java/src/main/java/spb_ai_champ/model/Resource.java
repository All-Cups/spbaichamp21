package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public enum Resource {
    /**
     * TODO - Document
     */
    STONE(0),
    /**
     * TODO - Document
     */
    ORE(1),
    /**
     * TODO - Document
     */
    SAND(2),
    /**
     * TODO - Document
     */
    ORGANICS(3),
    /**
     * TODO - Document
     */
    METAL(4),
    /**
     * TODO - Document
     */
    SILICON(5),
    /**
     * TODO - Document
     */
    PLASTIC(6),
    /**
     * TODO - Document
     */
    CHIP(7),
    /**
     * TODO - Document
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