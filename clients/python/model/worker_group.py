from stream_wrapper import StreamWrapper

class WorkerGroup:
    """Group of workers on a planet"""

    __slots__ = ("player_index","number",)

    player_index: int
    number: int

    def __init__(self, player_index: int, number: int):
        self.player_index = player_index
        """Index of player controlling the workers"""
        self.number = number
        """Number of workers in the group"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "WorkerGroup":
        """Read WorkerGroup from input stream
        """
        player_index = stream.read_int()
        number = stream.read_int()
        return WorkerGroup(player_index, number)
    
    def write_to(self, stream: StreamWrapper):
        """Write WorkerGroup to output stream
        """
        stream.write_int(self.player_index)
        stream.write_int(self.number)
    
    def __repr__(self):
        return "WorkerGroup(" + \
            repr(self.player_index) + \
            ", " + \
            repr(self.number) + \
            ")"