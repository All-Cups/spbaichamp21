package spb_ai_champ.codegame

import spb_ai_champ.util.StreamUtil

/**
 * Message sent from client
 */
sealed trait ClientMessage {
    /**
     * Write ClientMessage to output stream
     */
    def writeTo(stream: java.io.OutputStream)
}

object ClientMessage {
    /**
     * Ask app to perform new debug command
     *
     * @param command Command to perform
     */
    case class DebugMessage(command: spb_ai_champ.debugging.DebugCommand) extends ClientMessage {
        /**
         * Write DebugMessage to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, DebugMessage.TAG)
            command.writeTo(stream)
        }
    
        /**
         * Get string representation of DebugMessage
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("DebugMessage { ")
            stringBuilder.append("command: ")
            stringBuilder.append(command)
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object DebugMessage {
        val TAG: Int = 0
    
        /**
         * Read DebugMessage from input stream
         */
        def readFrom(stream: java.io.InputStream): DebugMessage = DebugMessage(
            spb_ai_champ.debugging.DebugCommand.readFrom(stream)
        )
    }

    /**
     * Reply for ServerMessage::GetAction
     *
     * @param action Player's action
     */
    case class ActionMessage(action: spb_ai_champ.model.Action) extends ClientMessage {
        /**
         * Write ActionMessage to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, ActionMessage.TAG)
            action.writeTo(stream)
        }
    
        /**
         * Get string representation of ActionMessage
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("ActionMessage { ")
            stringBuilder.append("action: ")
            stringBuilder.append(action)
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object ActionMessage {
        val TAG: Int = 1
    
        /**
         * Read ActionMessage from input stream
         */
        def readFrom(stream: java.io.InputStream): ActionMessage = ActionMessage(
            spb_ai_champ.model.Action.readFrom(stream)
        )
    }

    /**
     * Signifies finish of the debug update
     */
    case class DebugUpdateDone() extends ClientMessage {
        /**
         * Write DebugUpdateDone to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, DebugUpdateDone.TAG)
        }
    
        /**
         * Get string representation of DebugUpdateDone
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("DebugUpdateDone { ")
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object DebugUpdateDone {
        val TAG: Int = 2
    
        /**
         * Read DebugUpdateDone from input stream
         */
        def readFrom(stream: java.io.InputStream): DebugUpdateDone = DebugUpdateDone(
        )
    }

    /**
     * Request debug state from the app
     */
    case class RequestDebugState() extends ClientMessage {
        /**
         * Write RequestDebugState to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, RequestDebugState.TAG)
        }
    
        /**
         * Get string representation of RequestDebugState
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("RequestDebugState { ")
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object RequestDebugState {
        val TAG: Int = 3
    
        /**
         * Read RequestDebugState from input stream
         */
        def readFrom(stream: java.io.InputStream): RequestDebugState = RequestDebugState(
        )
    }

    /**
     * Read ClientMessage from input stream
     */
    def readFrom(stream: java.io.InputStream): ClientMessage = {
        StreamUtil.readInt(stream) match {
            case DebugMessage.TAG => DebugMessage.readFrom(stream)
            case ActionMessage.TAG => ActionMessage.readFrom(stream)
            case DebugUpdateDone.TAG => DebugUpdateDone.readFrom(stream)
            case RequestDebugState.TAG => RequestDebugState.readFrom(stream)
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
    }
}