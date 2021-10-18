#ifndef __MODEL_BUILDING_ACTION_HPP__
#define __MODEL_BUILDING_ACTION_HPP__

#include "Stream.hpp"
#include "model/BuildingType.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>

namespace model {

// Building order
class BuildingAction {
public:
    // Id of the planet where the action needs to be performed
    int planet;
    // Type of a building to build. If absent, current building will be destroyed
    std::optional<model::BuildingType> buildingType;

    BuildingAction(int planet, std::optional<model::BuildingType> buildingType);

    // Read BuildingAction from input stream
    static BuildingAction readFrom(InputStream& stream);

    // Write BuildingAction to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of BuildingAction
    std::string toString() const;
};

}

#endif