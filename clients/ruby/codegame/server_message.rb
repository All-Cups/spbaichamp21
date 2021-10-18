require './model/game'

module Codegame

# Message sent from server
class ServerMessage
    # Read ServerMessage from input stream
    def self.read_from(stream)
        tag = stream.read_int()
        if tag == ServerMessage::GetAction::TAG
            return ServerMessage::GetAction.read_from(stream)
        end
        if tag == ServerMessage::Finish::TAG
            return ServerMessage::Finish.read_from(stream)
        end
        if tag == ServerMessage::DebugUpdate::TAG
            return ServerMessage::DebugUpdate.read_from(stream)
        end
        raise "Unexpected tag value"
    end

    # Get action for next tick
    class GetAction
        TAG = 0
    
        # Player's view
        attr_accessor :player_view
        # Whether app is running with debug interface available
        attr_accessor :debug_available
    
        def initialize(player_view, debug_available)
            @player_view = player_view
            @debug_available = debug_available
        end
    
        # Read GetAction from input stream
        def self.read_from(stream)
            player_view = Model::Game.read_from(stream)
            debug_available = stream.read_bool()
            GetAction.new(player_view, debug_available)
        end
    
        # Write GetAction to output stream
        def write_to(stream)
            stream.write_int(TAG)
            @player_view.write_to(stream)
            stream.write_bool(@debug_available)
        end
    
        def to_s
            string_result = "GetAction { "
            string_result += "player_view: "
            string_result += @player_view.to_s
            string_result += ", "
            string_result += "debug_available: "
            string_result += @debug_available.to_s
            string_result += " }"
            string_result
        end
    
        def to_str
            to_s
        end
    end
    # Signifies end of the game
    class Finish
        TAG = 1
    
    
        def initialize()
        end
    
        # Read Finish from input stream
        def self.read_from(stream)
            Finish.new()
        end
    
        # Write Finish to output stream
        def write_to(stream)
            stream.write_int(TAG)
        end
    
        def to_s
            string_result = "Finish { "
            string_result += " }"
            string_result
        end
    
        def to_str
            to_s
        end
    end
    # Debug update
    class DebugUpdate
        TAG = 2
    
        # Player's view
        attr_accessor :player_view
    
        def initialize(player_view)
            @player_view = player_view
        end
    
        # Read DebugUpdate from input stream
        def self.read_from(stream)
            player_view = Model::Game.read_from(stream)
            DebugUpdate.new(player_view)
        end
    
        # Write DebugUpdate to output stream
        def write_to(stream)
            stream.write_int(TAG)
            @player_view.write_to(stream)
        end
    
        def to_s
            string_result = "DebugUpdate { "
            string_result += "player_view: "
            string_result += @player_view.to_s
            string_result += " }"
            string_result
        end
    
        def to_str
            to_s
        end
    end
end

end