#ifndef __MODEL_RESOURCE_HPP__
#define __MODEL_RESOURCE_HPP__

#include "Stream.hpp"

namespace model {

// TODO - Document
enum class Resource {
    // TODO - Document
    STONE = 0,
    // TODO - Document
    ORE = 1,
    // TODO - Document
    SAND = 2,
    // TODO - Document
    ORGANICS = 3,
    // TODO - Document
    METAL = 4,
    // TODO - Document
    SILICON = 5,
    // TODO - Document
    PLASTIC = 6,
    // TODO - Document
    CHIP = 7,
    // TODO - Document
    ACCUMULATOR = 8
};

// Read Resource from input stream
Resource readResource(InputStream& stream);

// Get string representation of Resource
std::string resourceToString(Resource value);

}

#endif