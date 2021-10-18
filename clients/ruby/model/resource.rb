module Model

# Resource type
module Resource
    # Stone
    STONE = 0
    # Ore
    ORE = 1
    # Sand
    SAND = 2
    # Organics
    ORGANICS = 3
    # Metal
    METAL = 4
    # Silicon
    SILICON = 5
    # Plastic
    PLASTIC = 6
    # Chip
    CHIP = 7
    # Accumulator
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