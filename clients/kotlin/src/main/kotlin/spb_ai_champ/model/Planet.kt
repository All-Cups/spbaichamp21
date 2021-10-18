package spb_ai_champ.model

import spb_ai_champ.util.StreamUtil

/**
 * TODO - Document
 */
class Planet {
    /**
     * TODO - Document
     */
    var id: Int
    /**
     * TODO - Document
     */
    var x: Int
    /**
     * TODO - Document
     */
    var y: Int
    /**
     * TODO - Document
     */
    var harvestableResource: spb_ai_champ.model.Resource?
    /**
     * TODO - Document
     */
    var workerGroups: Array<spb_ai_champ.model.WorkerGroup>
    /**
     * TODO - Document
     */
    var resources: MutableMap<spb_ai_champ.model.Resource, Int>
    /**
     * TODO - Document
     */
    var building: spb_ai_champ.model.Building?

    constructor(id: Int, x: Int, y: Int, harvestableResource: spb_ai_champ.model.Resource?, workerGroups: Array<spb_ai_champ.model.WorkerGroup>, resources: MutableMap<spb_ai_champ.model.Resource, Int>, building: spb_ai_champ.model.Building?) {
        this.id = id
        this.x = x
        this.y = y
        this.harvestableResource = harvestableResource
        this.workerGroups = workerGroups
        this.resources = resources
        this.building = building
    }

    /**
     * Write Planet to output stream
     */
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, id)
        StreamUtil.writeInt(stream, x)
        StreamUtil.writeInt(stream, y)
        val harvestableResourceValue = harvestableResource
        if (harvestableResourceValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            StreamUtil.writeInt(stream, harvestableResourceValue.tag)
        }
        StreamUtil.writeInt(stream, workerGroups.size)
        for (workerGroupsElement in workerGroups) {
            workerGroupsElement.writeTo(stream)
        }
        StreamUtil.writeInt(stream, resources.size)
        for (resourcesEntry in resources) {
            val resourcesKey = resourcesEntry.key
            StreamUtil.writeInt(stream, resourcesKey.tag)
            val resourcesValue = resourcesEntry.value
            StreamUtil.writeInt(stream, resourcesValue)
        }
        val buildingValue = building
        if (buildingValue == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            buildingValue.writeTo(stream)
        }
    }

    /**
     * Get string representation of Planet
     */
    override fun toString(): String {
        var stringBuilder = StringBuilder("Planet { ")
        stringBuilder.append("id: ")
        stringBuilder.append(id)
        stringBuilder.append(", ")
        stringBuilder.append("x: ")
        stringBuilder.append(x)
        stringBuilder.append(", ")
        stringBuilder.append("y: ")
        stringBuilder.append(y)
        stringBuilder.append(", ")
        stringBuilder.append("harvestableResource: ")
        stringBuilder.append(harvestableResource)
        stringBuilder.append(", ")
        stringBuilder.append("workerGroups: ")
        stringBuilder.append("[ ")
        var workerGroupsIndex = 0
        for (workerGroupsElement in workerGroups) {
            if (workerGroupsIndex != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(workerGroupsElement)
            workerGroupsIndex++
        }
        stringBuilder.append(" ]")
        stringBuilder.append(", ")
        stringBuilder.append("resources: ")
        stringBuilder.append(resources)
        stringBuilder.append(", ")
        stringBuilder.append("building: ")
        stringBuilder.append(building)
        stringBuilder.append(" }")
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Read Planet from input stream
         */
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): Planet {
            var id: Int
            id = StreamUtil.readInt(stream)
            var x: Int
            x = StreamUtil.readInt(stream)
            var y: Int
            y = StreamUtil.readInt(stream)
            var harvestableResource: spb_ai_champ.model.Resource?
            if (StreamUtil.readBoolean(stream)) {
                harvestableResource = spb_ai_champ.model.Resource.readFrom(stream)
            } else {
                harvestableResource = null
            }
            var workerGroups: Array<spb_ai_champ.model.WorkerGroup>
            workerGroups = Array(StreamUtil.readInt(stream), {
                var workerGroupsElement: spb_ai_champ.model.WorkerGroup
                workerGroupsElement = spb_ai_champ.model.WorkerGroup.readFrom(stream)
                workerGroupsElement
            })
            var resources: MutableMap<spb_ai_champ.model.Resource, Int>
            val resourcesSize = StreamUtil.readInt(stream)
            resources = mutableMapOf();
            for (resourcesIndex in 0 until resourcesSize) {
                var resourcesKey: spb_ai_champ.model.Resource
                resourcesKey = spb_ai_champ.model.Resource.readFrom(stream)
                var resourcesValue: Int
                resourcesValue = StreamUtil.readInt(stream)
                resources.put(resourcesKey, resourcesValue)
            }
            var building: spb_ai_champ.model.Building?
            if (StreamUtil.readBoolean(stream)) {
                building = spb_ai_champ.model.Building.readFrom(stream)
            } else {
                building = null
            }
            return Planet(id, x, y, harvestableResource, workerGroups, resources, building)
        }
    }
}