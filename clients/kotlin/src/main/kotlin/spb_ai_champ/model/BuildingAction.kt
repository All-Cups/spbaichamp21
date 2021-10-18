package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Building order
 */
class BuildingAction {
    /**
     * Id of the planet where the action needs to be performed
     */
    var planet: Int
    /**
     * Type of a building to build. If absent, current building will be destroyed
     */
    var buildingType: spb_ai_champ.model.BuildingType?

    constructor(planet: Int, buildingType: spb_ai_champ.model.BuildingType?) {
        this.planet = planet
        this.buildingType = buildingType
    }

    /**
     * Write BuildingAction to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, planet)
        val buildingTypeValue = buildingType
        if (buildingTypeValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, buildingTypeValue.tag)
        }
    }

    /**
     * Get string representation of BuildingAction
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("BuildingAction { ")
        stringBuilder.append("planet: ")
        stringBuilder.append(planet)
        stringBuilder.append(", ")
        stringBuilder.append("buildingType: ")
        stringBuilder.append(buildingType)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read BuildingAction from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): BuildingAction {
            var planet: Int
            planet = StreamUtil.readInt(stream)
            var buildingType: spb_ai_champ.model.BuildingType?
            if (StreamUtil.readBoolean(stream)) {
                buildingType = spb_ai_champ.model.BuildingType.readFrom(stream)
            } else {
                buildingType = null
            }
            return BuildingAction(planet, buildingType)
        }
    }
}