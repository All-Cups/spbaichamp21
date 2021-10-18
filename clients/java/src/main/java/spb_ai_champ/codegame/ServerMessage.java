package spb_ai_champ.codegame;

import spb_ai_champ.util.StreamUtil;

/**
 * Message sent from server
 */
public abstract class ServerMessage {
    /**
     * Write ServerMessage to output stream
     */
    public abstract void writeTo(java.io.OutputStream stream) throws java.io.IOException;

    /**
     * Read ServerMessage from input stream
     */
    public static ServerMessage readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
            case GetAction.TAG:
                return GetAction.readFrom(stream);
            case Finish.TAG:
                return Finish.readFrom(stream);
            case DebugUpdate.TAG:
                return DebugUpdate.readFrom(stream);
            default:
                throw new java.io.IOException("Unexpected tag value");
        }
    }

    /**
     * Get action for next tick
     */
    public static class GetAction extends ServerMessage {
        public static final int TAG = 0;
    
        /**
         * Player's view
         */
        private spb_ai_champ.model.Game playerView;
    
        /**
         * Player's view
         */
        public spb_ai_champ.model.Game getPlayerView() {
            return playerView;
        }
    
        /**
         * Player's view
         */
        public void setPlayerView(spb_ai_champ.model.Game value) {
            this.playerView = value;
        }
        /**
         * Whether app is running with debug interface available
         */
        private boolean debugAvailable;
    
        /**
         * Whether app is running with debug interface available
         */
        public boolean isDebugAvailable() {
            return debugAvailable;
        }
    
        /**
         * Whether app is running with debug interface available
         */
        public void setDebugAvailable(boolean value) {
            this.debugAvailable = value;
        }
    
        public GetAction(spb_ai_champ.model.Game playerView, boolean debugAvailable) {
            this.playerView = playerView;
            this.debugAvailable = debugAvailable;
        }
    
        /**
         * Read GetAction from input stream
         */
        public static GetAction readFrom(java.io.InputStream stream) throws java.io.IOException {
            spb_ai_champ.model.Game playerView;
            playerView = spb_ai_champ.model.Game.readFrom(stream);
            boolean debugAvailable;
            debugAvailable = StreamUtil.readBoolean(stream);
            return new GetAction(playerView, debugAvailable);
        }
    
        /**
         * Write GetAction to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            playerView.writeTo(stream);
            StreamUtil.writeBoolean(stream, debugAvailable);
        }
    
        /**
         * Get string representation of GetAction
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("GetAction { ");
            stringBuilder.append("playerView: ");
            stringBuilder.append(String.valueOf(playerView));
            stringBuilder.append(", ");
            stringBuilder.append("debugAvailable: ");
            stringBuilder.append(String.valueOf(debugAvailable));
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    /**
     * Signifies end of the game
     */
    public static class Finish extends ServerMessage {
        public static final int TAG = 1;
    
    
        public Finish() {
        }
    
        /**
         * Read Finish from input stream
         */
        public static Finish readFrom(java.io.InputStream stream) throws java.io.IOException {
            return new Finish();
        }
    
        /**
         * Write Finish to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    
        /**
         * Get string representation of Finish
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("Finish { ");
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    /**
     * Debug update
     */
    public static class DebugUpdate extends ServerMessage {
        public static final int TAG = 2;
    
        /**
         * Player's view
         */
        private spb_ai_champ.model.Game playerView;
    
        /**
         * Player's view
         */
        public spb_ai_champ.model.Game getPlayerView() {
            return playerView;
        }
    
        /**
         * Player's view
         */
        public void setPlayerView(spb_ai_champ.model.Game value) {
            this.playerView = value;
        }
    
        public DebugUpdate(spb_ai_champ.model.Game playerView) {
            this.playerView = playerView;
        }
    
        /**
         * Read DebugUpdate from input stream
         */
        public static DebugUpdate readFrom(java.io.InputStream stream) throws java.io.IOException {
            spb_ai_champ.model.Game playerView;
            playerView = spb_ai_champ.model.Game.readFrom(stream);
            return new DebugUpdate(playerView);
        }
    
        /**
         * Write DebugUpdate to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            playerView.writeTo(stream);
        }
    
        /**
         * Get string representation of DebugUpdate
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("DebugUpdate { ");
            stringBuilder.append("playerView: ");
            stringBuilder.append(String.valueOf(playerView));
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }
}