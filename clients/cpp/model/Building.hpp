#ifndef __MODEL_BUILDING_HPP__
#define __MODEL_BUILDING_HPP__

#include "Stream.hpp"
#include "model/BuildingType.hpp"
#include <sstream>
#include <stdexcept>
#include <string>

namespace model {

// A building
class Building {
public:
    // Building's type
    model::BuildingType buildingType;
    // Current health
    int health;
    // Amount of work done for current task
    int workDone;

    Building(model::BuildingType buildingType, int health, int workDone);

    // Read Building from input stream
    static Building readFrom(InputStream& stream);

    // Write Building to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of Building
    std::string toString() const;

    bool operator ==(const Building& other) const;
};

}

namespace std {
    template<>
    struct hash<model::Building> {
        size_t operator ()(const model::Building& value) const;
    };
}

#endif