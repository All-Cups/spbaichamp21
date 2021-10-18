package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Player's specialty
 */
sealed abstract class Specialty (val tag: Int) {
    /**
     * Write Specialty to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, tag)
    }
}

object Specialty {
    /**
     * Logistics. Increased travel distance
     */
    case object LOGISTICS extends Specialty(0)
    /**
     * Production. Increased work speed
     */
    case object PRODUCTION extends Specialty(1)
    /**
     * Combat. Increased damage
     */
    case object COMBAT extends Specialty(2)

    /**
     * Read Specialty from input stream
     */
    def readFrom(stream: java.io.InputStream): Specialty =
        StreamUtil.readInt(stream) match {
            case LOGISTICS.tag => LOGISTICS
            case PRODUCTION.tag => PRODUCTION
            case COMBAT.tag => COMBAT
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
}