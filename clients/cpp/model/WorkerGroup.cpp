#include "WorkerGroup.hpp"

namespace model {

WorkerGroup::WorkerGroup(int playerIndex, int number) : playerIndex(playerIndex), number(number) { }

// Read WorkerGroup from input stream
WorkerGroup WorkerGroup::readFrom(InputStream& stream) {
    int playerIndex = stream.readInt();
    int number = stream.readInt();
    return WorkerGroup(playerIndex, number);
}

// Write WorkerGroup to output stream
void WorkerGroup::writeTo(OutputStream& stream) const {
    stream.write(playerIndex);
    stream.write(number);
}

// Get string representation of WorkerGroup
std::string WorkerGroup::toString() const {
    std::stringstream ss;
    ss << "WorkerGroup { ";
    ss << "playerIndex: ";
    ss << playerIndex;
    ss << ", ";
    ss << "number: ";
    ss << number;
    ss << " }";
    return ss.str();
}

bool WorkerGroup::operator ==(const WorkerGroup& other) const {
    return playerIndex == other.playerIndex && number == other.number;
}

}

size_t std::hash<model::WorkerGroup>::operator ()(const model::WorkerGroup& value) const {
    size_t result = 0;
    result ^= std::hash<int>{}(value.playerIndex) + 0x9e3779b9 + (result << 6) + (result >> 2);
    result ^= std::hash<int>{}(value.number) + 0x9e3779b9 + (result << 6) + (result >> 2);
    return result;
}