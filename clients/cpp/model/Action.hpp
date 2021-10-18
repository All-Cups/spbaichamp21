#ifndef __MODEL_ACTION_HPP__
#define __MODEL_ACTION_HPP__

#include "Stream.hpp"
#include "model/BuildingAction.hpp"
#include "model/BuildingType.hpp"
#include "model/MoveAction.hpp"
#include "model/Resource.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

namespace model {

// TODO - Document
class Action {
public:
    // TODO - Document
    std::vector<model::MoveAction> moves;
    // TODO - Document
    std::vector<model::BuildingAction> buildings;

    Action(std::vector<model::MoveAction> moves, std::vector<model::BuildingAction> buildings);

    // Read Action from input stream
    static Action readFrom(InputStream& stream);

    // Write Action to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of Action
    std::string toString() const;
};

}

#endif