from enum import IntEnum

class BuildingType(IntEnum):
    """TODO - Document"""

    QUARRY = 0
    """TODO - Document"""
    MINES = 1
    """TODO - Document"""
    CAREER = 2
    """TODO - Document"""
    FARM = 3
    """TODO - Document"""
    FOUNDRY = 4
    """TODO - Document"""
    FURNACE = 5
    """TODO - Document"""
    BIOREACTOR = 6
    """TODO - Document"""
    CHIP_FACTORY = 7
    """TODO - Document"""
    ACCUMULATOR_FACTORY = 8
    """TODO - Document"""
    REPLICATOR = 9
    """TODO - Document"""

    def __repr__(self):
        return str(self)