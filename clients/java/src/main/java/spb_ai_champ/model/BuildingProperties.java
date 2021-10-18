package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * Building properties
 */
public class BuildingProperties {
    /**
     * Building type that this building can be upgraded from
     */
    private spb_ai_champ.model.BuildingType baseBuilding;

    /**
     * Building type that this building can be upgraded from
     */
    public spb_ai_champ.model.BuildingType getBaseBuilding() {
        return baseBuilding;
    }

    /**
     * Building type that this building can be upgraded from
     */
    public void setBaseBuilding(spb_ai_champ.model.BuildingType value) {
        this.baseBuilding = value;
    }
    /**
     * Resources required for building
     */
    private java.util.Map<spb_ai_champ.model.Resource, Integer> buildResources;

    /**
     * Resources required for building
     */
    public java.util.Map<spb_ai_champ.model.Resource, Integer> getBuildResources() {
        return buildResources;
    }

    /**
     * Resources required for building
     */
    public void setBuildResources(java.util.Map<spb_ai_champ.model.Resource, Integer> value) {
        this.buildResources = value;
    }
    /**
     * Max health points of the building
     */
    private int maxHealth;

    /**
     * Max health points of the building
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Max health points of the building
     */
    public void setMaxHealth(int value) {
        this.maxHealth = value;
    }
    /**
     * Max number of workers in the building
     */
    private int maxWorkers;

    /**
     * Max number of workers in the building
     */
    public int getMaxWorkers() {
        return maxWorkers;
    }

    /**
     * Max number of workers in the building
     */
    public void setMaxWorkers(int value) {
        this.maxWorkers = value;
    }
    /**
     * Resources required to start another task
     */
    private java.util.Map<spb_ai_champ.model.Resource, Integer> workResources;

    /**
     * Resources required to start another task
     */
    public java.util.Map<spb_ai_champ.model.Resource, Integer> getWorkResources() {
        return workResources;
    }

    /**
     * Resources required to start another task
     */
    public void setWorkResources(java.util.Map<spb_ai_champ.model.Resource, Integer> value) {
        this.workResources = value;
    }
    /**
     * Whether performing a task spawn new workers
     */
    private boolean produceWorker;

    /**
     * Whether performing a task spawn new workers
     */
    public boolean isProduceWorker() {
        return produceWorker;
    }

    /**
     * Whether performing a task spawn new workers
     */
    public void setProduceWorker(boolean value) {
        this.produceWorker = value;
    }
    /**
     * Resource produced when performing a task
     */
    private spb_ai_champ.model.Resource produceResource;

    /**
     * Resource produced when performing a task
     */
    public spb_ai_champ.model.Resource getProduceResource() {
        return produceResource;
    }

    /**
     * Resource produced when performing a task
     */
    public void setProduceResource(spb_ai_champ.model.Resource value) {
        this.produceResource = value;
    }
    /**
     * Amount of resources/workers produced when performing one task
     */
    private int produceAmount;

    /**
     * Amount of resources/workers produced when performing one task
     */
    public int getProduceAmount() {
        return produceAmount;
    }

    /**
     * Amount of resources/workers produced when performing one task
     */
    public void setProduceAmount(int value) {
        this.produceAmount = value;
    }
    /**
     * Score points given for performing one task
     */
    private int produceScore;

    /**
     * Score points given for performing one task
     */
    public int getProduceScore() {
        return produceScore;
    }

    /**
     * Score points given for performing one task
     */
    public void setProduceScore(int value) {
        this.produceScore = value;
    }
    /**
     * Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
     */
    private boolean harvest;

    /**
     * Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
     */
    public boolean isHarvest() {
        return harvest;
    }

    /**
     * Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
     */
    public void setHarvest(boolean value) {
        this.harvest = value;
    }
    /**
     * Amount of work needed to finish one task
     */
    private int workAmount;

    /**
     * Amount of work needed to finish one task
     */
    public int getWorkAmount() {
        return workAmount;
    }

    /**
     * Amount of work needed to finish one task
     */
    public void setWorkAmount(int value) {
        this.workAmount = value;
    }

    public BuildingProperties(spb_ai_champ.model.BuildingType baseBuilding, java.util.Map<spb_ai_champ.model.Resource, Integer> buildResources, int maxHealth, int maxWorkers, java.util.Map<spb_ai_champ.model.Resource, Integer> workResources, boolean produceWorker, spb_ai_champ.model.Resource produceResource, int produceAmount, int produceScore, boolean harvest, int workAmount) {
        this.baseBuilding = baseBuilding;
        this.buildResources = buildResources;
        this.maxHealth = maxHealth;
        this.maxWorkers = maxWorkers;
        this.workResources = workResources;
        this.produceWorker = produceWorker;
        this.produceResource = produceResource;
        this.produceAmount = produceAmount;
        this.produceScore = produceScore;
        this.harvest = harvest;
        this.workAmount = workAmount;
    }

    /**
     * Read BuildingProperties from input stream
     */
    public static BuildingProperties readFrom(java.io.InputStream stream) throws java.io.IOException {
        spb_ai_champ.model.BuildingType baseBuilding;
        if (StreamUtil.readBoolean(stream)) {
            baseBuilding = spb_ai_champ.model.BuildingType.readFrom(stream);
        } else {
            baseBuilding = null;
        }
        java.util.Map<spb_ai_champ.model.Resource, Integer> buildResources;
        int buildResourcesSize = StreamUtil.readInt(stream);
        buildResources = new java.util.HashMap<>(buildResourcesSize);
        for (int buildResourcesIndex = 0; buildResourcesIndex < buildResourcesSize; buildResourcesIndex++) {
            spb_ai_champ.model.Resource buildResourcesKey;
            buildResourcesKey = spb_ai_champ.model.Resource.readFrom(stream);
            int buildResourcesValue;
            buildResourcesValue = StreamUtil.readInt(stream);
            buildResources.put(buildResourcesKey, buildResourcesValue);
        }
        int maxHealth;
        maxHealth = StreamUtil.readInt(stream);
        int maxWorkers;
        maxWorkers = StreamUtil.readInt(stream);
        java.util.Map<spb_ai_champ.model.Resource, Integer> workResources;
        int workResourcesSize = StreamUtil.readInt(stream);
        workResources = new java.util.HashMap<>(workResourcesSize);
        for (int workResourcesIndex = 0; workResourcesIndex < workResourcesSize; workResourcesIndex++) {
            spb_ai_champ.model.Resource workResourcesKey;
            workResourcesKey = spb_ai_champ.model.Resource.readFrom(stream);
            int workResourcesValue;
            workResourcesValue = StreamUtil.readInt(stream);
            workResources.put(workResourcesKey, workResourcesValue);
        }
        boolean produceWorker;
        produceWorker = StreamUtil.readBoolean(stream);
        spb_ai_champ.model.Resource produceResource;
        if (StreamUtil.readBoolean(stream)) {
            produceResource = spb_ai_champ.model.Resource.readFrom(stream);
        } else {
            produceResource = null;
        }
        int produceAmount;
        produceAmount = StreamUtil.readInt(stream);
        int produceScore;
        produceScore = StreamUtil.readInt(stream);
        boolean harvest;
        harvest = StreamUtil.readBoolean(stream);
        int workAmount;
        workAmount = StreamUtil.readInt(stream);
        return new BuildingProperties(baseBuilding, buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount);
    }

    /**
     * Write BuildingProperties to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        if (baseBuilding == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, baseBuilding.tag);
        }
        StreamUtil.writeInt(stream, buildResources.size());
        for (java.util.Map.Entry<spb_ai_champ.model.Resource, Integer> buildResourcesEntry : buildResources.entrySet()) {
            spb_ai_champ.model.Resource buildResourcesKey = buildResourcesEntry.getKey();
            StreamUtil.writeInt(stream, buildResourcesKey.tag);
            int buildResourcesValue = buildResourcesEntry.getValue();
            StreamUtil.writeInt(stream, buildResourcesValue);
        }
        StreamUtil.writeInt(stream, maxHealth);
        StreamUtil.writeInt(stream, maxWorkers);
        StreamUtil.writeInt(stream, workResources.size());
        for (java.util.Map.Entry<spb_ai_champ.model.Resource, Integer> workResourcesEntry : workResources.entrySet()) {
            spb_ai_champ.model.Resource workResourcesKey = workResourcesEntry.getKey();
            StreamUtil.writeInt(stream, workResourcesKey.tag);
            int workResourcesValue = workResourcesEntry.getValue();
            StreamUtil.writeInt(stream, workResourcesValue);
        }
        StreamUtil.writeBoolean(stream, produceWorker);
        if (produceResource == null) {
            StreamUtil.writeBoolean(stream, false);
        } else {
            StreamUtil.writeBoolean(stream, true);
            StreamUtil.writeInt(stream, produceResource.tag);
        }
        StreamUtil.writeInt(stream, produceAmount);
        StreamUtil.writeInt(stream, produceScore);
        StreamUtil.writeBoolean(stream, harvest);
        StreamUtil.writeInt(stream, workAmount);
    }

    /**
     * Get string representation of BuildingProperties
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("BuildingProperties { ");
        stringBuilder.append("baseBuilding: ");
        stringBuilder.append(String.valueOf(baseBuilding));
        stringBuilder.append(", ");
        stringBuilder.append("buildResources: ");
        stringBuilder.append(String.valueOf(buildResources));
        stringBuilder.append(", ");
        stringBuilder.append("maxHealth: ");
        stringBuilder.append(String.valueOf(maxHealth));
        stringBuilder.append(", ");
        stringBuilder.append("maxWorkers: ");
        stringBuilder.append(String.valueOf(maxWorkers));
        stringBuilder.append(", ");
        stringBuilder.append("workResources: ");
        stringBuilder.append(String.valueOf(workResources));
        stringBuilder.append(", ");
        stringBuilder.append("produceWorker: ");
        stringBuilder.append(String.valueOf(produceWorker));
        stringBuilder.append(", ");
        stringBuilder.append("produceResource: ");
        stringBuilder.append(String.valueOf(produceResource));
        stringBuilder.append(", ");
        stringBuilder.append("produceAmount: ");
        stringBuilder.append(String.valueOf(produceAmount));
        stringBuilder.append(", ");
        stringBuilder.append("produceScore: ");
        stringBuilder.append(String.valueOf(produceScore));
        stringBuilder.append(", ");
        stringBuilder.append("harvest: ");
        stringBuilder.append(String.valueOf(harvest));
        stringBuilder.append(", ");
        stringBuilder.append("workAmount: ");
        stringBuilder.append(String.valueOf(workAmount));
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}