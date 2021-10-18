from model.specialty import Specialty
from stream_wrapper import StreamWrapper
from typing import Optional

class Player:
    """Player (game participant)"""

    __slots__ = ("team_index","score","specialty",)

    team_index: int
    score: int
    specialty: Optional[Specialty]

    def __init__(self, team_index: int, score: int, specialty: Optional[Specialty]):
        self.team_index = team_index
        """Team index"""
        self.score = score
        """Current score points"""
        self.specialty = specialty
        """Player's specialty"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "Player":
        """Read Player from input stream
        """
        team_index = stream.read_int()
        score = stream.read_int()
        if stream.read_bool():
            specialty = Specialty(stream.read_int())
        else:
            specialty = None
        return Player(team_index, score, specialty)
    
    def write_to(self, stream: StreamWrapper):
        """Write Player to output stream
        """
        stream.write_int(self.team_index)
        stream.write_int(self.score)
        if self.specialty is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.specialty)
    
    def __repr__(self):
        return "Player(" + \
            repr(self.team_index) + \
            ", " + \
            repr(self.score) + \
            ", " + \
            repr(self.specialty) + \
            ")"