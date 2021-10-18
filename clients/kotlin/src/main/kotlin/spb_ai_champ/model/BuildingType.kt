package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Building type
 */
enum class BuildingType private constructor(val tag: Int) {
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

    companion object {
        /**
         * Read BuildingType from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): BuildingType {
            return when (StreamUtil.readInt(stream)) {
            QUARRY.tag -> QUARRY
            MINES.tag -> MINES
            CAREER.tag -> CAREER
            FARM.tag -> FARM
            FOUNDRY.tag -> FOUNDRY
            FURNACE.tag -> FURNACE
            BIOREACTOR.tag -> BIOREACTOR
            CHIP_FACTORY.tag -> CHIP_FACTORY
            ACCUMULATOR_FACTORY.tag -> ACCUMULATOR_FACTORY
            REPLICATOR.tag -> REPLICATOR
            else -> throw java.io.IOException("Unexpected tag value")
            }
        }
    }
}