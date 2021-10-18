'use strict';

import { TcpStream } from "./tcp-stream";
import { MyStrategy } from "./my-strategy";
import { ServerMessage } from "./codegame/server-message";
import { ClientMessage } from "./codegame/client-message";

class Runner {
    private token: string;
    private stream: TcpStream;

    constructor(host: string, port: number, token: string) {
        this.stream = new TcpStream(host, port);
        this.token = token;
    }

    async connect() {
        await this.stream.writeString(this.token);
        await this.stream.writeInt(0);
        await this.stream.writeInt(3);
        await this.stream.writeInt(0);
        await this.stream.flush();
    }

    async run() {
        try {
            await this.connect();
            const strategy = new MyStrategy();
            //const debugInterface = new DebugInterface(this.stream);
            while (true) {
                let message = await ServerMessage.readFrom(this.stream);
                if (message instanceof ServerMessage.GetAction) {
                    await (new ClientMessage.ActionMessage(await strategy.getAction(message.playerView)).writeTo(this.stream));
                    await this.stream.flush();
                } else if (message instanceof ServerMessage.Finish) {
                    break;
                } else if (message instanceof ServerMessage.DebugUpdate) {
                    //await strategy.debugUpdate(message.playerView, debugInterface);
                    await (new ClientMessage.DebugUpdateDone().writeTo(this.stream));
                    await this.stream.flush();
                } else {
                    throw new Error("Unexpected server message");
                }
            }
        } catch (e) {
            console.error(e);
            process.exit(1);
        }
    }
}

const argv = process.argv;
const argc = argv.length;
const host = argc < 3 ? "127.0.0.1" : argv[2];
const port = argc < 4 ? 31001 : parseInt(argv[3]);
const token = argc < 5 ? "0000000000000000" : argv[4];
(new Runner(host, port, token)).run();