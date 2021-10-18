#include "DebugData.hpp"

namespace model {

DebugData::DebugData() { }

// Read DebugData from input stream
DebugData DebugData::readFrom(InputStream& stream) {
    return DebugData();
}

// Write DebugData to output stream
void DebugData::writeTo(OutputStream& stream) const {
}

// Get string representation of DebugData
std::string DebugData::toString() const {
    std::stringstream ss;
    ss << "DebugData { ";
    ss << " }";
    return ss.str();
}

bool DebugData::operator ==(const DebugData& other) const {
    return true;
}

}

size_t std::hash<model::DebugData>::operator ()(const model::DebugData& value) const {
    size_t result = 0;
    return result;
}