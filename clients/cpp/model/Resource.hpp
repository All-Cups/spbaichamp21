#ifndef __MODEL_RESOURCE_HPP__
#define __MODEL_RESOURCE_HPP__

#include "Stream.hpp"

namespace model {

// Resource type
enum class Resource {
    // Stone
    STONE = 0,
    // Ore
    ORE = 1,
    // Sand
    SAND = 2,
    // Organics
    ORGANICS = 3,
    // Metal
    METAL = 4,
    // Silicon
    SILICON = 5,
    // Plastic
    PLASTIC = 6,
    // Chip
    CHIP = 7,
    // Accumulator
    ACCUMULATOR = 8
};

// Read Resource from input stream
Resource readResource(InputStream& stream);

// Get string representation of Resource
std::string resourceToString(Resource value);

}

#endif