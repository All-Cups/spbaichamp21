from enum import IntEnum

class Resource(IntEnum):
    """TODO - Document"""

    STONE = 0
    """TODO - Document"""
    ORE = 1
    """TODO - Document"""
    SAND = 2
    """TODO - Document"""
    ORGANICS = 3
    """TODO - Document"""
    METAL = 4
    """TODO - Document"""
    SILICON = 5
    """TODO - Document"""
    PLASTIC = 6
    """TODO - Document"""
    CHIP = 7
    """TODO - Document"""
    ACCUMULATOR = 8
    """TODO - Document"""

    def __repr__(self):
        return str(self)