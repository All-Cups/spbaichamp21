from stream_wrapper import StreamWrapper

class DebugData:
    """TODO - Document"""

    __slots__ = ()


    def __init__(self):
        pass

    @staticmethod
    def read_from(stream: StreamWrapper) -> "DebugData":
        """Read DebugData from input stream
        """
        return DebugData()
    
    def write_to(self, stream: StreamWrapper):
        """Write DebugData to output stream
        """
        pass
    
    def __repr__(self):
        return "DebugData(" + \
            ")"