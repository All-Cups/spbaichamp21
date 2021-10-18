from model.building_type import BuildingType
from stream_wrapper import StreamWrapper
from typing import Optional

class BuildingAction:
    """Building order"""

    __slots__ = ("planet","building_type",)

    planet: int
    building_type: Optional[BuildingType]

    def __init__(self, planet: int, building_type: Optional[BuildingType]):
        self.planet = planet
        """Id of the planet where the action needs to be performed"""
        self.building_type = building_type
        """Type of a building to build. If absent, current building will be destroyed"""

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