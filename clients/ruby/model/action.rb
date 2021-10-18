require './model/building_action'
require './model/move_action'
require './model/specialty'

module Model

# Player's actions
class Action
    # List of movement orders
    attr_accessor :moves
    # List of building orders
    attr_accessor :buildings
    # Choosing specialty
    attr_accessor :choose_specialty

    def initialize(moves, buildings, choose_specialty)
        @moves = moves
        @buildings = buildings
        @choose_specialty = choose_specialty
    end

    # Read Action from input stream
    def self.read_from(stream)
        moves = []
        stream.read_int().times do |_|
            moves_element = Model::MoveAction.read_from(stream)
            moves.push(moves_element)
        end
        buildings = []
        stream.read_int().times do |_|
            buildings_element = Model::BuildingAction.read_from(stream)
            buildings.push(buildings_element)
        end
        if stream.read_bool()
            choose_specialty = Model::Specialty.read_from(stream)
        else
            choose_specialty = nil
        end
        Action.new(moves, buildings, choose_specialty)
    end

    # Write Action to output stream
    def write_to(stream)
        stream.write_int(@moves.length())
        @moves.each do |moves_element|
            moves_element.write_to(stream)
        end
        stream.write_int(@buildings.length())
        @buildings.each do |buildings_element|
            buildings_element.write_to(stream)
        end
        if @choose_specialty.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@choose_specialty)
        end
    end

    def to_s
        string_result = "Action { "
        string_result += "moves: "
        string_result += "[ "
        moves_index = 0
        @moves.each do |moves_element|
            if moves_index != 0
                string_result += ", "
            end
            string_result += moves_element.to_s
            moves_index += 1
        end
        string_result += " ]"
        string_result += ", "
        string_result += "buildings: "
        string_result += "[ "
        buildings_index = 0
        @buildings.each do |buildings_element|
            if buildings_index != 0
                string_result += ", "
            end
            string_result += buildings_element.to_s
            buildings_index += 1
        end
        string_result += " ]"
        string_result += ", "
        string_result += "choose_specialty: "
        if @choose_specialty.nil?
            string_result += "nil"
        else
            string_result += Specialty.to_s(@choose_specialty)
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end