namespace SpbAiChamp.Codegame
{
    /// <summary>
    /// Message sent from client
    /// </summary>
    public abstract class ClientMessage
    {
        /// <summary> Write ClientMessage to writer </summary>
        public abstract void WriteTo(System.IO.BinaryWriter writer);

        /// <summary> Read ClientMessage from reader </summary>
        public static ClientMessage ReadFrom(System.IO.BinaryReader reader)
        {
            switch (reader.ReadInt32())
            {
                case DebugMessage.TAG:
                    return DebugMessage.ReadFrom(reader);
                case ActionMessage.TAG:
                    return ActionMessage.ReadFrom(reader);
                case DebugUpdateDone.TAG:
                    return DebugUpdateDone.ReadFrom(reader);
                case RequestDebugState.TAG:
                    return RequestDebugState.ReadFrom(reader);
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }

        /// <summary>
        /// Ask app to perform new debug command
        /// </summary>
        public class DebugMessage : ClientMessage
        {
            public const int TAG = 0;
        
            /// <summary>
            /// Command to perform
            /// </summary>
            public Debugging.DebugCommand Command { get; set; }
        
            public DebugMessage() { }
        
            public DebugMessage(Debugging.DebugCommand command)
            {
                this.Command = command;
            }
        
            /// <summary> Read DebugMessage from reader </summary>
            public static new DebugMessage ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new DebugMessage();
                result.Command = Debugging.DebugCommand.ReadFrom(reader);
                return result;
            }
        
            /// <summary> Write DebugMessage to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                Command.WriteTo(writer);
            }
        
            /// <summary> Get string representation of DebugMessage </summary>
            public override string ToString() {
                string stringResult = "DebugMessage { ";
                stringResult += "Command: ";
                stringResult += Command.ToString();
                stringResult += " }";
                return stringResult;
            }
        }

        /// <summary>
        /// Reply for ServerMessage::GetAction
        /// </summary>
        public class ActionMessage : ClientMessage
        {
            public const int TAG = 1;
        
            /// <summary>
            /// Player's action
            /// </summary>
            public Model.Action Action { get; set; }
        
            public ActionMessage() { }
        
            public ActionMessage(Model.Action action)
            {
                this.Action = action;
            }
        
            /// <summary> Read ActionMessage from reader </summary>
            public static new ActionMessage ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new ActionMessage();
                result.Action = Model.Action.ReadFrom(reader);
                return result;
            }
        
            /// <summary> Write ActionMessage to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                Action.WriteTo(writer);
            }
        
            /// <summary> Get string representation of ActionMessage </summary>
            public override string ToString() {
                string stringResult = "ActionMessage { ";
                stringResult += "Action: ";
                stringResult += Action.ToString();
                stringResult += " }";
                return stringResult;
            }
        }

        /// <summary>
        /// Signifies finish of the debug update
        /// </summary>
        public class DebugUpdateDone : ClientMessage
        {
            public const int TAG = 2;
        
        
            public DebugUpdateDone() { }
        
            /// <summary> Read DebugUpdateDone from reader </summary>
            public static new DebugUpdateDone ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new DebugUpdateDone();
                return result;
            }
        
            /// <summary> Write DebugUpdateDone to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        
            /// <summary> Get string representation of DebugUpdateDone </summary>
            public override string ToString() {
                string stringResult = "DebugUpdateDone { ";
                stringResult += " }";
                return stringResult;
            }
        }

        /// <summary>
        /// Request debug state from the app
        /// </summary>
        public class RequestDebugState : ClientMessage
        {
            public const int TAG = 3;
        
        
            public RequestDebugState() { }
        
            /// <summary> Read RequestDebugState from reader </summary>
            public static new RequestDebugState ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new RequestDebugState();
                return result;
            }
        
            /// <summary> Write RequestDebugState to writer </summary>
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        
            /// <summary> Get string representation of RequestDebugState </summary>
            public override string ToString() {
                string stringResult = "RequestDebugState { ";
                stringResult += " }";
                return stringResult;
            }
        }
    }
}