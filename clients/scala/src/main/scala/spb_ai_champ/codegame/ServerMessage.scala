package spb_ai_champ.codegame

import spb_ai_champ.util.StreamUtil

/**
 * Message sent from server
 */
sealed trait ServerMessage {
    /**
     * Write ServerMessage to output stream
     */
    def writeTo(stream: java.io.OutputStream)
}

object ServerMessage {
    /**
     * Get action for next tick
     *
     * @param playerView Player's view
     * @param debugAvailable Whether app is running with debug interface available
     */
    case class GetAction(playerView: spb_ai_champ.model.Game, debugAvailable: Boolean) extends ServerMessage {
        /**
         * Write GetAction to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, GetAction.TAG)
            playerView.writeTo(stream)
            StreamUtil.writeBoolean(stream, debugAvailable)
        }
    
        /**
         * Get string representation of GetAction
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("GetAction { ")
            stringBuilder.append("playerView: ")
            stringBuilder.append(playerView)
            stringBuilder.append(", ")
            stringBuilder.append("debugAvailable: ")
            stringBuilder.append(debugAvailable)
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object GetAction {
        val TAG: Int = 0
    
        /**
         * Read GetAction from input stream
         */
        def readFrom(stream: java.io.InputStream): GetAction = GetAction(
            spb_ai_champ.model.Game.readFrom(stream),
            StreamUtil.readBoolean(stream)
        )
    }

    /**
     * Signifies end of the game
     */
    case class Finish() extends ServerMessage {
        /**
         * Write Finish to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, Finish.TAG)
        }
    
        /**
         * Get string representation of Finish
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("Finish { ")
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object Finish {
        val TAG: Int = 1
    
        /**
         * Read Finish from input stream
         */
        def readFrom(stream: java.io.InputStream): Finish = Finish(
        )
    }

    /**
     * Debug update
     *
     * @param playerView Player's view
     */
    case class DebugUpdate(playerView: spb_ai_champ.model.Game) extends ServerMessage {
        /**
         * Write DebugUpdate to output stream
         */
        override def writeTo(stream: java.io.OutputStream) {
            StreamUtil.writeInt(stream, DebugUpdate.TAG)
            playerView.writeTo(stream)
        }
    
        /**
         * Get string representation of DebugUpdate
         */
        override def toString(): String = {
            var stringBuilder = new StringBuilder("DebugUpdate { ")
            stringBuilder.append("playerView: ")
            stringBuilder.append(playerView)
            stringBuilder.append(" }")
            stringBuilder.toString()
        }
    }
    
    object DebugUpdate {
        val TAG: Int = 2
    
        /**
         * Read DebugUpdate from input stream
         */
        def readFrom(stream: java.io.InputStream): DebugUpdate = DebugUpdate(
            spb_ai_champ.model.Game.readFrom(stream)
        )
    }

    /**
     * Read ServerMessage from input stream
     */
    def readFrom(stream: java.io.InputStream): ServerMessage = {
        StreamUtil.readInt(stream) match {
            case GetAction.TAG => GetAction.readFrom(stream)
            case Finish.TAG => Finish.readFrom(stream)
            case DebugUpdate.TAG => DebugUpdate.readFrom(stream)
            case _ => throw new java.io.IOException("Unexpected tag value")
        }
    }
}