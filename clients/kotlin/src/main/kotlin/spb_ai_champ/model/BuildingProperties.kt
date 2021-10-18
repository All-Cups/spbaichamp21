package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * Building properties
 */
class BuildingProperties {
    /**
     * Building type that this building can be upgraded from
     */
    var baseBuilding: spb_ai_champ.model.BuildingType?
    /**
     * Resources required for building
     */
    var buildResources: MutableMap<spb_ai_champ.model.Resource, Int>
    /**
     * Max health points of the building
     */
    var maxHealth: Int
    /**
     * Max number of workers in the building
     */
    var maxWorkers: Int
    /**
     * Resources required to start another task
     */
    var workResources: MutableMap<spb_ai_champ.model.Resource, Int>
    /**
     * Whether performing a task spawn new workers
     */
    var produceWorker: Boolean
    /**
     * Resource produced when performing a task
     */
    var produceResource: spb_ai_champ.model.Resource?
    /**
     * Amount of resources/workers produced when performing one task
     */
    var produceAmount: Int
    /**
     * Score points given for performing one task
     */
    var produceScore: Int
    /**
     * Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
     */
    var harvest: Boolean
    /**
     * Amount of work needed to finish one task
     */
    var workAmount: Int

    constructor(baseBuilding: spb_ai_champ.model.BuildingType?, buildResources: MutableMap<spb_ai_champ.model.Resource, Int>, maxHealth: Int, maxWorkers: Int, workResources: MutableMap<spb_ai_champ.model.Resource, Int>, produceWorker: Boolean, produceResource: spb_ai_champ.model.Resource?, produceAmount: Int, produceScore: Int, harvest: Boolean, workAmount: Int) {
        this.baseBuilding = baseBuilding
        this.buildResources = buildResources
        this.maxHealth = maxHealth
        this.maxWorkers = maxWorkers
        this.workResources = workResources
        this.produceWorker = produceWorker
        this.produceResource = produceResource
        this.produceAmount = produceAmount
        this.produceScore = produceScore
        this.harvest = harvest
        this.workAmount = workAmount
    }

    /**
     * Write BuildingProperties to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        val baseBuildingValue = baseBuilding
        if (baseBuildingValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, baseBuildingValue.tag)
        }
        StreamUtil.writeInt(stream, buildResources.size)
        for (buildResourcesEntry in buildResources) {
            val buildResourcesKey = buildResourcesEntry.key
            StreamUtil.writeInt(stream, buildResourcesKey.tag)
            val buildResourcesValue = buildResourcesEntry.value
            StreamUtil.writeInt(stream, buildResourcesValue)
        }
        StreamUtil.writeInt(stream, maxHealth)
        StreamUtil.writeInt(stream, maxWorkers)
        StreamUtil.writeInt(stream, workResources.size)
        for (workResourcesEntry in workResources) {
            val workResourcesKey = workResourcesEntry.key
            StreamUtil.writeInt(stream, workResourcesKey.tag)
            val workResourcesValue = workResourcesEntry.value
            StreamUtil.writeInt(stream, workResourcesValue)
        }
        StreamUtil.writeBoolean(stream, produceWorker)
        val produceResourceValue = produceResource
        if (produceResourceValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, produceResourceValue.tag)
        }
        StreamUtil.writeInt(stream, produceAmount)
        StreamUtil.writeInt(stream, produceScore)
        StreamUtil.writeBoolean(stream, harvest)
        StreamUtil.writeInt(stream, workAmount)
    }

    /**
     * Get string representation of BuildingProperties
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("BuildingProperties { ")
        stringBuilder.append("baseBuilding: ")
        stringBuilder.append(baseBuilding)
        stringBuilder.append(", ")
        stringBuilder.append("buildResources: ")
        stringBuilder.append(buildResources)
        stringBuilder.append(", ")
        stringBuilder.append("maxHealth: ")
        stringBuilder.append(maxHealth)
        stringBuilder.append(", ")
        stringBuilder.append("maxWorkers: ")
        stringBuilder.append(maxWorkers)
        stringBuilder.append(", ")
        stringBuilder.append("workResources: ")
        stringBuilder.append(workResources)
        stringBuilder.append(", ")
        stringBuilder.append("produceWorker: ")
        stringBuilder.append(produceWorker)
        stringBuilder.append(", ")
        stringBuilder.append("produceResource: ")
        stringBuilder.append(produceResource)
        stringBuilder.append(", ")
        stringBuilder.append("produceAmount: ")
        stringBuilder.append(produceAmount)
        stringBuilder.append(", ")
        stringBuilder.append("produceScore: ")
        stringBuilder.append(produceScore)
        stringBuilder.append(", ")
        stringBuilder.append("harvest: ")
        stringBuilder.append(harvest)
        stringBuilder.append(", ")
        stringBuilder.append("workAmount: ")
        stringBuilder.append(workAmount)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read BuildingProperties from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): BuildingProperties {
            var baseBuilding: spb_ai_champ.model.BuildingType?
            if (StreamUtil.readBoolean(stream)) {
                baseBuilding = spb_ai_champ.model.BuildingType.readFrom(stream)
            } else {
                baseBuilding = null
            }
            var buildResources: MutableMap<spb_ai_champ.model.Resource, Int>
            val buildResourcesSize = StreamUtil.readInt(stream)
            buildResources = mutableMapOf();
            for (buildResourcesIndex in 0 until buildResourcesSize) {
                var buildResourcesKey: spb_ai_champ.model.Resource
                buildResourcesKey = spb_ai_champ.model.Resource.readFrom(stream)
                var buildResourcesValue: Int
                buildResourcesValue = StreamUtil.readInt(stream)
                buildResources.put(buildResourcesKey, buildResourcesValue)
            }
            var maxHealth: Int
            maxHealth = StreamUtil.readInt(stream)
            var maxWorkers: Int
            maxWorkers = StreamUtil.readInt(stream)
            var workResources: MutableMap<spb_ai_champ.model.Resource, Int>
            val workResourcesSize = StreamUtil.readInt(stream)
            workResources = mutableMapOf();
            for (workResourcesIndex in 0 until workResourcesSize) {
                var workResourcesKey: spb_ai_champ.model.Resource
                workResourcesKey = spb_ai_champ.model.Resource.readFrom(stream)
                var workResourcesValue: Int
                workResourcesValue = StreamUtil.readInt(stream)
                workResources.put(workResourcesKey, workResourcesValue)
            }
            var produceWorker: Boolean
            produceWorker = StreamUtil.readBoolean(stream)
            var produceResource: spb_ai_champ.model.Resource?
            if (StreamUtil.readBoolean(stream)) {
                produceResource = spb_ai_champ.model.Resource.readFrom(stream)
            } else {
                produceResource = null
            }
            var produceAmount: Int
            produceAmount = StreamUtil.readInt(stream)
            var produceScore: Int
            produceScore = StreamUtil.readInt(stream)
            var harvest: Boolean
            harvest = StreamUtil.readBoolean(stream)
            var workAmount: Int
            workAmount = StreamUtil.readInt(stream)
            return BuildingProperties(baseBuilding, buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount)
        }
    }
}