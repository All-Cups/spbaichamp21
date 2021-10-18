package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Resource type
 */
sealed abstract class Resource (val tag: Int) {
    /**
     * Write Resource to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, tag)
    }
}

object Resource {
    /**
     * Stone
     */
    case object STONE extends Resource(0)
    /**
     * Ore
     */
    case object ORE extends Resource(1)
    /**
     * Sand
     */
    case object SAND extends Resource(2)
    /**
     * Organics
     */
    case object ORGANICS extends Resource(3)
    /**
     * Metal
     */
    case object METAL extends Resource(4)
    /**
     * Silicon
     */
    case object SILICON extends Resource(5)
    /**
     * Plastic
     */
    case object PLASTIC extends Resource(6)
    /**
     * Chip
     */
    case object CHIP extends Resource(7)
    /**
     * Accumulator
     */
    case object ACCUMULATOR extends Resource(8)

    /**
     * Read Resource from input stream
     */
    def readFrom(stream: java.io.InputStream): Resource =
        StreamUtil.readInt(stream) match {
            case STONE.tag => STONE
            case ORE.tag => ORE
            case SAND.tag => SAND
            case ORGANICS.tag => ORGANICS
            case METAL.tag => METAL
            case SILICON.tag => SILICON
            case PLASTIC.tag => PLASTIC
            case CHIP.tag => CHIP
            case ACCUMULATOR.tag => ACCUMULATOR
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
}