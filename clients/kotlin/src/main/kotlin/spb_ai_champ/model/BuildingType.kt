package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
enum class BuildingType private constructor(val tag: Int) {
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