from model.building_action import BuildingAction
from model.move_action import MoveAction
from model.specialty import Specialty
from stream_wrapper import StreamWrapper
from typing import List
from typing import Optional

class Action:
    """Player's actions"""

    __slots__ = ("moves","buildings","choose_specialty",)

    moves: List[MoveAction]
    buildings: List[BuildingAction]
    choose_specialty: Optional[Specialty]

    def __init__(self, moves: List[MoveAction], buildings: List[BuildingAction], choose_specialty: Optional[Specialty]):
        self.moves = moves
        """List of movement orders"""
        self.buildings = buildings
        """List of building orders"""
        self.choose_specialty = choose_specialty
        """Choosing specialty"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "Action":
        """Read Action from input stream
        """
        moves = []
        for _ in range(stream.read_int()):
            moves_element = MoveAction.read_from(stream)
            moves.append(moves_element)
        buildings = []
        for _ in range(stream.read_int()):
            buildings_element = BuildingAction.read_from(stream)
            buildings.append(buildings_element)
        if stream.read_bool():
            choose_specialty = Specialty(stream.read_int())
        else:
            choose_specialty = None
        return Action(moves, buildings, choose_specialty)
    
    def write_to(self, stream: StreamWrapper):
        """Write Action to output stream
        """
        stream.write_int(len(self.moves))
        for element in self.moves:
            element.write_to(stream)
        stream.write_int(len(self.buildings))
        for element in self.buildings:
            element.write_to(stream)
        if self.choose_specialty is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.choose_specialty)
    
    def __repr__(self):
        return "Action(" + \
            repr(self.moves) + \
            ", " + \
            repr(self.buildings) + \
            ", " + \
            repr(self.choose_specialty) + \
            ")"