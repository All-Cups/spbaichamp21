module codegame.client_message;

import stream;
import std.conv;
import std.typecons : Nullable;
import debugging.debug_command;
import model.action;

/// Message sent from client
abstract class ClientMessage {
    /// Write ClientMessage to writer
    abstract void writeTo(Stream writer) const;

    /// Read ClientMessage from reader
    static ClientMessage readFrom(Stream reader) {
        switch (reader.readInt()) {
            case DebugMessage.TAG:
                return DebugMessage.readFrom(reader);
            case ActionMessage.TAG:
                return ActionMessage.readFrom(reader);
            case DebugUpdateDone.TAG:
                return DebugUpdateDone.readFrom(reader);
            case RequestDebugState.TAG:
                return RequestDebugState.readFrom(reader);
            default:
                throw new Exception("Unexpected tag value");
        }
    }
    
    /// Ask app to perform new debug command
    static class DebugMessage : ClientMessage {
        static const int TAG = 0;
    
        /// Command to perform
        debugging.DebugCommand command;
    
        this() {}
    
        this(debugging.DebugCommand command) {
            this.command = command;
        }
    
        /// Read DebugMessage from reader
        static DebugMessage readFrom(Stream reader) {
            debugging.DebugCommand command;
            command = debugging.DebugCommand.readFrom(reader);
            return new DebugMessage(command);
        }
    
        /// Write DebugMessage to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            command.writeTo(writer);
        }
    }
    
    /// Reply for ServerMessage::GetAction
    static class ActionMessage : ClientMessage {
        static const int TAG = 1;
    
        /// Player's action
        model.Action action;
    
        this() {}
    
        this(model.Action action) {
            this.action = action;
        }
    
        /// Read ActionMessage from reader
        static ActionMessage readFrom(Stream reader) {
            model.Action action;
            action = model.Action.readFrom(reader);
            return new ActionMessage(action);
        }
    
        /// Write ActionMessage to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            action.writeTo(writer);
        }
    }
    
    /// Signifies finish of the debug update
    static class DebugUpdateDone : ClientMessage {
        static const int TAG = 2;
    
    
        this() {}
    
        /// Read DebugUpdateDone from reader
        static DebugUpdateDone readFrom(Stream reader) {
            return new DebugUpdateDone();
        }
    
        /// Write DebugUpdateDone to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
    }
    
    /// Request debug state from the app
    static class RequestDebugState : ClientMessage {
        static const int TAG = 3;
    
    
        this() {}
    
        /// Read RequestDebugState from reader
        static RequestDebugState readFrom(Stream reader) {
            return new RequestDebugState();
        }
    
        /// Write RequestDebugState to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
    }
}