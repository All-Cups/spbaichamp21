from model.building_action import BuildingAction
from model.move_action import MoveAction
from stream_wrapper import StreamWrapper
from typing import List

class Action:
    """TODO - Document"""

    __slots__ = ("moves","buildings",)

    moves: List[MoveAction]
    buildings: List[BuildingAction]

    def __init__(self, moves: List[MoveAction], buildings: List[BuildingAction]):
        self.moves = moves
        """TODO - Document"""
        self.buildings = buildings
        """TODO - Document"""

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
        return Action(moves, buildings)
    
    def write_to(self, stream: StreamWrapper):
        """Write Action to output stream
        """
        stream.write_int(len(self.moves))
        for element in self.moves:
            element.write_to(stream)
        stream.write_int(len(self.buildings))
        for element in self.buildings:
            element.write_to(stream)
    
    def __repr__(self):
        return "Action(" + \
            repr(self.moves) + \
            ", " + \
            repr(self.buildings) + \
            ")"