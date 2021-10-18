require './model/resource'

module Model

# TODO - Document
class MoveAction
    # TODO - Document
    attr_accessor :start_planet
    # TODO - Document
    attr_accessor :target_planet
    # TODO - Document
    attr_accessor :worker_number
    # TODO - Document
    attr_accessor :take_resource

    def initialize(start_planet, target_planet, worker_number, take_resource)
        @start_planet = start_planet
        @target_planet = target_planet
        @worker_number = worker_number
        @take_resource = take_resource
    end

    # Read MoveAction from input stream
    def self.read_from(stream)
        start_planet = stream.read_int()
        target_planet = stream.read_int()
        worker_number = stream.read_int()
        if stream.read_bool()
            take_resource = Model::Resource.read_from(stream)
        else
            take_resource = nil
        end
        MoveAction.new(start_planet, target_planet, worker_number, take_resource)
    end

    # Write MoveAction to output stream
    def write_to(stream)
        stream.write_int(@start_planet)
        stream.write_int(@target_planet)
        stream.write_int(@worker_number)
        if @take_resource.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@take_resource)
        end
    end

    def to_s
        string_result = "MoveAction { "
        string_result += "start_planet: "
        string_result += @start_planet.to_s
        string_result += ", "
        string_result += "target_planet: "
        string_result += @target_planet.to_s
        string_result += ", "
        string_result += "worker_number: "
        string_result += @worker_number.to_s
        string_result += ", "
        string_result += "take_resource: "
        if @take_resource.nil?
            string_result += "nil"
        else
            string_result += Resource.to_s(@take_resource)
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end