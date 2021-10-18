from model.resource import Resource
from stream_wrapper import StreamWrapper
from typing import Optional

class FlyingWorkerGroup:
    """A flying worker group"""

    __slots__ = ("player_index","number","departure_tick","departure_planet","next_planet_arrival_tick","next_planet","target_planet","resource",)

    player_index: int
    number: int
    departure_tick: int
    departure_planet: int
    next_planet_arrival_tick: int
    next_planet: int
    target_planet: int
    resource: Optional[Resource]

    def __init__(self, player_index: int, number: int, departure_tick: int, departure_planet: int, next_planet_arrival_tick: int, next_planet: int, target_planet: int, resource: Optional[Resource]):
        self.player_index = player_index
        """Index of player controlling workers"""
        self.number = number
        """Number of workers in the group"""
        self.departure_tick = departure_tick
        """Tick when workers left previous planet on their path"""
        self.departure_planet = departure_planet
        """Id of the previous planet on the path"""
        self.next_planet_arrival_tick = next_planet_arrival_tick
        """Tick when workers will arrive to the next planet in their path"""
        self.next_planet = next_planet
        """Id of the next planet in the path"""
        self.target_planet = target_planet
        """Id of the target planet"""
        self.resource = resource
        """Resource that workers are carrying"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "FlyingWorkerGroup":
        """Read FlyingWorkerGroup from input stream
        """
        player_index = stream.read_int()
        number = stream.read_int()
        departure_tick = stream.read_int()
        departure_planet = stream.read_int()
        next_planet_arrival_tick = stream.read_int()
        next_planet = stream.read_int()
        target_planet = stream.read_int()
        if stream.read_bool():
            resource = Resource(stream.read_int())
        else:
            resource = None
        return FlyingWorkerGroup(player_index, number, departure_tick, departure_planet, next_planet_arrival_tick, next_planet, target_planet, resource)
    
    def write_to(self, stream: StreamWrapper):
        """Write FlyingWorkerGroup to output stream
        """
        stream.write_int(self.player_index)
        stream.write_int(self.number)
        stream.write_int(self.departure_tick)
        stream.write_int(self.departure_planet)
        stream.write_int(self.next_planet_arrival_tick)
        stream.write_int(self.next_planet)
        stream.write_int(self.target_planet)
        if self.resource is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.resource)
    
    def __repr__(self):
        return "FlyingWorkerGroup(" + \
            repr(self.player_index) + \
            ", " + \
            repr(self.number) + \
            ", " + \
            repr(self.departure_tick) + \
            ", " + \
            repr(self.departure_planet) + \
            ", " + \
            repr(self.next_planet_arrival_tick) + \
            ", " + \
            repr(self.next_planet) + \
            ", " + \
            repr(self.target_planet) + \
            ", " + \
            repr(self.resource) + \
            ")"