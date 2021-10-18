from model.building_type import BuildingType
from stream_wrapper import StreamWrapper

class Building:
    """TODO - Document"""

    __slots__ = ("building_type","health","work_done",)

    building_type: BuildingType
    health: int
    work_done: int

    def __init__(self, building_type: BuildingType, health: int, work_done: int):
        self.building_type = building_type
        """TODO - Document"""
        self.health = health
        """TODO - Document"""
        self.work_done = work_done
        """TODO - Document"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "Building":
        """Read Building from input stream
        """
        building_type = BuildingType(stream.read_int())
        health = stream.read_int()
        work_done = stream.read_int()
        return Building(building_type, health, work_done)
    
    def write_to(self, stream: StreamWrapper):
        """Write Building to output stream
        """
        stream.write_int(self.building_type)
        stream.write_int(self.health)
        stream.write_int(self.work_done)
    
    def __repr__(self):
        return "Building(" + \
            repr(self.building_type) + \
            ", " + \
            repr(self.health) + \
            ", " + \
            repr(self.work_done) + \
            ")"