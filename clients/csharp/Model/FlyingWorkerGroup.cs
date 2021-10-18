namespace SpbAiChamp.Model
{
    /// <summary>
    /// A flying worker group
    /// </summary>
    public struct FlyingWorkerGroup
    {
        /// <summary>
        /// Index of player controlling workers
        /// </summary>
        public int PlayerIndex { get; set; }
        /// <summary>
        /// Number of workers in the group
        /// </summary>
        public int Number { get; set; }
        /// <summary>
        /// Tick when workers left previous planet on their path
        /// </summary>
        public int DepartureTick { get; set; }
        /// <summary>
        /// Id of the previous planet on the path
        /// </summary>
        public int DeparturePlanet { get; set; }
        /// <summary>
        /// Tick when workers will arrive to the next planet in their path
        /// </summary>
        public int NextPlanetArrivalTick { get; set; }
        /// <summary>
        /// Id of the next planet in the path
        /// </summary>
        public int NextPlanet { get; set; }
        /// <summary>
        /// Id of the target planet
        /// </summary>
        public int TargetPlanet { get; set; }
        /// <summary>
        /// Resource that workers are carrying
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