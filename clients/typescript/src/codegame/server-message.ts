import { Game } from "../model/game";
import { Stream } from "../stream";

/**
 * Message sent from server
 */
export abstract class ServerMessage {
    /**
     * Write ServerMessage to output stream
     */
    abstract writeTo(stream: Stream): Promise<void>;

    /**
     * Read ServerMessage from input stream
     */
    static async readFrom(stream: Stream): Promise<ServerMessage> {
        const tag = await stream.readInt();
        if (tag == ServerMessage.GetAction.TAG) {
            return await ServerMessage.GetAction.readFrom(stream);
        }
        if (tag == ServerMessage.Finish.TAG) {
            return await ServerMessage.Finish.readFrom(stream);
        }
        if (tag == ServerMessage.DebugUpdate.TAG) {
            return await ServerMessage.DebugUpdate.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}

export namespace ServerMessage {
    /**
     * Get action for next tick
     */
    export class GetAction extends ServerMessage {
        /**
         * Player's view
         */
        playerView: Game
        /**
         * Whether app is running with debug interface available
         */
        debugAvailable: boolean
    
        constructor(playerView: Game, debugAvailable: boolean) {
            super();
            this.playerView = playerView;
            this.debugAvailable = debugAvailable;
        }
    
        /**
         * Read GetAction from input stream
         */
        static async readFrom(stream: Stream): Promise<ServerMessage.GetAction> {
            let playerView;
            playerView = await Game.readFrom(stream);
            let debugAvailable;
            debugAvailable = await stream.readBool();
            return new GetAction(playerView, debugAvailable)
        }
    
        /**
         * Write GetAction to output stream
         */
        async writeTo(stream: Stream) {
            await stream.writeInt(GetAction.TAG);
            let playerView = this.playerView;
            await playerView.writeTo(stream);
            let debugAvailable = this.debugAvailable;
            await stream.writeBool(debugAvailable);
        }
    }
    
    export namespace GetAction {
        export const TAG = 0;
    }
    /**
     * Signifies end of the game
     */
    export class Finish extends ServerMessage {
    
        constructor() {
            super();
        }
    
        /**
         * Read Finish from input stream
         */
        static async readFrom(stream: Stream): Promise<ServerMessage.Finish> {
            return new Finish()
        }
    
        /**
         * Write Finish to output stream
         */
        async writeTo(stream: Stream) {
            await stream.writeInt(Finish.TAG);
        }
    }
    
    export namespace Finish {
        export const TAG = 1;
    }
    /**
     * Debug update
     */
    export class DebugUpdate extends ServerMessage {
        /**
         * Player's view
         */
        playerView: Game
    
        constructor(playerView: Game) {
            super();
            this.playerView = playerView;
        }
    
        /**
         * Read DebugUpdate from input stream
         */
        static async readFrom(stream: Stream): Promise<ServerMessage.DebugUpdate> {
            let playerView;
            playerView = await Game.readFrom(stream);
            return new DebugUpdate(playerView)
        }
    
        /**
         * Write DebugUpdate to output stream
         */
        async writeTo(stream: Stream) {
            await stream.writeInt(DebugUpdate.TAG);
            let playerView = this.playerView;
            await playerView.writeTo(stream);
        }
    }
    
    export namespace DebugUpdate {
        export const TAG = 2;
    }
}