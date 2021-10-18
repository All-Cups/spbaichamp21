module Model

# Building type
module BuildingType
    # Quarry harvests stone
    QUARRY = 0
    # Mines harvests ore
    MINES = 1
    # Career harvest sand
    CAREER = 2
    # Farm harvests organics
    FARM = 3
    # Foundry produces metal
    FOUNDRY = 4
    # Furnace produces silicon
    FURNACE = 5
    # Bioreactor produces plastic
    BIOREACTOR = 6
    # Chip factory produces chips
    CHIP_FACTORY = 7
    # Accumulator factory produces accumulators
    ACCUMULATOR_FACTORY = 8
    # Replicator produces new workers
    REPLICATOR = 9

    # Read BuildingType from input stream
    def self.read_from(stream)
        result = stream.read_int()
        if result < 0 || result >= 10
            raise "Unexpected tag value"
        end
        result
    end

    def self.to_s(value)
        if value == QUARRY
            return "QUARRY"
        end
        if value == MINES
            return "MINES"
        end
        if value == CAREER
            return "CAREER"
        end
        if value == FARM
            return "FARM"
        end
        if value == FOUNDRY
            return "FOUNDRY"
        end
        if value == FURNACE
            return "FURNACE"
        end
        if value == BIOREACTOR
            return "BIOREACTOR"
        end
        if value == CHIP_FACTORY
            return "CHIP_FACTORY"
        end
        if value == ACCUMULATOR_FACTORY
            return "ACCUMULATOR_FACTORY"
        end
        if value == REPLICATOR
            return "REPLICATOR"
        end
        raise "Impossible happened"
    end

    def self.to_str(value)
        self.to_s(value)
    end
end

end