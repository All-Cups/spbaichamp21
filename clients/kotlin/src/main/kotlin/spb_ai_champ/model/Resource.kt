package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
enum class Resource private constructor(val tag: Int) {
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

    companion object {
        /**
         * Read Resource from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Resource {
            return when (StreamUtil.readInt(stream)) {
            STONE.tag -> STONE
            ORE.tag -> ORE
            SAND.tag -> SAND
            ORGANICS.tag -> ORGANICS
            METAL.tag -> METAL
            SILICON.tag -> SILICON
            PLASTIC.tag -> PLASTIC
            CHIP.tag -> CHIP
            ACCUMULATOR.tag -> ACCUMULATOR
            else -> throw java.io.IOException("Unexpected tag value")
            }
        }
    }
}