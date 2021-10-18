from model.resource import Resource
from stream_wrapper import StreamWrapper
from typing import Optional

class MoveAction:
    """Movement order"""

    __slots__ = ("start_planet","target_planet","worker_number","take_resource",)

    start_planet: int
    target_planet: int
    worker_number: int
    take_resource: Optional[Resource]

    def __init__(self, start_planet: int, target_planet: int, worker_number: int, take_resource: Optional[Resource]):
        self.start_planet = start_planet
        """Id of the planet where workers need to be sent from"""
        self.target_planet = target_planet
        """Id of the target planet"""
        self.worker_number = worker_number
        """Number of workers to send"""
        self.take_resource = take_resource
        """Resource workers should carry"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "MoveAction":
        """Read MoveAction from input stream
        """
        start_planet = stream.read_int()
        target_planet = stream.read_int()
        worker_number = stream.read_int()
        if stream.read_bool():
            take_resource = Resource(stream.read_int())
        else:
            take_resource = None
        return MoveAction(start_planet, target_planet, worker_number, take_resource)
    
    def write_to(self, stream: StreamWrapper):
        """Write MoveAction to output stream
        """
        stream.write_int(self.start_planet)
        stream.write_int(self.target_planet)
        stream.write_int(self.worker_number)
        if self.take_resource is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.take_resource)
    
    def __repr__(self):
        return "MoveAction(" + \
            repr(self.start_planet) + \
            ", " + \
            repr(self.target_planet) + \
            ", " + \
            repr(self.worker_number) + \
            ", " + \
            repr(self.take_resource) + \
            ")"