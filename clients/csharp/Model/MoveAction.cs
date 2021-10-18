namespace SpbAiChamp.Model
{
    /// <summary>
    /// Movement order
    /// </summary>
    public struct MoveAction
    {
        /// <summary>
        /// Id of the planet where workers need to be sent from
        /// </summary>
        public int StartPlanet { get; set; }
        /// <summary>
        /// Id of the target planet
        /// </summary>
        public int TargetPlanet { get; set; }
        /// <summary>
        /// Number of workers to send
        /// </summary>
        public int WorkerNumber { get; set; }
        /// <summary>
        /// Resource workers should carry
        /// </summary>
        public Model.Resource? TakeResource { get; set; }
    
        public MoveAction(int startPlanet, int targetPlanet, int workerNumber, Model.Resource? takeResource)
        {
            this.StartPlanet = startPlanet;
            this.TargetPlanet = targetPlanet;
            this.WorkerNumber = workerNumber;
            this.TakeResource = takeResource;
        }
    
        /// <summary> Read MoveAction from reader </summary>
        public static MoveAction ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new MoveAction();
            result.StartPlanet = reader.ReadInt32();
            result.TargetPlanet = reader.ReadInt32();
            result.WorkerNumber = reader.ReadInt32();
            if (reader.ReadBoolean())
            {
                result.TakeResource = ResourceHelper.ReadFrom(reader);
            } else
            {
                result.TakeResource = null;
            }
            return result;
        }
    
        /// <summary> Write MoveAction to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(StartPlanet);
            writer.Write(TargetPlanet);
            writer.Write(WorkerNumber);
            if (!TakeResource.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (TakeResource.Value));
            }
        }
    
        /// <summary> Get string representation of MoveAction </summary>
        public override string ToString() {
            string stringResult = "MoveAction { ";
            stringResult += "StartPlanet: ";
            stringResult += StartPlanet.ToString();
            stringResult += ", ";
            stringResult += "TargetPlanet: ";
            stringResult += TargetPlanet.ToString();
            stringResult += ", ";
            stringResult += "WorkerNumber: ";
            stringResult += WorkerNumber.ToString();
            stringResult += ", ";
            stringResult += "TakeResource: ";
            if (!TakeResource.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += TakeResource.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}