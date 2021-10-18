namespace SpbAiChamp.Model
{
    /// <summary>
    /// Building order
    /// </summary>
    public struct BuildingAction
    {
        /// <summary>
        /// Id of the planet where the action needs to be performed
        /// </summary>
        public int Planet { get; set; }
        /// <summary>
        /// Type of a building to build. If absent, current building will be destroyed
        /// </summary>
        public Model.BuildingType? BuildingType { get; set; }
    
        public BuildingAction(int planet, Model.BuildingType? buildingType)
        {
            this.Planet = planet;
            this.BuildingType = buildingType;
        }
    
        /// <summary> Read BuildingAction from reader </summary>
        public static BuildingAction ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new BuildingAction();
            result.Planet = reader.ReadInt32();
            if (reader.ReadBoolean())
            {
                result.BuildingType = BuildingTypeHelper.ReadFrom(reader);
            } else
            {
                result.BuildingType = null;
            }
            return result;
        }
    
        /// <summary> Write BuildingAction to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Planet);
            if (!BuildingType.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (BuildingType.Value));
            }
        }
    
        /// <summary> Get string representation of BuildingAction </summary>
        public override string ToString() {
            string stringResult = "BuildingAction { ";
            stringResult += "Planet: ";
            stringResult += Planet.ToString();
            stringResult += ", ";
            stringResult += "BuildingType: ";
            if (!BuildingType.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += BuildingType.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}