from model.building_properties import BuildingProperties
from model.building_type import BuildingType
from model.flying_worker_group import FlyingWorkerGroup
from model.planet import Planet
from model.player import Player
from stream_wrapper import StreamWrapper
from typing import Dict
from typing import List
from typing import Optional

class Game:
    """Current game's state"""

    __slots__ = ("my_index","current_tick","max_tick_count","players","planets","flying_worker_groups","max_flying_worker_groups","max_travel_distance","logistics_upgrade","production_upgrade","combat_upgrade","max_builders","building_properties","specialties_allowed","view_distance",)

    my_index: int
    current_tick: int
    max_tick_count: int
    players: List[Player]
    planets: List[Planet]
    flying_worker_groups: List[FlyingWorkerGroup]
    max_flying_worker_groups: int
    max_travel_distance: int
    logistics_upgrade: int
    production_upgrade: int
    combat_upgrade: int
    max_builders: int
    building_properties: Dict[BuildingType, BuildingProperties]
    specialties_allowed: bool
    view_distance: Optional[int]

    def __init__(self, my_index: int, current_tick: int, max_tick_count: int, players: List[Player], planets: List[Planet], flying_worker_groups: List[FlyingWorkerGroup], max_flying_worker_groups: int, max_travel_distance: int, logistics_upgrade: int, production_upgrade: int, combat_upgrade: int, max_builders: int, building_properties: Dict[BuildingType, BuildingProperties], specialties_allowed: bool, view_distance: Optional[int]):
        self.my_index = my_index
        """Your player's index"""
        self.current_tick = current_tick
        """Current tick number"""
        self.max_tick_count = max_tick_count
        """Max number of ticks in the game"""
        self.players = players
        """List of players"""
        self.planets = planets
        """List of planets"""
        self.flying_worker_groups = flying_worker_groups
        """List of flying worker groups"""
        self.max_flying_worker_groups = max_flying_worker_groups
        """Max number of flying worker groups for one player"""
        self.max_travel_distance = max_travel_distance
        """Max distance of direct travel between planets"""
        self.logistics_upgrade = logistics_upgrade
        """Additional distance of direct travel between planets for player with Logistics specialty"""
        self.production_upgrade = production_upgrade
        """Additional work done by player with Production specialty (in percent)"""
        self.combat_upgrade = combat_upgrade
        """Additional strength workers for player with Combat specialty (in percent)"""
        self.max_builders = max_builders
        """Max number of workers performing building on one planet"""
        self.building_properties = building_properties
        """Properties of every building type"""
        self.specialties_allowed = specialties_allowed
        """Whether choosing specialties is allowed"""
        self.view_distance = view_distance
        """View distance"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "Game":
        """Read Game from input stream
        """
        my_index = stream.read_int()
        current_tick = stream.read_int()
        max_tick_count = stream.read_int()
        players = []
        for _ in range(stream.read_int()):
            players_element = Player.read_from(stream)
            players.append(players_element)
        planets = []
        for _ in range(stream.read_int()):
            planets_element = Planet.read_from(stream)
            planets.append(planets_element)
        flying_worker_groups = []
        for _ in range(stream.read_int()):
            flying_worker_groups_element = FlyingWorkerGroup.read_from(stream)
            flying_worker_groups.append(flying_worker_groups_element)
        max_flying_worker_groups = stream.read_int()
        max_travel_distance = stream.read_int()
        logistics_upgrade = stream.read_int()
        production_upgrade = stream.read_int()
        combat_upgrade = stream.read_int()
        max_builders = stream.read_int()
        building_properties = {}
        for _ in range(stream.read_int()):
            building_properties_key = BuildingType(stream.read_int())
            building_properties_value = BuildingProperties.read_from(stream)
            building_properties[building_properties_key] = building_properties_value
        specialties_allowed = stream.read_bool()
        if stream.read_bool():
            view_distance = stream.read_int()
        else:
            view_distance = None
        return Game(my_index, current_tick, max_tick_count, players, planets, flying_worker_groups, max_flying_worker_groups, max_travel_distance, logistics_upgrade, production_upgrade, combat_upgrade, max_builders, building_properties, specialties_allowed, view_distance)
    
    def write_to(self, stream: StreamWrapper):
        """Write Game to output stream
        """
        stream.write_int(self.my_index)
        stream.write_int(self.current_tick)
        stream.write_int(self.max_tick_count)
        stream.write_int(len(self.players))
        for element in self.players:
            element.write_to(stream)
        stream.write_int(len(self.planets))
        for element in self.planets:
            element.write_to(stream)
        stream.write_int(len(self.flying_worker_groups))
        for element in self.flying_worker_groups:
            element.write_to(stream)
        stream.write_int(self.max_flying_worker_groups)
        stream.write_int(self.max_travel_distance)
        stream.write_int(self.logistics_upgrade)
        stream.write_int(self.production_upgrade)
        stream.write_int(self.combat_upgrade)
        stream.write_int(self.max_builders)
        stream.write_int(len(self.building_properties))
        for key, value in self.building_properties.items():
            stream.write_int(key)
            value.write_to(stream)
        stream.write_bool(self.specialties_allowed)
        if self.view_distance is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.view_distance)
    
    def __repr__(self):
        return "Game(" + \
            repr(self.my_index) + \
            ", " + \
            repr(self.current_tick) + \
            ", " + \
            repr(self.max_tick_count) + \
            ", " + \
            repr(self.players) + \
            ", " + \
            repr(self.planets) + \
            ", " + \
            repr(self.flying_worker_groups) + \
            ", " + \
            repr(self.max_flying_worker_groups) + \
            ", " + \
            repr(self.max_travel_distance) + \
            ", " + \
            repr(self.logistics_upgrade) + \
            ", " + \
            repr(self.production_upgrade) + \
            ", " + \
            repr(self.combat_upgrade) + \
            ", " + \
            repr(self.max_builders) + \
            ", " + \
            repr(self.building_properties) + \
            ", " + \
            repr(self.specialties_allowed) + \
            ", " + \
            repr(self.view_distance) + \
            ")"