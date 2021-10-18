require './model/resource'

module Model

# TODO - Document
class BuildingProperties
    # TODO - Document
    attr_accessor :build_resources
    # TODO - Document
    attr_accessor :max_health
    # TODO - Document
    attr_accessor :max_workers
    # TODO - Document
    attr_accessor :work_resources
    # TODO - Document
    attr_accessor :produce_worker
    # TODO - Document
    attr_accessor :produce_resource
    # TODO - Document
    attr_accessor :produce_amount
    # TODO - Document
    attr_accessor :produce_score
    # TODO - Document
    attr_accessor :harvest
    # TODO - Document
    attr_accessor :work_amount

    def initialize(build_resources, max_health, max_workers, work_resources, produce_worker, produce_resource, produce_amount, produce_score, harvest, work_amount)
        @build_resources = build_resources
        @max_health = max_health
        @max_workers = max_workers
        @work_resources = work_resources
        @produce_worker = produce_worker
        @produce_resource = produce_resource
        @produce_amount = produce_amount
        @produce_score = produce_score
        @harvest = harvest
        @work_amount = work_amount
    end

    # Read BuildingProperties from input stream
    def self.read_from(stream)
        build_resources = Hash.new
        stream.read_int().times do |_|
            build_resources_key = Model::Resource.read_from(stream)
            build_resources_value = stream.read_int()
            build_resources[build_resources_key] = build_resources_value
        end
        max_health = stream.read_int()
        max_workers = stream.read_int()
        work_resources = Hash.new
        stream.read_int().times do |_|
            work_resources_key = Model::Resource.read_from(stream)
            work_resources_value = stream.read_int()
            work_resources[work_resources_key] = work_resources_value
        end
        produce_worker = stream.read_bool()
        if stream.read_bool()
            produce_resource = Model::Resource.read_from(stream)
        else
            produce_resource = nil
        end
        produce_amount = stream.read_int()
        produce_score = stream.read_int()
        harvest = stream.read_bool()
        work_amount = stream.read_int()
        BuildingProperties.new(build_resources, max_health, max_workers, work_resources, produce_worker, produce_resource, produce_amount, produce_score, harvest, work_amount)
    end

    # Write BuildingProperties to output stream
    def write_to(stream)
        stream.write_int(@build_resources.length())
        @build_resources.each do |build_resources_key, build_resources_value|
            stream.write_int(build_resources_key)
            stream.write_int(build_resources_value)
        end
        stream.write_int(@max_health)
        stream.write_int(@max_workers)
        stream.write_int(@work_resources.length())
        @work_resources.each do |work_resources_key, work_resources_value|
            stream.write_int(work_resources_key)
            stream.write_int(work_resources_value)
        end
        stream.write_bool(@produce_worker)
        if @produce_resource.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@produce_resource)
        end
        stream.write_int(@produce_amount)
        stream.write_int(@produce_score)
        stream.write_bool(@harvest)
        stream.write_int(@work_amount)
    end

    def to_s
        string_result = "BuildingProperties { "
        string_result += "build_resources: "
        string_result += "{ "
        build_resources_index = 0
        @build_resources.each do |build_resources_key, build_resources_value|
            if build_resources_index != 0
                string_result += ", "
            end
            string_result += Resource.to_s(build_resources_key)
            string_result += " => "
            string_result += build_resources_value.to_s
            build_resources_index += 1
        end
        string_result += " }"
        string_result += ", "
        string_result += "max_health: "
        string_result += @max_health.to_s
        string_result += ", "
        string_result += "max_workers: "
        string_result += @max_workers.to_s
        string_result += ", "
        string_result += "work_resources: "
        string_result += "{ "
        work_resources_index = 0
        @work_resources.each do |work_resources_key, work_resources_value|
            if work_resources_index != 0
                string_result += ", "
            end
            string_result += Resource.to_s(work_resources_key)
            string_result += " => "
            string_result += work_resources_value.to_s
            work_resources_index += 1
        end
        string_result += " }"
        string_result += ", "
        string_result += "produce_worker: "
        string_result += @produce_worker.to_s
        string_result += ", "
        string_result += "produce_resource: "
        if @produce_resource.nil?
            string_result += "nil"
        else
            string_result += Resource.to_s(@produce_resource)
        end
        string_result += ", "
        string_result += "produce_amount: "
        string_result += @produce_amount.to_s
        string_result += ", "
        string_result += "produce_score: "
        string_result += @produce_score.to_s
        string_result += ", "
        string_result += "harvest: "
        string_result += @harvest.to_s
        string_result += ", "
        string_result += "work_amount: "
        string_result += @work_amount.to_s
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end