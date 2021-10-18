#ifndef __MODEL_MOVE_ACTION_HPP__
#define __MODEL_MOVE_ACTION_HPP__

#include "Stream.hpp"
#include "model/Resource.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>

namespace model {

// TODO - Document
class MoveAction {
public:
    // TODO - Document
    int startPlanet;
    // TODO - Document
    int targetPlanet;
    // TODO - Document
    int workerNumber;
    // TODO - Document
    std::optional<model::Resource> takeResource;

    MoveAction(int startPlanet, int targetPlanet, int workerNumber, std::optional<model::Resource> takeResource);

    // Read MoveAction from input stream
    static MoveAction readFrom(InputStream& stream);

    // Write MoveAction to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of MoveAction
    std::string toString() const;
};

}

#endif