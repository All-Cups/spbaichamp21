import java.io.{BufferedInputStream, BufferedOutputStream}
import java.net.Socket

import spb_ai_champ.util.StreamUtil

object Runner extends App {

  val host = if (args.length < 1) "127.0.0.1" else args(0)
  val port = if (args.length < 2) 31001 else args(1).toInt
  val token = if (args.length < 3) "0000000000000000" else args(2)

  run(host, port, token)

  def run(host: String, port: Int, token: String) {
    val socket = new Socket(host, port)
    socket.setTcpNoDelay(true)
    val inputStream = new BufferedInputStream(socket.getInputStream)
    val outputStream = new BufferedOutputStream(socket.getOutputStream)

    StreamUtil.writeString(outputStream, token)
    StreamUtil.writeInt(outputStream, 0)
    StreamUtil.writeInt(outputStream, 4)
    StreamUtil.writeInt(outputStream, 0)
    outputStream.flush()

    val myStrategy = new MyStrategy()
    //val debugInterface = new DebugInterface(inputStream, outputStream)
    while (true) {
      spb_ai_champ.codegame.ServerMessage.readFrom(inputStream) match {
        case spb_ai_champ.codegame.ServerMessage.GetAction(playerView, debugAvailable) =>
          spb_ai_champ.codegame.ClientMessage.ActionMessage(myStrategy.getAction(playerView)).writeTo(outputStream)
          outputStream.flush()
        case spb_ai_champ.codegame.ServerMessage.Finish() => return
        case spb_ai_champ.codegame.ServerMessage.DebugUpdate(playerView) =>
          //myStrategy.debugUpdate(playerView, debugInterface)
          spb_ai_champ.codegame.ClientMessage.DebugUpdateDone().writeTo(outputStream)
          outputStream.flush()
      }
    }
  }
}