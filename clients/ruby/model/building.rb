require './model/building_type'

module Model

# A building
class Building
    # Building's type
    attr_accessor :building_type
    # Current health
    attr_accessor :health
    # Amount of work done for current task
    attr_accessor :work_done

    def initialize(building_type, health, work_done)
        @building_type = building_type
        @health = health
        @work_done = work_done
    end

    # Read Building from input stream
    def self.read_from(stream)
        building_type = Model::BuildingType.read_from(stream)
        health = stream.read_int()
        work_done = stream.read_int()
        Building.new(building_type, health, work_done)
    end

    # Write Building to output stream
    def write_to(stream)
        stream.write_int(@building_type)
        stream.write_int(@health)
        stream.write_int(@work_done)
    end

    def to_s
        string_result = "Building { "
        string_result += "building_type: "
        string_result += BuildingType.to_s(@building_type)
        string_result += ", "
        string_result += "health: "
        string_result += @health.to_s
        string_result += ", "
        string_result += "work_done: "
        string_result += @work_done.to_s
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end