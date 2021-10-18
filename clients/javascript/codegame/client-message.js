const Action = require.main.require('./model/action');
const DebugCommand = require.main.require('./debugging/debug-command');

/**
 * Message sent from client
 */
class ClientMessage {
    /**
     * Read ClientMessage from input stream
     */
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == DebugMessage.TAG) {
            return await DebugMessage.readFrom(stream);
        }
        if (tag == ActionMessage.TAG) {
            return await ActionMessage.readFrom(stream);
        }
        if (tag == DebugUpdateDone.TAG) {
            return await DebugUpdateDone.readFrom(stream);
        }
        if (tag == RequestDebugState.TAG) {
            return await RequestDebugState.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}
/**
 * Ask app to perform new debug command
 */
class DebugMessage extends ClientMessage {
    /**
     * Command to perform
     */
    command;

    constructor(command) {
        super();
        this.command = command;
    }

    /**
     * Read DebugMessage from input stream
     */
    static async readFrom(stream) {
        let command;
        command = await DebugCommand.readFrom(stream);
        return new DebugMessage(command);
    }

    /**
     * Write DebugMessage to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(DebugMessage.TAG);
        let command = this.command;
        await command.writeTo(stream);
    }
}

DebugMessage.TAG = 0;
ClientMessage.DebugMessage = DebugMessage;
/**
 * Reply for ServerMessage::GetAction
 */
class ActionMessage extends ClientMessage {
    /**
     * Player's action
     */
    action;

    constructor(action) {
        super();
        this.action = action;
    }

    /**
     * Read ActionMessage from input stream
     */
    static async readFrom(stream) {
        let action;
        action = await Action.readFrom(stream);
        return new ActionMessage(action);
    }

    /**
     * Write ActionMessage to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(ActionMessage.TAG);
        let action = this.action;
        await action.writeTo(stream);
    }
}

ActionMessage.TAG = 1;
ClientMessage.ActionMessage = ActionMessage;
/**
 * Signifies finish of the debug update
 */
class DebugUpdateDone extends ClientMessage {

    constructor() {
        super();
    }

    /**
     * Read DebugUpdateDone from input stream
     */
    static async readFrom(stream) {
        return new DebugUpdateDone();
    }

    /**
     * Write DebugUpdateDone to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(DebugUpdateDone.TAG);
    }
}

DebugUpdateDone.TAG = 2;
ClientMessage.DebugUpdateDone = DebugUpdateDone;
/**
 * Request debug state from the app
 */
class RequestDebugState extends ClientMessage {

    constructor() {
        super();
    }

    /**
     * Read RequestDebugState from input stream
     */
    static async readFrom(stream) {
        return new RequestDebugState();
    }

    /**
     * Write RequestDebugState to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(RequestDebugState.TAG);
    }
}

RequestDebugState.TAG = 3;
ClientMessage.RequestDebugState = RequestDebugState;
module.exports = ClientMessage;