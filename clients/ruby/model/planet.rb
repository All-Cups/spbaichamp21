require './model/building'
require './model/resource'
require './model/worker_group'

module Model

# TODO - Document
class Planet
    # TODO - Document
    attr_accessor :id
    # TODO - Document
    attr_accessor :x
    # TODO - Document
    attr_accessor :y
    # TODO - Document
    attr_accessor :harvestable_resource
    # TODO - Document
    attr_accessor :worker_groups
    # TODO - Document
    attr_accessor :resources
    # TODO - Document
    attr_accessor :building

    def initialize(id, x, y, harvestable_resource, worker_groups, resources, building)
        @id = id
        @x = x
        @y = y
        @harvestable_resource = harvestable_resource
        @worker_groups = worker_groups
        @resources = resources
        @building = building
    end

    # Read Planet from input stream
    def self.read_from(stream)
        id = stream.read_int()
        x = stream.read_int()
        y = stream.read_int()
        if stream.read_bool()
            harvestable_resource = Model::Resource.read_from(stream)
        else
            harvestable_resource = nil
        end
        worker_groups = []
        stream.read_int().times do |_|
            worker_groups_element = Model::WorkerGroup.read_from(stream)
            worker_groups.push(worker_groups_element)
        end
        resources = Hash.new
        stream.read_int().times do |_|
            resources_key = Model::Resource.read_from(stream)
            resources_value = stream.read_int()
            resources[resources_key] = resources_value
        end
        if stream.read_bool()
            building = Model::Building.read_from(stream)
        else
            building = nil
        end
        Planet.new(id, x, y, harvestable_resource, worker_groups, resources, building)
    end

    # Write Planet to output stream
    def write_to(stream)
        stream.write_int(@id)
        stream.write_int(@x)
        stream.write_int(@y)
        if @harvestable_resource.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@harvestable_resource)
        end
        stream.write_int(@worker_groups.length())
        @worker_groups.each do |worker_groups_element|
            worker_groups_element.write_to(stream)
        end
        stream.write_int(@resources.length())
        @resources.each do |resources_key, resources_value|
            stream.write_int(resources_key)
            stream.write_int(resources_value)
        end
        if @building.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @building.write_to(stream)
        end
    end

    def to_s
        string_result = "Planet { "
        string_result += "id: "
        string_result += @id.to_s
        string_result += ", "
        string_result += "x: "
        string_result += @x.to_s
        string_result += ", "
        string_result += "y: "
        string_result += @y.to_s
        string_result += ", "
        string_result += "harvestable_resource: "
        if @harvestable_resource.nil?
            string_result += "nil"
        else
            string_result += Resource.to_s(@harvestable_resource)
        end
        string_result += ", "
        string_result += "worker_groups: "
        string_result += "[ "
        worker_groups_index = 0
        @worker_groups.each do |worker_groups_element|
            if worker_groups_index != 0
                string_result += ", "
            end
            string_result += worker_groups_element.to_s
            worker_groups_index += 1
        end
        string_result += " ]"
        string_result += ", "
        string_result += "resources: "
        string_result += "{ "
        resources_index = 0
        @resources.each do |resources_key, resources_value|
            if resources_index != 0
                string_result += ", "
            end
            string_result += Resource.to_s(resources_key)
            string_result += " => "
            string_result += resources_value.to_s
            resources_index += 1
        end
        string_result += " }"
        string_result += ", "
        string_result += "building: "
        if @building.nil?
            string_result += "nil"
        else
            string_result += @building.to_s
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end