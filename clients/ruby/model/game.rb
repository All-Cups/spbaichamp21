require './model/building_properties'
require './model/building_type'
require './model/flying_worker_group'
require './model/planet'
require './model/player'

module Model

# TODO - Document
class Game
    # TODO - Document
    attr_accessor :my_index
    # TODO - Document
    attr_accessor :current_tick
    # TODO - Document
    attr_accessor :max_tick_count
    # TODO - Document
    attr_accessor :players
    # TODO - Document
    attr_accessor :planets
    # TODO - Document
    attr_accessor :flying_worker_groups
    # TODO - Document
    attr_accessor :max_flying_worker_groups
    # TODO - Document
    attr_accessor :max_travel_distance
    # TODO - Document
    attr_accessor :max_builders
    # TODO - Document
    attr_accessor :building_properties

    def initialize(my_index, current_tick, max_tick_count, players, planets, flying_worker_groups, max_flying_worker_groups, max_travel_distance, max_builders, building_properties)
        @my_index = my_index
        @current_tick = current_tick
        @max_tick_count = max_tick_count
        @players = players
        @planets = planets
        @flying_worker_groups = flying_worker_groups
        @max_flying_worker_groups = max_flying_worker_groups
        @max_travel_distance = max_travel_distance
        @max_builders = max_builders
        @building_properties = building_properties
    end

    # Read Game from input stream
    def self.read_from(stream)
        my_index = stream.read_int()
        current_tick = stream.read_int()
        max_tick_count = stream.read_int()
        players = []
        stream.read_int().times do |_|
            players_element = Model::Player.read_from(stream)
            players.push(players_element)
        end
        planets = []
        stream.read_int().times do |_|
            planets_element = Model::Planet.read_from(stream)
            planets.push(planets_element)
        end
        flying_worker_groups = []
        stream.read_int().times do |_|
            flying_worker_groups_element = Model::FlyingWorkerGroup.read_from(stream)
            flying_worker_groups.push(flying_worker_groups_element)
        end
        max_flying_worker_groups = stream.read_int()
        max_travel_distance = stream.read_int()
        max_builders = stream.read_int()
        building_properties = Hash.new
        stream.read_int().times do |_|
            building_properties_key = Model::BuildingType.read_from(stream)
            building_properties_value = Model::BuildingProperties.read_from(stream)
            building_properties[building_properties_key] = building_properties_value
        end
        Game.new(my_index, current_tick, max_tick_count, players, planets, flying_worker_groups, max_flying_worker_groups, max_travel_distance, max_builders, building_properties)
    end

    # Write Game to output stream
    def write_to(stream)
        stream.write_int(@my_index)
        stream.write_int(@current_tick)
        stream.write_int(@max_tick_count)
        stream.write_int(@players.length())
        @players.each do |players_element|
            players_element.write_to(stream)
        end
        stream.write_int(@planets.length())
        @planets.each do |planets_element|
            planets_element.write_to(stream)
        end
        stream.write_int(@flying_worker_groups.length())
        @flying_worker_groups.each do |flying_worker_groups_element|
            flying_worker_groups_element.write_to(stream)
        end
        stream.write_int(@max_flying_worker_groups)
        stream.write_int(@max_travel_distance)
        stream.write_int(@max_builders)
        stream.write_int(@building_properties.length())
        @building_properties.each do |building_properties_key, building_properties_value|
            stream.write_int(building_properties_key)
            building_properties_value.write_to(stream)
        end
    end

    def to_s
        string_result = "Game { "
        string_result += "my_index: "
        string_result += @my_index.to_s
        string_result += ", "
        string_result += "current_tick: "
        string_result += @current_tick.to_s
        string_result += ", "
        string_result += "max_tick_count: "
        string_result += @max_tick_count.to_s
        string_result += ", "
        string_result += "players: "
        string_result += "[ "
        players_index = 0
        @players.each do |players_element|
            if players_index != 0
                string_result += ", "
            end
            string_result += players_element.to_s
            players_index += 1
        end
        string_result += " ]"
        string_result += ", "
        string_result += "planets: "
        string_result += "[ "
        planets_index = 0
        @planets.each do |planets_element|
            if planets_index != 0
                string_result += ", "
            end
            string_result += planets_element.to_s
            planets_index += 1
        end
        string_result += " ]"
        string_result += ", "
        string_result += "flying_worker_groups: "
        string_result += "[ "
        flying_worker_groups_index = 0
        @flying_worker_groups.each do |flying_worker_groups_element|
            if flying_worker_groups_index != 0
                string_result += ", "
            end
            string_result += flying_worker_groups_element.to_s
            flying_worker_groups_index += 1
        end
        string_result += " ]"
        string_result += ", "
        string_result += "max_flying_worker_groups: "
        string_result += @max_flying_worker_groups.to_s
        string_result += ", "
        string_result += "max_travel_distance: "
        string_result += @max_travel_distance.to_s
        string_result += ", "
        string_result += "max_builders: "
        string_result += @max_builders.to_s
        string_result += ", "
        string_result += "building_properties: "
        string_result += "{ "
        building_properties_index = 0
        @building_properties.each do |building_properties_key, building_properties_value|
            if building_properties_index != 0
                string_result += ", "
            end
            string_result += BuildingType.to_s(building_properties_key)
            string_result += " => "
            string_result += building_properties_value.to_s
            building_properties_index += 1
        end
        string_result += " }"
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end