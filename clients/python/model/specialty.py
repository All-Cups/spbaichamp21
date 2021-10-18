from enum import IntEnum

class Specialty(IntEnum):
    """Player's specialty"""

    LOGISTICS = 0
    """Logistics. Increased travel distance"""
    PRODUCTION = 1
    """Production. Increased work speed"""
    COMBAT = 2
    """Combat. Increased damage"""

    def __repr__(self):
        return str(self)