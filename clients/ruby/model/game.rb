require './model/building_properties'
require './model/building_type'
require './model/flying_worker_group'
require './model/planet'
require './model/player'

module Model

# Current game's state
class Game
    # Your player's index
    attr_accessor :my_index
    # Current tick number
    attr_accessor :current_tick
    # Max number of ticks in the game
    attr_accessor :max_tick_count
    # List of players
    attr_accessor :players
    # List of planets
    attr_accessor :planets
    # List of flying worker groups
    attr_accessor :flying_worker_groups
    # Max number of flying worker groups for one player
    attr_accessor :max_flying_worker_groups
    # Max distance of direct travel between planets
    attr_accessor :max_travel_distance
    # Additional distance of direct travel between planets for player with Logistics specialty
    attr_accessor :logistics_upgrade
    # Additional work done by player with Production specialty (in percent)
    attr_accessor :production_upgrade
    # Additional strength workers for player with Combat specialty (in percent)
    attr_accessor :combat_upgrade
    # Max number of workers performing building on one planet
    attr_accessor :max_builders
    # Properties of every building type
    attr_accessor :building_properties
    # Whether choosing specialties is allowed
    attr_accessor :specialties_allowed
    # View distance
    attr_accessor :view_distance

    def initialize(my_index, current_tick, max_tick_count, players, planets, flying_worker_groups, max_flying_worker_groups, max_travel_distance, logistics_upgrade, production_upgrade, combat_upgrade, max_builders, building_properties, specialties_allowed, view_distance)
        @my_index = my_index
        @current_tick = current_tick
        @max_tick_count = max_tick_count
        @players = players
        @planets = planets
        @flying_worker_groups = flying_worker_groups
        @max_flying_worker_groups = max_flying_worker_groups
        @max_travel_distance = max_travel_distance
        @logistics_upgrade = logistics_upgrade
        @production_upgrade = production_upgrade
        @combat_upgrade = combat_upgrade
        @max_builders = max_builders
        @building_properties = building_properties
        @specialties_allowed = specialties_allowed
        @view_distance = view_distance
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
        logistics_upgrade = stream.read_int()
        production_upgrade = stream.read_int()
        combat_upgrade = stream.read_int()
        max_builders = stream.read_int()
        building_properties = Hash.new
        stream.read_int().times do |_|
            building_properties_key = Model::BuildingType.read_from(stream)
            building_properties_value = Model::BuildingProperties.read_from(stream)
            building_properties[building_properties_key] = building_properties_value
        end
        specialties_allowed = stream.read_bool()
        if stream.read_bool()
            view_distance = stream.read_int()
        else
            view_distance = nil
        end
        Game.new(my_index, current_tick, max_tick_count, players, planets, flying_worker_groups, max_flying_worker_groups, max_travel_distance, logistics_upgrade, production_upgrade, combat_upgrade, max_builders, building_properties, specialties_allowed, view_distance)
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
        stream.write_int(@logistics_upgrade)
        stream.write_int(@production_upgrade)
        stream.write_int(@combat_upgrade)
        stream.write_int(@max_builders)
        stream.write_int(@building_properties.length())
        @building_properties.each do |building_properties_key, building_properties_value|
            stream.write_int(building_properties_key)
            building_properties_value.write_to(stream)
        end
        stream.write_bool(@specialties_allowed)
        if @view_distance.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@view_distance)
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
        string_result += "logistics_upgrade: "
        string_result += @logistics_upgrade.to_s
        string_result += ", "
        string_result += "production_upgrade: "
        string_result += @production_upgrade.to_s
        string_result += ", "
        string_result += "combat_upgrade: "
        string_result += @combat_upgrade.to_s
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
        string_result += ", "
        string_result += "specialties_allowed: "
        string_result += @specialties_allowed.to_s
        string_result += ", "
        string_result += "view_distance: "
        if @view_distance.nil?
            string_result += "nil"
        else
            string_result += @view_distance.to_s
        end
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end