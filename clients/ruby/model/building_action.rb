require './model/building_type'

module Model

# TODO - Document
class BuildingAction
    # TODO - Document
    attr_accessor :planet
    # TODO - Document
    attr_accessor :building_type

    def initialize(planet, building_type)
        @planet = planet
        @building_type = building_type
    end

    # Read BuildingAction from input stream
    def self.read_from(stream)
        planet = stream.read_int()
        if stream.read_bool()
            building_type = Model::BuildingType.read_from(stream)
        else
            building_type = nil
        end
        BuildingAction.new(planet, building_type)
    end

    # Write BuildingAction to output stream
    def write_to(stream)
        stream.write_int(@planet)
        if @building_type.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@building_type)
        end
    end

    def to_s
        string_result = "BuildingAction { "
        string_result += "planet: "
        string_result += @planet.to_s
        string_result += ", "
        string_result += "building_type: "
        if @building_type.nil?
            string_result += "nil"
        else
            string_result += BuildingType.to_s(@building_type)
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end