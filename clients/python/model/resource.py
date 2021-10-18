from enum import IntEnum

class Resource(IntEnum):
    """Resource type"""

    STONE = 0
    """Stone"""
    ORE = 1
    """Ore"""
    SAND = 2
    """Sand"""
    ORGANICS = 3
    """Organics"""
    METAL = 4
    """Metal"""
    SILICON = 5
    """Silicon"""
    PLASTIC = 6
    """Plastic"""
    CHIP = 7
    """Chip"""
    ACCUMULATOR = 8
    """Accumulator"""

    def __repr__(self):
        return str(self)