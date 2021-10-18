import my_strategy;
import stream;
import socket_stream;
import buffered_stream;
//import debug_interface;
import std.socket;
import std.conv;
import std.exception;
import codegame.server_message;
import codegame.client_message;

class Runner
{
    this(string host, ushort port, string token)
    {
        auto addr = getAddress(host, port)[0];
        auto socket = new Socket(addr.addressFamily, SocketType.STREAM);
        socket.setOption(SocketOptionLevel.TCP, SocketOption.TCP_NODELAY, true);
        socket.connect(addr);
        stream = new BufferedStream(new SocketStream(socket));
        stream.write(token);
        stream.write(0);
        stream.write(3);
        stream.write(0);
        stream.flush();
    }

    void run()
    {
        auto myStrategy = new MyStrategy();
        //auto debugInterface = new DebugInterface(stream);
        while (true)
        {
            codegame.ServerMessage message = codegame.ServerMessage.readFrom(stream);
            if (auto getActionMessage = cast(codegame.ServerMessage.GetAction)(message))
            {
                new codegame.ClientMessage.ActionMessage(myStrategy.getAction(getActionMessage.playerView)).writeTo(stream);
                stream.flush();
            }
            else if (auto finishMessage = cast(codegame.ServerMessage.Finish)(message))
            {
                break;
            }
            else if (auto debugUpdateMessage = cast(codegame.ServerMessage.DebugUpdate)(message))
            {
                //myStrategy.debugUpdate(debugUpdateMessage.playerView, debugInterface);
                new codegame.ClientMessage.DebugUpdateDone().writeTo(stream);
                stream.flush();
            }
            else
            {
                throw new Error("Unexpected server message");
            }
        }
    }

private:
    Stream stream;
}

void main(string[] args)
{
    string host = args.length < 2 ? "127.0.0.1" : args[1];
    ushort port = args.length < 3 ? 31001 : to!ushort(args[2]);
    string token = args.length < 4 ? "0000000000000000" : args[3];

    new Runner(host, port, token).run();
}