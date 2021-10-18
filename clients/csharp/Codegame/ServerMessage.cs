namespace SpbAiChamp.Codegame
{
    /// <summary>
    /// Message sent from server
    /// </summary>
    public abstract class ServerMessage
    {
        /// <summary> Write ServerMessage to writer </summary>
        public abstract void WriteTo(System.IO.BinaryWriter writer);

        /// <summary> Read ServerMessage from reader </summary>
        public static ServerMessage ReadFrom(System.IO.BinaryReader reader)
        {
            switch (reader.ReadInt32())
            {
                case GetAction.TAG:
                    return GetAction.ReadFrom(reader);
                case Finish.TAG:
                    return Finish.ReadFrom(reader);
                case DebugUpdate.TAG:
                    return DebugUpdate.ReadFrom(reader);
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }

        /// <summary>
        /// Get action for next tick
        /// </summary>
        public class GetAction : ServerMessage
        {
            public const int TAG = 0;
        
            /// <summary>
            /// Player's view
            /// </summary>
            public Model.Game PlayerView { get; set; }
            /// <summary>
            /// Whether app is running with debug interface available
            /// </summary>
            public bool DebugAvailable { get; set; }
        
            public GetAction() { }
        
            public GetAction(Model.Game playerView, bool debugAvailable)
            {
                this.PlayerView = playerView;
                this.DebugAvailable = debugAvailable;
            }
        
            /// <summary> Read GetAction from reader </summary>
            public static new GetAction ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new GetAction();
                result.PlayerView = Model.Game.ReadFrom(reader);
                result.DebugAvailable = reader.ReadBoolean();
                return result;
            }
        
            /// <summary> Write GetAction to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                PlayerView.WriteTo(writer);
                writer.Write(DebugAvailable);
            }
        
            /// <summary> Get string representation of GetAction </summary>
            public override string ToString() {
                string stringResult = "GetAction { ";
                stringResult += "PlayerView: ";
                stringResult += PlayerView.ToString();
                stringResult += ", ";
                stringResult += "DebugAvailable: ";
                stringResult += DebugAvailable.ToString();
                stringResult += " }";
                return stringResult;
            }
        }

        /// <summary>
        /// Signifies end of the game
        /// </summary>
        public class Finish : ServerMessage
        {
            public const int TAG = 1;
        
        
            public Finish() { }
        
            /// <summary> Read Finish from reader </summary>
            public static new Finish ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Finish();
                return result;
            }
        
            /// <summary> Write Finish to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        
            /// <summary> Get string representation of Finish </summary>
            public override string ToString() {
                string stringResult = "Finish { ";
                stringResult += " }";
                return stringResult;
            }
        }

        /// <summary>
        /// Debug update
        /// </summary>
        public class DebugUpdate : ServerMessage
        {
            public const int TAG = 2;
        
            /// <summary>
            /// Player's view
            /// </summary>
            public Model.Game PlayerView { get; set; }
        
            public DebugUpdate() { }
        
            public DebugUpdate(Model.Game playerView)
            {
                this.PlayerView = playerView;
            }
        
            /// <summary> Read DebugUpdate from reader </summary>
            public static new DebugUpdate ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new DebugUpdate();
                result.PlayerView = Model.Game.ReadFrom(reader);
                return result;
            }
        
            /// <summary> Write DebugUpdate to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                PlayerView.WriteTo(writer);
            }
        
            /// <summary> Get string representation of DebugUpdate </summary>
            public override string ToString() {
                string stringResult = "DebugUpdate { ";
                stringResult += "PlayerView: ";
                stringResult += PlayerView.ToString();
                stringResult += " }";
                return stringResult;
            }
        }
    }
}