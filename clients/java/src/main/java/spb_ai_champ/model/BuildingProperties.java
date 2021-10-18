package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class BuildingProperties {
    /**
     * TODO - Document
     */
    private java.util.Map<spb_ai_champ.model.Resource, Integer> buildResources;

    /**
     * TODO - Document
     */
    public java.util.Map<spb_ai_champ.model.Resource, Integer> getBuildResources() {
        return buildResources;
    }

    /**
     * TODO - Document
     */
    public void setBuildResources(java.util.Map<spb_ai_champ.model.Resource, Integer> value) {
        this.buildResources = value;
    }
    /**
     * TODO - Document
     */
    private int maxHealth;

    /**
     * TODO - Document
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * TODO - Document
     */
    public void setMaxHealth(int value) {
        this.maxHealth = value;
    }
    /**
     * TODO - Document
     */
    private int maxWorkers;

    /**
     * TODO - Document
     */
    public int getMaxWorkers() {
        return maxWorkers;
    }

    /**
     * TODO - Document
     */
    public void setMaxWorkers(int value) {
        this.maxWorkers = value;
    }
    /**
     * TODO - Document
     */
    private java.util.Map<spb_ai_champ.model.Resource, Integer> workResources;

    /**
     * TODO - Document
     */
    public java.util.Map<spb_ai_champ.model.Resource, Integer> getWorkResources() {
        return workResources;
    }

    /**
     * TODO - Document
     */
    public void setWorkResources(java.util.Map<spb_ai_champ.model.Resource, Integer> value) {
        this.workResources = value;
    }
    /**
     * TODO - Document
     */
    private boolean produceWorker;

    /**
     * TODO - Document
     */
    public boolean isProduceWorker() {
        return produceWorker;
    }

    /**
     * TODO - Document
     */
    public void setProduceWorker(boolean value) {
        this.produceWorker = value;
    }
    /**
     * TODO - Document
     */
    private spb_ai_champ.model.Resource produceResource;

    /**
     * TODO - Document
     */
    public spb_ai_champ.model.Resource getProduceResource() {
        return produceResource;
    }

    /**
     * TODO - Document
     */
    public void setProduceResource(spb_ai_champ.model.Resource value) {
        this.produceResource = value;
    }
    /**
     * TODO - Document
     */
    private int produceAmount;

    /**
     * TODO - Document
     */
    public int getProduceAmount() {
        return produceAmount;
    }

    /**
     * TODO - Document
     */
    public void setProduceAmount(int value) {
        this.produceAmount = value;
    }
    /**
     * TODO - Document
     */
    private int produceScore;

    /**
     * TODO - Document
     */
    public int getProduceScore() {
        return produceScore;
    }

    /**
     * TODO - Document
     */
    public void setProduceScore(int value) {
        this.produceScore = value;
    }
    /**
     * TODO - Document
     */
    private boolean harvest;

    /**
     * TODO - Document
     */
    public boolean isHarvest() {
        return harvest;
    }

    /**
     * TODO - Document
     */
    public void setHarvest(boolean value) {
        this.harvest = value;
    }
    /**
     * TODO - Document
     */
    private int workAmount;

    /**
     * TODO - Document
     */
    public int getWorkAmount() {
        return workAmount;
    }

    /**
     * TODO - Document
     */
    public void setWorkAmount(int value) {
        this.workAmount = value;
    }

    public BuildingProperties(java.util.Map<spb_ai_champ.model.Resource, Integer> buildResources, int maxHealth, int maxWorkers, java.util.Map<spb_ai_champ.model.Resource, Integer> workResources, boolean produceWorker, spb_ai_champ.model.Resource produceResource, int produceAmount, int produceScore, boolean harvest, int workAmount) {
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
        return new BuildingProperties(buildResources, maxHealth, maxWorkers, workResources, produceWorker, produceResource, produceAmount, produceScore, harvest, workAmount);
    }

    /**
     * Write BuildingProperties to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
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