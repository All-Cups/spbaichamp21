package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player's specialty
 */
enum class Specialty private constructor(val tag: Int) {
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

    companion object {
        /**
         * Read Specialty from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Specialty {
            return when (StreamUtil.readInt(stream)) {
            LOGISTICS.tag -> LOGISTICS
            PRODUCTION.tag -> PRODUCTION
            COMBAT.tag -> COMBAT
            else -> throw java.io.IOException("Unexpected tag value")
            }
        }
    }
}