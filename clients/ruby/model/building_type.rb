module Model

# TODO - Document
module BuildingType
    # TODO - Document
    QUARRY = 0
    # TODO - Document
    MINES = 1
    # TODO - Document
    CAREER = 2
    # TODO - Document
    FARM = 3
    # TODO - Document
    FOUNDRY = 4
    # TODO - Document
    FURNACE = 5
    # TODO - Document
    BIOREACTOR = 6
    # TODO - Document
    CHIP_FACTORY = 7
    # TODO - Document
    ACCUMULATOR_FACTORY = 8
    # TODO - Document
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