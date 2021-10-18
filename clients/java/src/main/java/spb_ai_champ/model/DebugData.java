package spb_ai_champ.model;

import spb_ai_champ.util.StreamUtil;

/**
 * TODO - Document
 */
public class DebugData {

    public DebugData() {
    }

    /**
     * Read DebugData from input stream
     */
    public static DebugData readFrom(java.io.InputStream stream) throws java.io.IOException {
        return new DebugData();
    }

    /**
     * Write DebugData to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
    }

    /**
     * Get string representation of DebugData
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DebugData { ");
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}