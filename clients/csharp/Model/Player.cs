namespace SpbAiChamp.Model
{
    /// <summary>
    /// TODO - Document
    /// </summary>
    public struct Player
    {
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int Score { get; set; }
    
        public Player(int score)
        {
            this.Score = score;
        }
    
        /// <summary> Read Player from reader </summary>
        public static Player ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Player();
            result.Score = reader.ReadInt32();
            return result;
        }
    
        /// <summary> Write Player to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Score);
        }
    
        /// <summary> Get string representation of Player </summary>
        public override string ToString() {
            string stringResult = "Player { ";
            stringResult += "Score: ";
            stringResult += Score.ToString();
            stringResult += " }";
            return stringResult;
        }
    }
}