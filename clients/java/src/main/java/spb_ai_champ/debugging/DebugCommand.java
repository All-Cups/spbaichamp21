package spb_ai_champ.debugging;

import spb_ai_champ.util.StreamUtil;

/**
 * Debug commands that can be sent while debugging with the app
 */
public abstract class DebugCommand {
    /**
     * Write DebugCommand to output stream
     */
    public abstract void writeTo(java.io.OutputStream stream) throws java.io.IOException;

    /**
     * Read DebugCommand from input stream
     */
    public static DebugCommand readFrom(java.io.InputStream stream) throws java.io.IOException {
        switch (StreamUtil.readInt(stream)) {
            case Add.TAG:
                return Add.readFrom(stream);
            case Clear.TAG:
                return Clear.readFrom(stream);
            case SetAutoFlush.TAG:
                return SetAutoFlush.readFrom(stream);
            case Flush.TAG:
                return Flush.readFrom(stream);
            default:
                throw new java.io.IOException("Unexpected tag value");
        }
    }

    /**
     * Add debug data to current tick
     */
    public static class Add extends DebugCommand {
        public static final int TAG = 0;
    
        /**
         * Data to add
         */
        private spb_ai_champ.model.DebugData data;
    
        /**
         * Data to add
         */
        public spb_ai_champ.model.DebugData getData() {
            return data;
        }
    
        /**
         * Data to add
         */
        public void setData(spb_ai_champ.model.DebugData value) {
            this.data = value;
        }
    
        public Add(spb_ai_champ.model.DebugData data) {
            this.data = data;
        }
    
        /**
         * Read Add from input stream
         */
        public static Add readFrom(java.io.InputStream stream) throws java.io.IOException {
            spb_ai_champ.model.DebugData data;
            data = spb_ai_champ.model.DebugData.readFrom(stream);
            return new Add(data);
        }
    
        /**
         * Write Add to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            data.writeTo(stream);
        }
    
        /**
         * Get string representation of Add
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("Add { ");
            stringBuilder.append("data: ");
            stringBuilder.append(String.valueOf(data));
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    /**
     * Clear current tick's debug data
     */
    public static class Clear extends DebugCommand {
        public static final int TAG = 1;
    
    
        public Clear() {
        }
    
        /**
         * Read Clear from input stream
         */
        public static Clear readFrom(java.io.InputStream stream) throws java.io.IOException {
            return new Clear();
        }
    
        /**
         * Write Clear to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    
        /**
         * Get string representation of Clear
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("Clear { ");
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    /**
     * Enable/disable auto performing of commands
     */
    public static class SetAutoFlush extends DebugCommand {
        public static final int TAG = 2;
    
        /**
         * Enable/disable autoflush
         */
        private boolean enable;
    
        /**
         * Enable/disable autoflush
         */
        public boolean isEnable() {
            return enable;
        }
    
        /**
         * Enable/disable autoflush
         */
        public void setEnable(boolean value) {
            this.enable = value;
        }
    
        public SetAutoFlush(boolean enable) {
            this.enable = enable;
        }
    
        /**
         * Read SetAutoFlush from input stream
         */
        public static SetAutoFlush readFrom(java.io.InputStream stream) throws java.io.IOException {
            boolean enable;
            enable = StreamUtil.readBoolean(stream);
            return new SetAutoFlush(enable);
        }
    
        /**
         * Write SetAutoFlush to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
            StreamUtil.writeBoolean(stream, enable);
        }
    
        /**
         * Get string representation of SetAutoFlush
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("SetAutoFlush { ");
            stringBuilder.append("enable: ");
            stringBuilder.append(String.valueOf(enable));
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }

    /**
     * Perform all previously sent commands
     */
    public static class Flush extends DebugCommand {
        public static final int TAG = 3;
    
    
        public Flush() {
        }
    
        /**
         * Read Flush from input stream
         */
        public static Flush readFrom(java.io.InputStream stream) throws java.io.IOException {
            return new Flush();
        }
    
        /**
         * Write Flush to output stream
         */
        @Override
        public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
            StreamUtil.writeInt(stream, TAG);
        }
    
        /**
         * Get string representation of Flush
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("Flush { ");
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }
    }
}