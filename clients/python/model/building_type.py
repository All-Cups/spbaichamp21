from enum import IntEnum

class BuildingType(IntEnum):
    """Building type"""

    QUARRY = 0
    """Quarry harvests stone"""
    MINES = 1
    """Mines harvests ore"""
    CAREER = 2
    """Career harvest sand"""
    FARM = 3
    """Farm harvests organics"""
    FOUNDRY = 4
    """Foundry produces metal"""
    FURNACE = 5
    """Furnace produces silicon"""
    BIOREACTOR = 6
    """Bioreactor produces plastic"""
    CHIP_FACTORY = 7
    """Chip factory produces chips"""
    ACCUMULATOR_FACTORY = 8
    """Accumulator factory produces accumulators"""
    REPLICATOR = 9
    """Replicator produces new workers"""
    REPLICATOR2 = 10
    """Second level replicator"""
    REPLICATOR3 = 11
    """Third level replicator"""

    def __repr__(self):
        return str(self)