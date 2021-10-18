namespace SpbAiChamp.Model
{
    /// <summary>
    /// TODO - Document
    /// </summary>
    public struct Planet
    {
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int Id { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int X { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int Y { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public Model.Resource? HarvestableResource { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public Model.WorkerGroup[] WorkerGroups { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public System.Collections.Generic.IDictionary<Model.Resource, int> Resources { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public Model.Building? Building { get; set; }
    
        public Planet(int id, int x, int y, Model.Resource? harvestableResource, Model.WorkerGroup[] workerGroups, System.Collections.Generic.IDictionary<Model.Resource, int> resources, Model.Building? building)
        {
            this.Id = id;
            this.X = x;
            this.Y = y;
            this.HarvestableResource = harvestableResource;
            this.WorkerGroups = workerGroups;
            this.Resources = resources;
            this.Building = building;
        }
    
        /// <summary> Read Planet from reader </summary>
        public static Planet ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Planet();
            result.Id = reader.ReadInt32();
            result.X = reader.ReadInt32();
            result.Y = reader.ReadInt32();
            if (reader.ReadBoolean())
            {
                result.HarvestableResource = ResourceHelper.ReadFrom(reader);
            } else
            {
                result.HarvestableResource = null;
            }
            result.WorkerGroups = new Model.WorkerGroup[reader.ReadInt32()];
            for (int workerGroupsIndex = 0; workerGroupsIndex < result.WorkerGroups.Length; workerGroupsIndex++)
            {
                result.WorkerGroups[workerGroupsIndex] = Model.WorkerGroup.ReadFrom(reader);
            }
            int resourcesSize = reader.ReadInt32();
            result.Resources = new System.Collections.Generic.Dictionary<Model.Resource, int>(resourcesSize);
            for (int resourcesIndex = 0; resourcesIndex < resourcesSize; resourcesIndex++)
            {
                Model.Resource resourcesKey;
                int resourcesValue;
                resourcesKey = ResourceHelper.ReadFrom(reader);
                resourcesValue = reader.ReadInt32();
                result.Resources.Add(resourcesKey, resourcesValue);
            }
            if (reader.ReadBoolean())
            {
                result.Building = Model.Building.ReadFrom(reader);
            } else
            {
                result.Building = null;
            }
            return result;
        }
    
        /// <summary> Write Planet to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Id);
            writer.Write(X);
            writer.Write(Y);
            if (!HarvestableResource.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (HarvestableResource.Value));
            }
            writer.Write(WorkerGroups.Length);
            foreach (var workerGroupsElement in WorkerGroups)
            {
                workerGroupsElement.WriteTo(writer);
            }
            writer.Write(Resources.Count);
            foreach (var resourcesEntry in Resources)
            {
                var resourcesKey = resourcesEntry.Key;
                var resourcesValue = resourcesEntry.Value;
                writer.Write((int) (resourcesKey));
                writer.Write(resourcesValue);
            }
            if (!Building.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                Building.Value.WriteTo(writer);
            }
        }
    
        /// <summary> Get string representation of Planet </summary>
        public override string ToString() {
            string stringResult = "Planet { ";
            stringResult += "Id: ";
            stringResult += Id.ToString();
            stringResult += ", ";
            stringResult += "X: ";
            stringResult += X.ToString();
            stringResult += ", ";
            stringResult += "Y: ";
            stringResult += Y.ToString();
            stringResult += ", ";
            stringResult += "HarvestableResource: ";
            if (!HarvestableResource.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += HarvestableResource.Value.ToString();
            }
            stringResult += ", ";
            stringResult += "WorkerGroups: ";
            stringResult += "[ ";
            int workerGroupsIndex = 0;
            foreach (var workerGroupsElement in WorkerGroups)
            {
                if (workerGroupsIndex != 0) {
                    stringResult += ", ";
                }
                stringResult += workerGroupsElement.ToString();
                workerGroupsIndex++;
            }
            stringResult += " ]";
            stringResult += ", ";
            stringResult += "Resources: ";
            stringResult += "{ ";
            int resourcesIndex = 0;
            foreach (var resourcesEntry in Resources)
            {
                if (resourcesIndex != 0) {
                    stringResult += ", ";
                }
                var resourcesKey = resourcesEntry.Key;
                stringResult += resourcesKey.ToString();
                stringResult += ": ";
                var resourcesValue = resourcesEntry.Value;
                stringResult += resourcesValue.ToString();
                resourcesIndex++;
            }
            stringResult += " }";
            stringResult += ", ";
            stringResult += "Building: ";
            if (!Building.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += Building.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}