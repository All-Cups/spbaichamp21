//#include "DebugInterface.hpp"
#include "MyStrategy.hpp"
#include "TcpStream.hpp"
#include "codegame/ServerMessage.hpp"
#include "codegame/ClientMessage.hpp"
#include <memory>
#include <string>

class Runner {
public:
    Runner(const std::string& host, int port, const std::string& token): tcpStream(host, port)
    {
        tcpStream.write(token);
        tcpStream.write(int(1));
        tcpStream.write(int(0));
        tcpStream.write(int(0));
        tcpStream.flush();
    }
    void run()
    {
        //DebugInterface debugInterface(tcpStream);
        MyStrategy myStrategy;
        while (true) {
            auto message = codegame::ServerMessage::readFrom(tcpStream);
            if (auto getActionMessage = std::dynamic_pointer_cast<codegame::ServerMessage::GetAction>(message)) {
                codegame::ClientMessage::ActionMessage(myStrategy.getAction(getActionMessage->playerView)).writeTo(tcpStream);
                tcpStream.flush();
            } else if (auto finishMessage = std::dynamic_pointer_cast<codegame::ServerMessage::Finish>(message)) {
                break;
            } else if (auto debugUpdateMessage = std::dynamic_pointer_cast<codegame::ServerMessage::DebugUpdate>(message)) {
                //myStrategy.debugUpdate(debugUpdateMessage->playerView, debugInterface);
                codegame::ClientMessage::DebugUpdateDone().writeTo(tcpStream);
                tcpStream.flush();
            }
        }
    }

private:
    TcpStream tcpStream;
};

int main(int argc, char* argv[])
{
    std::string host = argc < 2 ? "127.0.0.1" : argv[1];
    int port = argc < 3 ? 31001 : atoi(argv[2]);
    std::string token = argc < 4 ? "0000000000000000" : argv[3];
    Runner(host, port, token).run();
    return 0;
}