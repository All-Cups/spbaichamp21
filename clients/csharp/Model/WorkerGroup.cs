namespace SpbAiChamp.Model
{
    /// <summary>
    /// TODO - Document
    /// </summary>
    public struct WorkerGroup
    {
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int PlayerIndex { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int Number { get; set; }
    
        public WorkerGroup(int playerIndex, int number)
        {
            this.PlayerIndex = playerIndex;
            this.Number = number;
        }
    
        /// <summary> Read WorkerGroup from reader </summary>
        public static WorkerGroup ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new WorkerGroup();
            result.PlayerIndex = reader.ReadInt32();
            result.Number = reader.ReadInt32();
            return result;
        }
    
        /// <summary> Write WorkerGroup to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(PlayerIndex);
            writer.Write(Number);
        }
    
        /// <summary> Get string representation of WorkerGroup </summary>
        public override string ToString() {
            string stringResult = "WorkerGroup { ";
            stringResult += "PlayerIndex: ";
            stringResult += PlayerIndex.ToString();
            stringResult += ", ";
            stringResult += "Number: ";
            stringResult += Number.ToString();
            stringResult += " }";
            return stringResult;
        }
    }
}