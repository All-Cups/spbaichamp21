from model.resource import Resource
from stream_wrapper import StreamWrapper
from typing import Dict
from typing import Optional

class BuildingProperties:
    """TODO - Document"""

    __slots__ = ("build_resources","max_health","max_workers","work_resources","produce_worker","produce_resource","produce_amount","produce_score","harvest","work_amount",)

    build_resources: Dict[Resource, int]
    max_health: int
    max_workers: int
    work_resources: Dict[Resource, int]
    produce_worker: bool
    produce_resource: Optional[Resource]
    produce_amount: int
    produce_score: int
    harvest: bool
    work_amount: int

    def __init__(self, build_resources: Dict[Resource, int], max_health: int, max_workers: int, work_resources: Dict[Resource, int], produce_worker: bool, produce_resource: Optional[Resource], produce_amount: int, produce_score: int, harvest: bool, work_amount: int):
        self.build_resources = build_resources
        """TODO - Document"""
        self.max_health = max_health
        """TODO - Document"""
        self.max_workers = max_workers
        """TODO - Document"""
        self.work_resources = work_resources
        """TODO - Document"""
        self.produce_worker = produce_worker
        """TODO - Document"""
        self.produce_resource = produce_resource
        """TODO - Document"""
        self.produce_amount = produce_amount
        """TODO - Document"""
        self.produce_score = produce_score
        """TODO - Document"""
        self.harvest = harvest
        """TODO - Document"""
        self.work_amount = work_amount
        """TODO - Document"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "BuildingProperties":
        """Read BuildingProperties from input stream
        """
        build_resources = {}
        for _ in range(stream.read_int()):
            build_resources_key = Resource(stream.read_int())
            build_resources_value = stream.read_int()
            build_resources[build_resources_key] = build_resources_value
        max_health = stream.read_int()
        max_workers = stream.read_int()
        work_resources = {}
        for _ in range(stream.read_int()):
            work_resources_key = Resource(stream.read_int())
            work_resources_value = stream.read_int()
            work_resources[work_resources_key] = work_resources_value
        produce_worker = stream.read_bool()
        if stream.read_bool():
            produce_resource = Resource(stream.read_int())
        else:
            produce_resource = None
        produce_amount = stream.read_int()
        produce_score = stream.read_int()
        harvest = stream.read_bool()
        work_amount = stream.read_int()
        return BuildingProperties(build_resources, max_health, max_workers, work_resources, produce_worker, produce_resource, produce_amount, produce_score, harvest, work_amount)
    
    def write_to(self, stream: StreamWrapper):
        """Write BuildingProperties to output stream
        """
        stream.write_int(len(self.build_resources))
        for key, value in self.build_resources.items():
            stream.write_int(key)
            stream.write_int(value)
        stream.write_int(self.max_health)
        stream.write_int(self.max_workers)
        stream.write_int(len(self.work_resources))
        for key, value in self.work_resources.items():
            stream.write_int(key)
            stream.write_int(value)
        stream.write_bool(self.produce_worker)
        if self.produce_resource is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.produce_resource)
        stream.write_int(self.produce_amount)
        stream.write_int(self.produce_score)
        stream.write_bool(self.harvest)
        stream.write_int(self.work_amount)
    
    def __repr__(self):
        return "BuildingProperties(" + \
            repr(self.build_resources) + \
            ", " + \
            repr(self.max_health) + \
            ", " + \
            repr(self.max_workers) + \
            ", " + \
            repr(self.work_resources) + \
            ", " + \
            repr(self.produce_worker) + \
            ", " + \
            repr(self.produce_resource) + \
            ", " + \
            repr(self.produce_amount) + \
            ", " + \
            repr(self.produce_score) + \
            ", " + \
            repr(self.harvest) + \
            ", " + \
            repr(self.work_amount) + \
            ")"