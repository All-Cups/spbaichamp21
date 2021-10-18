from model.building_properties import BuildingProperties
from model.building_type import BuildingType
from model.flying_worker_group import FlyingWorkerGroup
from model.planet import Planet
from model.player import Player
from stream_wrapper import StreamWrapper
from typing import Dict
from typing import List

class Game:
    """TODO - Document"""

    __slots__ = ("my_index","current_tick","max_tick_count","players","planets","flying_worker_groups","max_flying_worker_groups","max_travel_distance","max_builders","building_properties",)

    my_index: int
    current_tick: int
    max_tick_count: int
    players: List[Player]
    planets: List[Planet]
    flying_worker_groups: List[FlyingWorkerGroup]
    max_flying_worker_groups: int
    max_travel_distance: int
    max_builders: int
    building_properties: Dict[BuildingType, BuildingProperties]

    def __init__(self, my_index: int, current_tick: int, max_tick_count: int, players: List[Player], planets: List[Planet], flying_worker_groups: List[FlyingWorkerGroup], max_flying_worker_groups: int, max_travel_distance: int, max_builders: int, building_properties: Dict[BuildingType, BuildingProperties]):
        self.my_index = my_index
        """TODO - Document"""
        self.current_tick = current_tick
        """TODO - Document"""
        self.max_tick_count = max_tick_count
        """TODO - Document"""
        self.players = players
        """TODO - Document"""
        self.planets = planets
        """TODO - Document"""
        self.flying_worker_groups = flying_worker_groups
        """TODO - Document"""
        self.max_flying_worker_groups = max_flying_worker_groups
        """TODO - Document"""
        self.max_travel_distance = max_travel_distance
        """TODO - Document"""
        self.max_builders = max_builders
        """TODO - Document"""
        self.building_properties = building_properties
        """TODO - Document"""

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
        max_builders = stream.read_int()
        building_properties = {}
        for _ in range(stream.read_int()):
            building_properties_key = BuildingType(stream.read_int())
            building_properties_value = BuildingProperties.read_from(stream)
            building_properties[building_properties_key] = building_properties_value
        return Game(my_index, current_tick, max_tick_count, players, planets, flying_worker_groups, max_flying_worker_groups, max_travel_distance, max_builders, building_properties)
    
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
        stream.write_int(self.max_builders)
        stream.write_int(len(self.building_properties))
        for key, value in self.building_properties.items():
            stream.write_int(key)
            value.write_to(stream)
    
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
            repr(self.max_builders) + \
            ", " + \
            repr(self.building_properties) + \
            ")"