namespace SpbAiChamp.Model
{
    /// <summary>
    /// TODO - Document
    /// </summary>
    public struct DebugData
    {
    
        /// <summary> Read DebugData from reader </summary>
        public static DebugData ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new DebugData();
            return result;
        }
    
        /// <summary> Write DebugData to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
        }
    
        /// <summary> Get string representation of DebugData </summary>
        public override string ToString() {
            string stringResult = "DebugData { ";
            stringResult += " }";
            return stringResult;
        }
    }
}