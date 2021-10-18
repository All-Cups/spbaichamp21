module codegame.server_message;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.game;

/// Message sent from server
abstract class ServerMessage {
    /// Write ServerMessage to writer
    abstract void writeTo(Stream writer) const;

    /// Read ServerMessage from reader
    static ServerMessage readFrom(Stream reader) {
        switch (reader.readInt()) {
            case GetAction.TAG:
                return GetAction.readFrom(reader);
            case Finish.TAG:
                return Finish.readFrom(reader);
            case DebugUpdate.TAG:
                return DebugUpdate.readFrom(reader);
            default:
                throw new Exception("Unexpected tag value");
        }
    }
    
    /// Get action for next tick
    static class GetAction : ServerMessage {
        static const int TAG = 0;
    
        /// Player's view
        model.Game playerView;
        /// Whether app is running with debug interface available
        bool debugAvailable;
    
        this() {}
    
        this(model.Game playerView, bool debugAvailable) {
            this.playerView = playerView;
            this.debugAvailable = debugAvailable;
        }
    
        /// Read GetAction from reader
        static GetAction readFrom(Stream reader) {
            model.Game playerView;
            playerView = model.Game.readFrom(reader);
            bool debugAvailable;
            debugAvailable = reader.readBool();
            return new GetAction(playerView, debugAvailable);
        }
    
        /// Write GetAction to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            playerView.writeTo(writer);
            writer.write(debugAvailable);
        }
    }
    
    /// Signifies end of the game
    static class Finish : ServerMessage {
        static const int TAG = 1;
    
    
        this() {}
    
        /// Read Finish from reader
        static Finish readFrom(Stream reader) {
            return new Finish();
        }
    
        /// Write Finish to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
        }
    }
    
    /// Debug update
    static class DebugUpdate : ServerMessage {
        static const int TAG = 2;
    
        /// Player's view
        model.Game playerView;
    
        this() {}
    
        this(model.Game playerView) {
            this.playerView = playerView;
        }
    
        /// Read DebugUpdate from reader
        static DebugUpdate readFrom(Stream reader) {
            model.Game playerView;
            playerView = model.Game.readFrom(reader);
            return new DebugUpdate(playerView);
        }
    
        /// Write DebugUpdate to writer
        override void writeTo(Stream writer) const {
            writer.write(TAG);
            playerView.writeTo(writer);
        }
    }
}