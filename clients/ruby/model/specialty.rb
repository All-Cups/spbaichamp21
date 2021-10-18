module Model

# Player's specialty
module Specialty
    # Logistics. Increased travel distance
    LOGISTICS = 0
    # Production. Increased work speed
    PRODUCTION = 1
    # Combat. Increased damage
    COMBAT = 2

    # Read Specialty from input stream
    def self.read_from(stream)
        result = stream.read_int()
        if result < 0 || result >= 3
            raise "Unexpected tag value"
        end
        result
    end

    def self.to_s(value)
        if value == LOGISTICS
            return "LOGISTICS"
        end
        if value == PRODUCTION
            return "PRODUCTION"
        end
        if value == COMBAT
            return "COMBAT"
        end
        raise "Impossible happened"
    end

    def self.to_str(value)
        self.to_s(value)
    end
end

end