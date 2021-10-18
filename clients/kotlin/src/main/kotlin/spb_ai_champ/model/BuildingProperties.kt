package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
class BuildingProperties {
    /**
     * TODO - Document
     */
    var buildResources: MutableMap<spb_ai_champ.model.Resource, Int>
    /**
     * TODO - Document
     */
    var maxHealth: Int
    /**
     * TODO - Document
     */
    var maxWorkers: Int
    /**
     * TODO - Document
     */
    var workResources: MutableMap<spb_ai_champ.model.Resource, Int>
    /**
     * TODO - Document
     */
    var produceWorker: Boolean
    /**
     * TODO - Document
     */
    var produceResource: spb_ai_champ.model.Resource?
    /**
     * TODO - Document
     */
    var produceAmount: Int
    /**
     * TODO - Document
     */
    var produceScore: Int
    /**
     * TODO - Document
     */
    var harvest: Boolean
    /**
     * TODO - Document
     */
    var workAmount: Int

    constructor(buildResources: MutableMap<spb_ai_champ.model.Resource, Int>, maxHealth: Int, maxWorkers: Int, workResources: MutableMap<spb_ai_champ.model.Resource, Int>, produceWorker: Boolean, produceResource: spb_ai_champ.model.Resource?, produceAmount: Int, produceScore: Int, harvest: Boolean, workAmount: Int) {
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
            return BuildingProperties(buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount)
        }
    }
}