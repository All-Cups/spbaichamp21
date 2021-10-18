module Model

# Group of workers on a planet
class WorkerGroup
    # Index of player controlling the workers
    attr_accessor :player_index
    # Number of workers in the group
    attr_accessor :number

    def initialize(player_index, number)
        @player_index = player_index
        @number = number
    end

    # Read WorkerGroup from input stream
    def self.read_from(stream)
        player_index = stream.read_int()
        number = stream.read_int()
        WorkerGroup.new(player_index, number)
    end

    # Write WorkerGroup to output stream
    def write_to(stream)
        stream.write_int(@player_index)
        stream.write_int(@number)
    end

    def to_s
        string_result = "WorkerGroup { "
        string_result += "player_index: "
        string_result += @player_index.to_s
        string_result += ", "
        string_result += "number: "
        string_result += @number.to_s
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end