const Game = require.main.require('./model/game');

/**
 * Message sent from server
 */
class ServerMessage {
    /**
     * Read ServerMessage from input stream
     */
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == GetAction.TAG) {
            return await GetAction.readFrom(stream);
        }
        if (tag == Finish.TAG) {
            return await Finish.readFrom(stream);
        }
        if (tag == DebugUpdate.TAG) {
            return await DebugUpdate.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}
/**
 * Get action for next tick
 */
class GetAction extends ServerMessage {
    /**
     * Player's view
     */
    playerView;
    /**
     * Whether app is running with debug interface available
     */
    debugAvailable;

    constructor(playerView, debugAvailable) {
        super();
        this.playerView = playerView;
        this.debugAvailable = debugAvailable;
    }

    /**
     * Read GetAction from input stream
     */
    static async readFrom(stream) {
        let playerView;
        playerView = await Game.readFrom(stream);
        let debugAvailable;
        debugAvailable = await stream.readBool();
        return new GetAction(playerView, debugAvailable);
    }

    /**
     * Write GetAction to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(GetAction.TAG);
        let playerView = this.playerView;
        await playerView.writeTo(stream);
        let debugAvailable = this.debugAvailable;
        await stream.writeBool(debugAvailable);
    }
}

GetAction.TAG = 0;
ServerMessage.GetAction = GetAction;
/**
 * Signifies end of the game
 */
class Finish extends ServerMessage {

    constructor() {
        super();
    }

    /**
     * Read Finish from input stream
     */
    static async readFrom(stream) {
        return new Finish();
    }

    /**
     * Write Finish to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(Finish.TAG);
    }
}

Finish.TAG = 1;
ServerMessage.Finish = Finish;
/**
 * Debug update
 */
class DebugUpdate extends ServerMessage {
    /**
     * Player's view
     */
    playerView;

    constructor(playerView) {
        super();
        this.playerView = playerView;
    }

    /**
     * Read DebugUpdate from input stream
     */
    static async readFrom(stream) {
        let playerView;
        playerView = await Game.readFrom(stream);
        return new DebugUpdate(playerView);
    }

    /**
     * Write DebugUpdate to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(DebugUpdate.TAG);
        let playerView = this.playerView;
        await playerView.writeTo(stream);
    }
}

DebugUpdate.TAG = 2;
ServerMessage.DebugUpdate = DebugUpdate;
module.exports = ServerMessage;