package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class Planet {
    /**
     * TODO - Document
     */
    private int id;

    /**
     * TODO - Document
     */
    public int getId() {
        return id;
    }

    /**
     * TODO - Document
     */
    public void setId(int value) {
        this.id = value;
    }
    /**
     * TODO - Document
     */
    private int x;

    /**
     * TODO - Document
     */
    public int getX() {
        return x;
    }

    /**
     * TODO - Document
     */
    public void setX(int value) {
        this.x = value;
    }
    /**
     * TODO - Document
     */
    private int y;

    /**
     * TODO - Document
     */
    public int getY() {
        return y;
    }

    /**
     * TODO - Document
     */
    public void setY(int value) {
        this.y = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Resource harvestableResource;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Resource getHarvestableResource() {
        return harvestableResource;
    }

    /**
     * TODO - Document
     */
    public void setHarvestableResource(spb_ai_champ.model.Resource value) {
        this.harvestableResource = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.WorkerGroup[] workerGroups;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.WorkerGroup[] getWorkerGroups() {
        return workerGroups;
    }

    /**
     * TODO - Document
     */
    public void setWorkerGroups(spb_ai_champ.model.WorkerGroup[] value) {
        this.workerGroups = value;
    }
    /**
     * TODO - Document
     */
    private java.util.Map<spb_ai_champ.model.Resource, Integer> resources;

    /**
     * TODO - Document
     */
    public java.util.Map<spb_ai_champ.model.Resource, Integer> getResources() {
        return resources;
    }

    /**
     * TODO - Document
     */
    public void setResources(java.util.Map<spb_ai_champ.model.Resource, Integer> value) {
        this.resources = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Building building;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Building getBuilding() {
        return building;
    }

    /**
     * TODO - Document
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