namespace SpbAiChamp.Model
{
    /// <summary>
    /// TODO - Document
    /// </summary>
    public struct FlyingWorkerGroup
    {
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int PlayerIndex { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int Number { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int DepartureTick { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int DeparturePlanet { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int NextPlanetArrivalTick { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int NextPlanet { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public int TargetPlanet { get; set; }
        /// <summary>
        /// TODO - Document
        /// </summary>
        public Model.Resource? Resource { get; set; }
    
        public FlyingWorkerGroup(int playerIndex, int number, int departureTick, int departurePlanet, int nextPlanetArrivalTick, int nextPlanet, int targetPlanet, Model.Resource? resource)
        {
            this.PlayerIndex = playerIndex;
            this.Number = number;
            this.DepartureTick = departureTick;
            this.DeparturePlanet = departurePlanet;
            this.NextPlanetArrivalTick = nextPlanetArrivalTick;
            this.NextPlanet = nextPlanet;
            this.TargetPlanet = targetPlanet;
            this.Resource = resource;
        }
    
        /// <summary> Read FlyingWorkerGroup from reader </summary>
        public static FlyingWorkerGroup ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new FlyingWorkerGroup();
            result.PlayerIndex = reader.ReadInt32();
            result.Number = reader.ReadInt32();
            result.DepartureTick = reader.ReadInt32();
            result.DeparturePlanet = reader.ReadInt32();
            result.NextPlanetArrivalTick = reader.ReadInt32();
            result.NextPlanet = reader.ReadInt32();
            result.TargetPlanet = reader.ReadInt32();
            if (reader.ReadBoolean())
            {
                result.Resource = ResourceHelper.ReadFrom(reader);
            } else
            {
                result.Resource = null;
            }
            return result;
        }
    
        /// <summary> Write FlyingWorkerGroup to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(PlayerIndex);
            writer.Write(Number);
            writer.Write(DepartureTick);
            writer.Write(DeparturePlanet);
            writer.Write(NextPlanetArrivalTick);
            writer.Write(NextPlanet);
            writer.Write(TargetPlanet);
            if (!Resource.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (Resource.Value));
            }
        }
    
        /// <summary> Get string representation of FlyingWorkerGroup </summary>
        public override string ToString() {
            string stringResult = "FlyingWorkerGroup { ";
            stringResult += "PlayerIndex: ";
            stringResult += PlayerIndex.ToString();
            stringResult += ", ";
            stringResult += "Number: ";
            stringResult += Number.ToString();
            stringResult += ", ";
            stringResult += "DepartureTick: ";
            stringResult += DepartureTick.ToString();
            stringResult += ", ";
            stringResult += "DeparturePlanet: ";
            stringResult += DeparturePlanet.ToString();
            stringResult += ", ";
            stringResult += "NextPlanetArrivalTick: ";
            stringResult += NextPlanetArrivalTick.ToString();
            stringResult += ", ";
            stringResult += "NextPlanet: ";
            stringResult += NextPlanet.ToString();
            stringResult += ", ";
            stringResult += "TargetPlanet: ";
            stringResult += TargetPlanet.ToString();
            stringResult += ", ";
            stringResult += "Resource: ";
            if (!Resource.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += Resource.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}