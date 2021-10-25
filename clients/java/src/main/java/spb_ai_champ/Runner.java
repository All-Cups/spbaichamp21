package spb_ai_champ;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedOutputStream;

import spb_ai_champ.util.StreamUtil;

public class Runner {
    private final InputStream inputStream;
    private final OutputStream outputStream;

    Runner(String host, int port, String token) throws IOException {
        Socket socket = new Socket(host, port);
        socket.setTcpNoDelay(true);
        inputStream = new BufferedInputStream(socket.getInputStream());
        outputStream = new BufferedOutputStream(socket.getOutputStream());
        StreamUtil.writeString(outputStream, token);
        StreamUtil.writeInt(outputStream, 1);
        StreamUtil.writeInt(outputStream, 0);
        StreamUtil.writeInt(outputStream, 0);
        outputStream.flush();
    }

    void run() throws IOException {
        MyStrategy myStrategy = new MyStrategy();
        //DebugInterface debugInterface = new DebugInterface(inputStream, outputStream);
        while (true) {
            spb_ai_champ.codegame.ServerMessage message = spb_ai_champ.codegame.ServerMessage.readFrom(inputStream);
            if (message instanceof spb_ai_champ.codegame.ServerMessage.GetAction) {
                spb_ai_champ.codegame.ServerMessage.GetAction getActionMessage = (spb_ai_champ.codegame.ServerMessage.GetAction) message;
                new spb_ai_champ.codegame.ClientMessage.ActionMessage(myStrategy.getAction(getActionMessage.getPlayerView())).writeTo(outputStream);
                outputStream.flush();
            } else if (message instanceof spb_ai_champ.codegame.ServerMessage.Finish) {
                break;
            } else if (message instanceof spb_ai_champ.codegame.ServerMessage.DebugUpdate) {
                spb_ai_champ.codegame.ServerMessage.DebugUpdate debugUpdateMessage = (spb_ai_champ.codegame.ServerMessage.DebugUpdate) message;
                //myStrategy.debugUpdate(debugUpdateMessage.getPlayerView(), debugInterface);
                new spb_ai_champ.codegame.ClientMessage.DebugUpdateDone().writeTo(outputStream);
                outputStream.flush();
            } else {
                throw new IOException("Unexpected server message");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String host = args.length < 1 ? "127.0.0.1" : args[0];
        int port = args.length < 2 ? 31001 : Integer.parseInt(args[1]);
        String token = args.length < 3 ? "0000000000000000" : args[2];
        new Runner(host, port, token).run();
    }
}