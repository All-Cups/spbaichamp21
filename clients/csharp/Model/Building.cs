namespace SpbAiChamp.Model
{
    /// <summary>
    /// A building
    /// </summary>
    public struct Building
    {
        /// <summary>
        /// Building's type
        /// </summary>
        public Model.BuildingType BuildingType { get; set; }
        /// <summary>
        /// Current health
        /// </summary>
        public int Health { get; set; }
        /// <summary>
        /// Amount of work done for current task
        /// </summary>
        public int WorkDone { get; set; }
    
        public Building(Model.BuildingType buildingType, int health, int workDone)
        {
            this.BuildingType = buildingType;
            this.Health = health;
            this.WorkDone = workDone;
        }
    
        /// <summary> Read Building from reader </summary>
        public static Building ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Building();
            result.BuildingType = BuildingTypeHelper.ReadFrom(reader);
            result.Health = reader.ReadInt32();
            result.WorkDone = reader.ReadInt32();
            return result;
        }
    
        /// <summary> Write Building to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write((int) (BuildingType));
            writer.Write(Health);
            writer.Write(WorkDone);
        }
    
        /// <summary> Get string representation of Building </summary>
        public override string ToString() {
            string stringResult = "Building { ";
            stringResult += "BuildingType: ";
            stringResult += BuildingType.ToString();
            stringResult += ", ";
            stringResult += "Health: ";
            stringResult += Health.ToString();
            stringResult += ", ";
            stringResult += "WorkDone: ";
            stringResult += WorkDone.ToString();
            stringResult += " }";
            return stringResult;
        }
    }
}