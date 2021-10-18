namespace SpbAiChamp.Model
{
    /// <summary>
    /// Player (game participant)
    /// </summary>
    public struct Player
    {
        /// <summary>
        /// Team index
        /// </summary>
        public int TeamIndex { get; set; }
        /// <summary>
        /// Current score points
        /// </summary>
        public int Score { get; set; }
        /// <summary>
        /// Player's specialty
        /// </summary>
        public Model.Specialty? Specialty { get; set; }
    
        public Player(int teamIndex, int score, Model.Specialty? specialty)
        {
            this.TeamIndex = teamIndex;
            this.Score = score;
            this.Specialty = specialty;
        }
    
        /// <summary> Read Player from reader </summary>
        public static Player ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Player();
            result.TeamIndex = reader.ReadInt32();
            result.Score = reader.ReadInt32();
            if (reader.ReadBoolean())
            {
                result.Specialty = SpecialtyHelper.ReadFrom(reader);
            } else
            {
                result.Specialty = null;
            }
            return result;
        }
    
        /// <summary> Write Player to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(TeamIndex);
            writer.Write(Score);
            if (!Specialty.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (Specialty.Value));
            }
        }
    
        /// <summary> Get string representation of Player </summary>
        public override string ToString() {
            string stringResult = "Player { ";
            stringResult += "TeamIndex: ";
            stringResult += TeamIndex.ToString();
            stringResult += ", ";
            stringResult += "Score: ";
            stringResult += Score.ToString();
            stringResult += ", ";
            stringResult += "Specialty: ";
            if (!Specialty.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += Specialty.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}