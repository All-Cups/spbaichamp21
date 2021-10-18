namespace SpbAiChamp.Model
{
    /// <summary>
    /// Building properties
    /// </summary>
    public struct BuildingProperties
    {
        /// <summary>
        /// Building type that this building can be upgraded from
        /// </summary>
        public Model.BuildingType? BaseBuilding { get; set; }
        /// <summary>
        /// Resources required for building
        /// </summary>
        public System.Collections.Generic.IDictionary<Model.Resource, int> BuildResources { get; set; }
        /// <summary>
        /// Max health points of the building
        /// </summary>
        public int MaxHealth { get; set; }
        /// <summary>
        /// Max number of workers in the building
        /// </summary>
        public int MaxWorkers { get; set; }
        /// <summary>
        /// Resources required to start another task
        /// </summary>
        public System.Collections.Generic.IDictionary<Model.Resource, int> WorkResources { get; set; }
        /// <summary>
        /// Whether performing a task spawn new workers
        /// </summary>
        public bool ProduceWorker { get; set; }
        /// <summary>
        /// Resource produced when performing a task
        /// </summary>
        public Model.Resource? ProduceResource { get; set; }
        /// <summary>
        /// Amount of resources/workers produced when performing one task
        /// </summary>
        public int ProduceAmount { get; set; }
        /// <summary>
        /// Score points given for performing one task
        /// </summary>
        public int ProduceScore { get; set; }
        /// <summary>
        /// Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
        /// </summary>
        public bool Harvest { get; set; }
        /// <summary>
        /// Amount of work needed to finish one task
        /// </summary>
        public int WorkAmount { get; set; }
    
        public BuildingProperties(Model.BuildingType? baseBuilding, System.Collections.Generic.IDictionary<Model.Resource, int> buildResources, int maxHealth, int maxWorkers, System.Collections.Generic.IDictionary<Model.Resource, int> workResources, bool produceWorker, Model.Resource? produceResource, int produceAmount, int produceScore, bool harvest, int workAmount)
        {
            this.BaseBuilding = baseBuilding;
            this.BuildResources = buildResources;
            this.MaxHealth = maxHealth;
            this.MaxWorkers = maxWorkers;
            this.WorkResources = workResources;
            this.ProduceWorker = produceWorker;
            this.ProduceResource = produceResource;
            this.ProduceAmount = produceAmount;
            this.ProduceScore = produceScore;
            this.Harvest = harvest;
            this.WorkAmount = workAmount;
        }
    
        /// <summary> Read BuildingProperties from reader </summary>
        public static BuildingProperties ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new BuildingProperties();
            if (reader.ReadBoolean())
            {
                result.BaseBuilding = BuildingTypeHelper.ReadFrom(reader);
            } else
            {
                result.BaseBuilding = null;
            }
            int buildResourcesSize = reader.ReadInt32();
            result.BuildResources = new System.Collections.Generic.Dictionary<Model.Resource, int>(buildResourcesSize);
            for (int buildResourcesIndex = 0; buildResourcesIndex < buildResourcesSize; buildResourcesIndex++)
            {
                Model.Resource buildResourcesKey;
                int buildResourcesValue;
                buildResourcesKey = ResourceHelper.ReadFrom(reader);
                buildResourcesValue = reader.ReadInt32();
                result.BuildResources.Add(buildResourcesKey, buildResourcesValue);
            }
            result.MaxHealth = reader.ReadInt32();
            result.MaxWorkers = reader.ReadInt32();
            int workResourcesSize = reader.ReadInt32();
            result.WorkResources = new System.Collections.Generic.Dictionary<Model.Resource, int>(workResourcesSize);
            for (int workResourcesIndex = 0; workResourcesIndex < workResourcesSize; workResourcesIndex++)
            {
                Model.Resource workResourcesKey;
                int workResourcesValue;
                workResourcesKey = ResourceHelper.ReadFrom(reader);
                workResourcesValue = reader.ReadInt32();
                result.WorkResources.Add(workResourcesKey, workResourcesValue);
            }
            result.ProduceWorker = reader.ReadBoolean();
            if (reader.ReadBoolean())
            {
                result.ProduceResource = ResourceHelper.ReadFrom(reader);
            } else
            {
                result.ProduceResource = null;
            }
            result.ProduceAmount = reader.ReadInt32();
            result.ProduceScore = reader.ReadInt32();
            result.Harvest = reader.ReadBoolean();
            result.WorkAmount = reader.ReadInt32();
            return result;
        }
    
        /// <summary> Write BuildingProperties to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            if (!BaseBuilding.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (BaseBuilding.Value));
            }
            writer.Write(BuildResources.Count);
            foreach (var buildResourcesEntry in BuildResources)
            {
                var buildResourcesKey = buildResourcesEntry.Key;
                var buildResourcesValue = buildResourcesEntry.Value;
                writer.Write((int) (buildResourcesKey));
                writer.Write(buildResourcesValue);
            }
            writer.Write(MaxHealth);
            writer.Write(MaxWorkers);
            writer.Write(WorkResources.Count);
            foreach (var workResourcesEntry in WorkResources)
            {
                var workResourcesKey = workResourcesEntry.Key;
                var workResourcesValue = workResourcesEntry.Value;
                writer.Write((int) (workResourcesKey));
                writer.Write(workResourcesValue);
            }
            writer.Write(ProduceWorker);
            if (!ProduceResource.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (ProduceResource.Value));
            }
            writer.Write(ProduceAmount);
            writer.Write(ProduceScore);
            writer.Write(Harvest);
            writer.Write(WorkAmount);
        }
    
        /// <summary> Get string representation of BuildingProperties </summary>
        public override string ToString() {
            string stringResult = "BuildingProperties { ";
            stringResult += "BaseBuilding: ";
            if (!BaseBuilding.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += BaseBuilding.Value.ToString();
            }
            stringResult += ", ";
            stringResult += "BuildResources: ";
            stringResult += "{ ";
            int buildResourcesIndex = 0;
            foreach (var buildResourcesEntry in BuildResources)
            {
                if (buildResourcesIndex != 0) {
                    stringResult += ", ";
                }
                var buildResourcesKey = buildResourcesEntry.Key;
                stringResult += buildResourcesKey.ToString();
                stringResult += ": ";
                var buildResourcesValue = buildResourcesEntry.Value;
                stringResult += buildResourcesValue.ToString();
                buildResourcesIndex++;
            }
            stringResult += " }";
            stringResult += ", ";
            stringResult += "MaxHealth: ";
            stringResult += MaxHealth.ToString();
            stringResult += ", ";
            stringResult += "MaxWorkers: ";
            stringResult += MaxWorkers.ToString();
            stringResult += ", ";
            stringResult += "WorkResources: ";
            stringResult += "{ ";
            int workResourcesIndex = 0;
            foreach (var workResourcesEntry in WorkResources)
            {
                if (workResourcesIndex != 0) {
                    stringResult += ", ";
                }
                var workResourcesKey = workResourcesEntry.Key;
                stringResult += workResourcesKey.ToString();
                stringResult += ": ";
                var workResourcesValue = workResourcesEntry.Value;
                stringResult += workResourcesValue.ToString();
                workResourcesIndex++;
            }
            stringResult += " }";
            stringResult += ", ";
            stringResult += "ProduceWorker: ";
            stringResult += ProduceWorker.ToString();
            stringResult += ", ";
            stringResult += "ProduceResource: ";
            if (!ProduceResource.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += ProduceResource.Value.ToString();
            }
            stringResult += ", ";
            stringResult += "ProduceAmount: ";
            stringResult += ProduceAmount.ToString();
            stringResult += ", ";
            stringResult += "ProduceScore: ";
            stringResult += ProduceScore.ToString();
            stringResult += ", ";
            stringResult += "Harvest: ";
            stringResult += Harvest.ToString();
            stringResult += ", ";
            stringResult += "WorkAmount: ";
            stringResult += WorkAmount.ToString();
            stringResult += " }";
            return stringResult;
        }
    }
}