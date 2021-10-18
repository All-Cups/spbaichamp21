from model.building import Building
from model.resource import Resource
from model.worker_group import WorkerGroup
from stream_wrapper import StreamWrapper
from typing import Dict
from typing import List
from typing import Optional

class Planet:
    """TODO - Document"""

    __slots__ = ("id","x","y","harvestable_resource","worker_groups","resources","building",)

    id: int
    x: int
    y: int
    harvestable_resource: Optional[Resource]
    worker_groups: List[WorkerGroup]
    resources: Dict[Resource, int]
    building: Optional[Building]

    def __init__(self, id: int, x: int, y: int, harvestable_resource: Optional[Resource], worker_groups: List[WorkerGroup], resources: Dict[Resource, int], building: Optional[Building]):
        self.id = id
        """TODO - Document"""
        self.x = x
        """TODO - Document"""
        self.y = y
        """TODO - Document"""
        self.harvestable_resource = harvestable_resource
        """TODO - Document"""
        self.worker_groups = worker_groups
        """TODO - Document"""
        self.resources = resources
        """TODO - Document"""
        self.building = building
        """TODO - Document"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "Planet":
        """Read Planet from input stream
        """
        id = stream.read_int()
        x = stream.read_int()
        y = stream.read_int()
        if stream.read_bool():
            harvestable_resource = Resource(stream.read_int())
        else:
            harvestable_resource = None
        worker_groups = []
        for _ in range(stream.read_int()):
            worker_groups_element = WorkerGroup.read_from(stream)
            worker_groups.append(worker_groups_element)
        resources = {}
        for _ in range(stream.read_int()):
            resources_key = Resource(stream.read_int())
            resources_value = stream.read_int()
            resources[resources_key] = resources_value
        if stream.read_bool():
            building = Building.read_from(stream)
        else:
            building = None
        return Planet(id, x, y, harvestable_resource, worker_groups, resources, building)
    
    def write_to(self, stream: StreamWrapper):
        """Write Planet to output stream
        """
        stream.write_int(self.id)
        stream.write_int(self.x)
        stream.write_int(self.y)
        if self.harvestable_resource is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.harvestable_resource)
        stream.write_int(len(self.worker_groups))
        for element in self.worker_groups:
            element.write_to(stream)
        stream.write_int(len(self.resources))
        for key, value in self.resources.items():
            stream.write_int(key)
            stream.write_int(value)
        if self.building is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.building.write_to(stream)
    
    def __repr__(self):
        return "Planet(" + \
            repr(self.id) + \
            ", " + \
            repr(self.x) + \
            ", " + \
            repr(self.y) + \
            ", " + \
            repr(self.harvestable_resource) + \
            ", " + \
            repr(self.worker_groups) + \
            ", " + \
            repr(self.resources) + \
            ", " + \
            repr(self.building) + \
            ")"