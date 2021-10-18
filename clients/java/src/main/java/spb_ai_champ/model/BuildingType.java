package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public enum BuildingType {
    /**
     * TODO - Document
     */
    QUARRY(0),
    /**
     * TODO - Document
     */
    MINES(1),
    /**
     * TODO - Document
     */
    CAREER(2),
    /**
     * TODO - Document
     */
    FARM(3),
    /**
     * TODO - Document
     */
    FOUNDRY(4),
    /**
     * TODO - Document
     */
    FURNACE(5),
    /**
     * TODO - Document
     */
    BIOREACTOR(6),
    /**
     * TODO - Document
     */
    CHIP_FACTORY(7),
    /**
     * TODO - Document
     */
    ACCUMULATOR_FACTORY(8),
    /**
     * TODO - Document
     */
    REPLICATOR(9);

    public int tag;

    BuildingType(int tag) {
        this.tag = tag;
    }

    /**
     * Read BuildingType from input stream
     */
    public static BuildingType readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
        case 0:
            return QUARRY;
        case 1:
            return MINES;
        case 2:
            return CAREER;
        case 3:
            return FARM;
        case 4:
            return FOUNDRY;
        case 5:
            return FURNACE;
        case 6:
            return BIOREACTOR;
        case 7:
            return CHIP_FACTORY;
        case 8:
            return ACCUMULATOR_FACTORY;
        case 9:
            return REPLICATOR;
        default:
            throw new java.io.IOException("Unexpected tag value");
        }
    }
}