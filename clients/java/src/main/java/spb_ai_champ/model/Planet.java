package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * A planet
 */
public class Planet {
    /**
     * Unique identifier of the planet
     */
    private int id;

    /**
     * Unique identifier of the planet
     */
    public int getId() {
        return id;
    }

    /**
     * Unique identifier of the planet
     */
    public void setId(int value) {
        this.id = value;
    }
    /**
     * X coordinate
     */
    private int x;

    /**
     * X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * X coordinate
     */
    public void setX(int value) {
        this.x = value;
    }
    /**
     * Y coordinate
     */
    private int y;

    /**
     * Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Y coordinate
     */
    public void setY(int value) {
        this.y = value;
    }
    /**
     * Resource that can be harvested on the planet
     */
    private spb_ai_champ.model.Resource harvestableResource;

    /**
     * Resource that can be harvested on the planet
     */
    public spb_ai_champ.model.Resource getHarvestableResource() {
        return harvestableResource;
    }

    /**
     * Resource that can be harvested on the planet
     */
    public void setHarvestableResource(spb_ai_champ.model.Resource value) {
        this.harvestableResource = value;
    }
    /**
     * List of worker groups
     */
    private spb_ai_champ.model.WorkerGroup[] workerGroups;

    /**
     * List of worker groups
     */
    public spb_ai_champ.model.WorkerGroup[] getWorkerGroups() {
        return workerGroups;
    }

    /**
     * List of worker groups
     */
    public void setWorkerGroups(spb_ai_champ.model.WorkerGroup[] value) {
        this.workerGroups = value;
    }
    /**
     * Resources stored on the planet
     */
    private java.util.Map<spb_ai_champ.model.Resource, Integer> resources;

    /**
     * Resources stored on the planet
     */
    public java.util.Map<spb_ai_champ.model.Resource, Integer> getResources() {
        return resources;
    }

    /**
     * Resources stored on the planet
     */
    public void setResources(java.util.Map<spb_ai_champ.model.Resource, Integer> value) {
        this.resources = value;
    }
    /**
     * Building on the planet
     */
    private spb_ai_champ.model.Building building;

    /**
     * Building on the planet
     */
    public spb_ai_champ.model.Building getBuilding() {
        return building;
    }

    /**
     * Building on the planet
     */
    public void setBuilding(spb_ai_champ.model.Building value) {
        this.building = value;
    }

    public Planet(int id, int x, int y, spb_ai_champ.model.Resource harvestableResource, spb_ai_champ.model.WorkerGroup[] workerGroups, java.util.Map<spb_ai_champ.model.Resource, Integer> resources, spb_ai_champ.model.Building building) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.harvestableResource = harvestableResource;
        this.workerGroups = workerGroups;
        this.resources = resources;
        this.building = building;
    }

    /**
     * Read Planet from input stream
     */
    public static Planet readFrom(java.io.InputStream stream) throws java.io.IOException {
        int id;
        id = StreamUtil.readInt(stream);
        int x;
        x = StreamUtil.readInt(stream);
        int y;
        y = StreamUtil.readInt(stream);
        spb_ai_champ.model.Resource harvestableResource;
        if (StreamUtil.readBoolean(stream)) {
            harvestableResource = spb_ai_champ.model.Resource.readFrom(stream);
        } else {
            harvestableResource = null;
        }
        spb_ai_champ.model.WorkerGroup[] workerGroups;
        workerGroups = new spb_ai_champ.model.WorkerGroup[StreamUtil.readInt(stream)];
        for (int workerGroupsIndex = 0; workerGroupsIndex < workerGroups.length; workerGroupsIndex++) {
            spb_ai_champ.model.WorkerGroup workerGroupsElement;
            workerGroupsElement = spb_ai_champ.model.WorkerGroup.readFrom(stream);
            workerGroups[workerGroupsIndex] = workerGroupsElement;
        }
        java.util.Map<spb_ai_champ.model.Resource, Integer> resources;
        int resourcesSize = StreamUtil.readInt(stream);
        resources = new java.util.HashMap<>(resourcesSize);
        for (int resourcesIndex = 0; resourcesIndex < resourcesSize; resourcesIndex++) {
            spb_ai_champ.model.Resource resourcesKey;
            resourcesKey = spb_ai_champ.model.Resource.readFrom(stream);
            int resourcesValue;
            resourcesValue = StreamUtil.readInt(stream);
            resources.put(resourcesKey, resourcesValue);
        }
        spb_ai_champ.model.Building building;
        if (StreamUtil.readBoolean(stream)) {
            building = spb_ai_champ.model.Building.readFrom(stream);
        } else {
            building = null;
        }
        return new Planet(id, x, y, harvestableResource, workerGroups, resources, building);
    }

    /**
     * Write Planet to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeInt(stream, id);
        StreamUtil.writeInt(stream, x);
        StreamUtil.writeInt(stream, y);
        if (harvestableResource == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, harvestableResource.tag);
        }
        StreamUtil.writeInt(stream, workerGroups.length);
        for (spb_ai_champ.model.WorkerGroup workerGroupsElement : workerGroups) {
            workerGroupsElement.writeTo(stream);
        }
        StreamUtil.writeInt(stream, resources.size());
        for (java.util.Map.Entry<spb_ai_champ.model.Resource, Integer> resourcesEntry : resources.entrySet()) {
            spb_ai_champ.model.Resource resourcesKey = resourcesEntry.getKey();
            StreamUtil.writeInt(stream, resourcesKey.tag);
            int resourcesValue = resourcesEntry.getValue();
            StreamUtil.writeInt(stream, resourcesValue);
        }
        if (building == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            building.writeTo(stream);
        }
    }

    /**
     * Get string representation of Planet
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Planet { ");
        stringBuilder.append("id: ");
        stringBuilder.append(String.valueOf(id));
        stringBuilder.append(", ");
        stringBuilder.append("x: ");
        stringBuilder.append(String.valueOf(x));
        stringBuilder.append(", ");
        stringBuilder.append("y: ");
        stringBuilder.append(String.valueOf(y));
        stringBuilder.append(", ");
        stringBuilder.append("harvestableResource: ");
        stringBuilder.append(String.valueOf(harvestableResource));
        stringBuilder.append(", ");
        stringBuilder.append("workerGroups: ");
        stringBuilder.append("[ ");
        for (int workerGroupsIndex = 0; workerGroupsIndex < workerGroups.length; workerGroupsIndex++) {
            if (workerGroupsIndex != 0) {
                stringBuilder.append(", ");
            }
            spb_ai_champ.model.WorkerGroup workerGroupsElement = workerGroups[workerGroupsIndex];
            stringBuilder.append(String.valueOf(workerGroupsElement));
        }
        stringBuilder.append(" ]");
        stringBuilder.append(", ");
        stringBuilder.append("resources: ");
        stringBuilder.append(String.valueOf(resources));
        stringBuilder.append(", ");
        stringBuilder.append("building: ");
        stringBuilder.append(String.valueOf(building));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}