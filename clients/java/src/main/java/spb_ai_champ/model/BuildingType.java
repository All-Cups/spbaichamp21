package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Building type
 */
public enum BuildingType {
    /**
     * Quarry harvests stone
     */
    QUARRY(0),
    /**
     * Mines harvests ore
     */
    MINES(1),
    /**
     * Career harvest sand
     */
    CAREER(2),
    /**
     * Farm harvests organics
     */
    FARM(3),
    /**
     * Foundry produces metal
     */
    FOUNDRY(4),
    /**
     * Furnace produces silicon
     */
    FURNACE(5),
    /**
     * Bioreactor produces plastic
     */
    BIOREACTOR(6),
    /**
     * Chip factory produces chips
     */
    CHIP_FACTORY(7),
    /**
     * Accumulator factory produces accumulators
     */
    ACCUMULATOR_FACTORY(8),
    /**
     * Replicator produces new workers
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