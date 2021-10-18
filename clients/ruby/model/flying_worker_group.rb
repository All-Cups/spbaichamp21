require './model/resource'

module Model

# A flying worker group
class FlyingWorkerGroup
    # Index of player controlling workers
    attr_accessor :player_index
    # Number of workers in the group
    attr_accessor :number
    # Tick when workers left previous planet on their path
    attr_accessor :departure_tick
    # Id of the previous planet on the path
    attr_accessor :departure_planet
    # Tick when workers will arrive to the next planet in their path
    attr_accessor :next_planet_arrival_tick
    # Id of the next planet in the path
    attr_accessor :next_planet
    # Id of the target planet
    attr_accessor :target_planet
    # Resource that workers are carrying
    attr_accessor :resource

    def initialize(player_index, number, departure_tick, departure_planet, next_planet_arrival_tick, next_planet, target_planet, resource)
        @player_index = player_index
        @number = number
        @departure_tick = departure_tick
        @departure_planet = departure_planet
        @next_planet_arrival_tick = next_planet_arrival_tick
        @next_planet = next_planet
        @target_planet = target_planet
        @resource = resource
    end

    # Read FlyingWorkerGroup from input stream
    def self.read_from(stream)
        player_index = stream.read_int()
        number = stream.read_int()
        departure_tick = stream.read_int()
        departure_planet = stream.read_int()
        next_planet_arrival_tick = stream.read_int()
        next_planet = stream.read_int()
        target_planet = stream.read_int()
        if stream.read_bool()
            resource = Model::Resource.read_from(stream)
        else
            resource = nil
        end
        FlyingWorkerGroup.new(player_index, number, departure_tick, departure_planet, next_planet_arrival_tick, next_planet, target_planet, resource)
    end

    # Write FlyingWorkerGroup to output stream
    def write_to(stream)
        stream.write_int(@player_index)
        stream.write_int(@number)
        stream.write_int(@departure_tick)
        stream.write_int(@departure_planet)
        stream.write_int(@next_planet_arrival_tick)
        stream.write_int(@next_planet)
        stream.write_int(@target_planet)
        if @resource.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@resource)
        end
    end

    def to_s
        string_result = "FlyingWorkerGroup { "
        string_result += "player_index: "
        string_result += @player_index.to_s
        string_result += ", "
        string_result += "number: "
        string_result += @number.to_s
        string_result += ", "
        string_result += "departure_tick: "
        string_result += @departure_tick.to_s
        string_result += ", "
        string_result += "departure_planet: "
        string_result += @departure_planet.to_s
        string_result += ", "
        string_result += "next_planet_arrival_tick: "
        string_result += @next_planet_arrival_tick.to_s
        string_result += ", "
        string_result += "next_planet: "
        string_result += @next_planet.to_s
        string_result += ", "
        string_result += "target_planet: "
        string_result += @target_planet.to_s
        string_result += ", "
        string_result += "resource: "
        if @resource.nil?
            string_result += "nil"
        else
            string_result += Resource.to_s(@resource)
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end