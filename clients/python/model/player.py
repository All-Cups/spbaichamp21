from stream_wrapper import StreamWrapper

class Player:
    """TODO - Document"""

    __slots__ = ("score",)

    score: int

    def __init__(self, score: int):
        self.score = score
        """TODO - Document"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "Player":
        """Read Player from input stream
        """
        score = stream.read_int()
        return Player(score)
    
    def write_to(self, stream: StreamWrapper):
        """Write Player to output stream
        """
        stream.write_int(self.score)
    
    def __repr__(self):
        return "Player(" + \
            repr(self.score) + \
            ")"