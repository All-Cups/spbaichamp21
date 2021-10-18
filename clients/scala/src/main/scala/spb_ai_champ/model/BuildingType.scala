package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
sealed abstract class BuildingType (val tag: Int) {
    /**
     * Write BuildingType to output stream
     */
    def writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, tag)
    }
}

object BuildingType {
    /**
     * TODO - Document
     */
    case object QUARRY extends BuildingType(0)
    /**
     * TODO - Document
     */
    case object MINES extends BuildingType(1)
    /**
     * TODO - Document
     */
    case object CAREER extends BuildingType(2)
    /**
     * TODO - Document
     */
    case object FARM extends BuildingType(3)
    /**
     * TODO - Document
     */
    case object FOUNDRY extends BuildingType(4)
    /**
     * TODO - Document
     */
    case object FURNACE extends BuildingType(5)
    /**
     * TODO - Document
     */
    case object BIOREACTOR extends BuildingType(6)
    /**
     * TODO - Document
     */
    case object CHIP_FACTORY extends BuildingType(7)
    /**
     * TODO - Document
     */
    case object ACCUMULATOR_FACTORY extends BuildingType(8)
    /**
     * TODO - Document
     */
    case object REPLICATOR extends BuildingType(9)

    /**
     * Read BuildingType from input stream
     */
    def readFrom(stream: java.io.InputStream): BuildingType =
        StreamUtil.readInt(stream) match {
            case QUARRY.tag => QUARRY
            case MINES.tag => MINES
            case CAREER.tag => CAREER
            case FARM.tag => FARM
            case FOUNDRY.tag => FOUNDRY
            case FURNACE.tag => FURNACE
            case BIOREACTOR.tag => BIOREACTOR
            case CHIP_FACTORY.tag => CHIP_FACTORY
            case ACCUMULATOR_FACTORY.tag => ACCUMULATOR_FACTORY
            case REPLICATOR.tag => REPLICATOR
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
}