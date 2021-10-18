#ifndef __MODEL_WORKER_GROUP_HPP__
#define __MODEL_WORKER_GROUP_HPP__

#include "Stream.hpp"
#include <sstream>
#include <string>

namespace model {

// Group of workers on a planet
class WorkerGroup {
public:
    // Index of player controlling the workers
    int playerIndex;
    // Number of workers in the group
    int number;

    WorkerGroup(int playerIndex, int number);

    // Read WorkerGroup from input stream
    static WorkerGroup readFrom(InputStream& stream);

    // Write WorkerGroup to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of WorkerGroup
    std::string toString() const;

    bool operator ==(const WorkerGroup& other) const;
};

}

namespace std {
    template<>
    struct hash<model::WorkerGroup> {
        size_t operator ()(const model::WorkerGroup& value) const;
    };
}

#endif