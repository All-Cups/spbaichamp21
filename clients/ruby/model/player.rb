module Model

# TODO - Document
class Player
    # TODO - Document
    attr_accessor :score

    def initialize(score)
        @score = score
    end

    # Read Player from input stream
    def self.read_from(stream)
        score = stream.read_int()
        Player.new(score)
    end

    # Write Player to output stream
    def write_to(stream)
        stream.write_int(@score)
    end

    def to_s
        string_result = "Player { "
        string_result += "score: "
        string_result += @score.to_s
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end