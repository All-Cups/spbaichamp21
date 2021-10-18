#ifndef __MODEL_DEBUG_DATA_HPP__
#define __MODEL_DEBUG_DATA_HPP__

#include "Stream.hpp"
#include <sstream>
#include <string>

namespace model {

// TODO - Document
class DebugData {
public:

    DebugData();

    // Read DebugData from input stream
    static DebugData readFrom(InputStream& stream);

    // Write DebugData to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of DebugData
    std::string toString() const;

    bool operator ==(const DebugData& other) const;
};

}

namespace std {
    template<>
    struct hash<model::DebugData> {
        size_t operator ()(const model::DebugData& value) const;
    };
}

#endif