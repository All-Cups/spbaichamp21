require_relative 'tcp_stream'
require './codegame/server_message'
require './codegame/client_message'
require_relative 'my_strategy'
#require_relative 'debug_interface'

class Runner
    def initialize(host, port, token)
        @stream = TcpStream.new(host, port)
        @token = token
        @stream.write_string(@token)
        @stream.write_int(0)
        @stream.write_int(3)
        @stream.write_int(0)
        @stream.flush()
    end

    def run()
        strategy = MyStrategy.new()
        # debug_interface = DebugInterface.new(@stream)

        while true
            message = Codegame::ServerMessage.read_from(@stream)
            if message.instance_of? Codegame::ServerMessage::GetAction
                Codegame::ClientMessage::ActionMessage.new(strategy.get_action(message.player_view)).write_to(@stream)
                @stream.flush()
            elsif message.instance_of? Codegame::ServerMessage::Finish
                break
            elsif message.instance_of? Codegame::ServerMessage::DebugUpdate
                #strategy.debug_update(message.player_view, debug_interface)
                Codegame::ClientMessage::DebugUpdateDone.new().write_to(@stream)
                @stream.flush()
            else
                raise "Unexpected server message"
            end
        end
    end
end

host = ARGV.length < 1 ? "127.0.0.1" : ARGV[0]
port = ARGV.length < 2 ? 31001 : ARGV[1].to_i
token = ARGV.length < 3 ? "0000000000000000" : ARGV[2]
Runner.new(host, port, token).run()