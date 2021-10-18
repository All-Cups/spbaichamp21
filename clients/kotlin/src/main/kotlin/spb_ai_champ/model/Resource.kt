package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Resource type
 */
enum class Resource private constructor(val tag: Int) {
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