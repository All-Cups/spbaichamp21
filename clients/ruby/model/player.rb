require './model/specialty'

module Model

# Player (game participant)
class Player
    # Team index
    attr_accessor :team_index
    # Current score points
    attr_accessor :score
    # Player's specialty
    attr_accessor :specialty

    def initialize(team_index, score, specialty)
        @team_index = team_index
        @score = score
        @specialty = specialty
    end

    # Read Player from input stream
    def self.read_from(stream)
        team_index = stream.read_int()
        score = stream.read_int()
        if stream.read_bool()
            specialty = Model::Specialty.read_from(stream)
        else
            specialty = nil
        end
        Player.new(team_index, score, specialty)
    end

    # Write Player to output stream
    def write_to(stream)
        stream.write_int(@team_index)
        stream.write_int(@score)
        if @specialty.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@specialty)
        end
    end

    def to_s
        string_result = "Player { "
        string_result += "team_index: "
        string_result += @team_index.to_s
        string_result += ", "
        string_result += "score: "
        string_result += @score.to_s
        string_result += ", "
        string_result += "specialty: "
        if @specialty.nil?
            string_result += "nil"
        else
            string_result += Specialty.to_s(@specialty)
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end