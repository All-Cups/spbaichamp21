const DebugData = require.main.require('./model/debug-data');

/**
 * Debug commands that can be sent while debugging with the app
 */
class DebugCommand {
    /**
     * Read DebugCommand from input stream
     */
    static async readFrom(stream) {
        let tag = await stream.readInt();
        if (tag == Add.TAG) {
            return await Add.readFrom(stream);
        }
        if (tag == Clear.TAG) {
            return await Clear.readFrom(stream);
        }
        if (tag == SetAutoFlush.TAG) {
            return await SetAutoFlush.readFrom(stream);
        }
        if (tag == Flush.TAG) {
            return await Flush.readFrom(stream);
        }
        throw new Error("Unexpected tag value");
    }
}
/**
 * Add debug data to current tick
 */
class Add extends DebugCommand {
    /**
     * Data to add
     */
    data;

    constructor(data) {
        super();
        this.data = data;
    }

    /**
     * Read Add from input stream
     */
    static async readFrom(stream) {
        let data;
        data = await DebugData.readFrom(stream);
        return new Add(data);
    }

    /**
     * Write Add to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(Add.TAG);
        let data = this.data;
        await data.writeTo(stream);
    }
}

Add.TAG = 0;
DebugCommand.Add = Add;
/**
 * Clear current tick's debug data
 */
class Clear extends DebugCommand {

    constructor() {
        super();
    }

    /**
     * Read Clear from input stream
     */
    static async readFrom(stream) {
        return new Clear();
    }

    /**
     * Write Clear to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(Clear.TAG);
    }
}

Clear.TAG = 1;
DebugCommand.Clear = Clear;
/**
 * Enable/disable auto performing of commands
 */
class SetAutoFlush extends DebugCommand {
    /**
     * Enable/disable autoflush
     */
    enable;

    constructor(enable) {
        super();
        this.enable = enable;
    }

    /**
     * Read SetAutoFlush from input stream
     */
    static async readFrom(stream) {
        let enable;
        enable = await stream.readBool();
        return new SetAutoFlush(enable);
    }

    /**
     * Write SetAutoFlush to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(SetAutoFlush.TAG);
        let enable = this.enable;
        await stream.writeBool(enable);
    }
}

SetAutoFlush.TAG = 2;
DebugCommand.SetAutoFlush = SetAutoFlush;
/**
 * Perform all previously sent commands
 */
class Flush extends DebugCommand {

    constructor() {
        super();
    }

    /**
     * Read Flush from input stream
     */
    static async readFrom(stream) {
        return new Flush();
    }

    /**
     * Write Flush to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(Flush.TAG);
    }
}

Flush.TAG = 3;
DebugCommand.Flush = Flush;
module.exports = DebugCommand;