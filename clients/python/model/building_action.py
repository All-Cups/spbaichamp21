from model.building_type import BuildingType
from stream_wrapper import StreamWrapper
from typing import Optional

class BuildingAction:
    """TODO - Document"""

    __slots__ = ("planet","building_type",)

    planet: int
    building_type: Optional[BuildingType]

    def __init__(self, planet: int, building_type: Optional[BuildingType]):
        self.planet = planet
        """TODO - Document"""
        self.building_type = building_type
        """TODO - Document"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "BuildingAction":
        """Read BuildingAction from input stream
        """
        planet = stream.read_int()
        if stream.read_bool():
            building_type = BuildingType(stream.read_int())
        else:
            building_type = None
        return BuildingAction(planet, building_type)
    
    def write_to(self, stream: StreamWrapper):
        """Write BuildingAction to output stream
        """
        stream.write_int(self.planet)
        if self.building_type is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.building_type)
    
    def __repr__(self):
        return "BuildingAction(" + \
            repr(self.planet) + \
            ", " + \
            repr(self.building_type) + \
            ")"