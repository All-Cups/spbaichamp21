import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import spb_ai_champ.util.StreamUtil

fun main(args: Array<String>) {
    val host = if (args.size < 1) "127.0.0.1" else args[0]
    val port = if (args.size < 2) 31001 else Integer.parseInt(args[1])
    val token = if (args.size < 3) "0000000000000000" else args[2]
    
    val socket = Socket(host, port)
    socket.tcpNoDelay = true
    val inputStream = BufferedInputStream(socket.getInputStream())
    val outputStream = BufferedOutputStream(socket.getOutputStream())
    StreamUtil.writeString(outputStream, token)
    StreamUtil.writeInt(outputStream, 0)
    StreamUtil.writeInt(outputStream, 4)
    StreamUtil.writeInt(outputStream, 0)
    outputStream.flush()
    
    val myStrategy = MyStrategy()
    //val debugInterface = DebugInterface(inputStream, outputStream)
    while (true) {
        val message = spb_ai_champ.codegame.ServerMessage.readFrom(inputStream)
        if (message is spb_ai_champ.codegame.ServerMessage.GetAction) {
            spb_ai_champ.codegame.ClientMessage.ActionMessage(myStrategy.getAction(message.playerView)).writeTo(outputStream)
            outputStream.flush()
        } else if (message is spb_ai_champ.codegame.ServerMessage.Finish) {
            break
        } else if (message is spb_ai_champ.codegame.ServerMessage.DebugUpdate) {
            //myStrategy.debugUpdate(message.playerView, debugInterface)
            spb_ai_champ.codegame.ClientMessage.DebugUpdateDone().writeTo(outputStream)
            outputStream.flush()
        }
    }
}