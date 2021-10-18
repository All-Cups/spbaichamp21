module Model

# TODO - Document
class DebugData

    def initialize()
    end

    # Read DebugData from input stream
    def self.read_from(stream)
        DebugData.new()
    end

    # Write DebugData to output stream
    def write_to(stream)
    end

    def to_s
        string_result = "DebugData { "
        string_result += " }"
        string_result
    end

    def to_str
        to_s
    end
end

end