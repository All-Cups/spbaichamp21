module Model

# TODO - Document
module Resource
    # TODO - Document
    STONE = 0
    # TODO - Document
    ORE = 1
    # TODO - Document
    SAND = 2
    # TODO - Document
    ORGANICS = 3
    # TODO - Document
    METAL = 4
    # TODO - Document
    SILICON = 5
    # TODO - Document
    PLASTIC = 6
    # TODO - Document
    CHIP = 7
    # TODO - Document
    ACCUMULATOR = 8

    # Read Resource from input stream
    def self.read_from(stream)
        result = stream.read_int()
        if result < 0 || result >= 9
            raise "Unexpected tag value"
        end
        result
    end

    def self.to_s(value)
        if value == STONE
            return "STONE"
        end
        if value == ORE
            return "ORE"
        end
        if value == SAND
            return "SAND"
        end
        if value == ORGANICS
            return "ORGANICS"
        end
        if value == METAL
            return "METAL"
        end
        if value == SILICON
            return "SILICON"
        end
        if value == PLASTIC
            return "PLASTIC"
        end
        if value == CHIP
            return "CHIP"
        end
        if value == ACCUMULATOR
            return "ACCUMULATOR"
        end
        raise "Impossible happened"
    end

    def self.to_str(value)
        self.to_s(value)
    end
end

end