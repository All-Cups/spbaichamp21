#include "Resource.hpp"
#include <stdexcept>

namespace model {

// Read Resource from input stream
Resource readResource(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return Resource::STONE;
    case 1:
        return Resource::ORE;
    case 2:
        return Resource::SAND;
    case 3:
        return Resource::ORGANICS;
    case 4:
        return Resource::METAL;
    case 5:
        return Resource::SILICON;
    case 6:
        return Resource::PLASTIC;
    case 7:
        return Resource::CHIP;
    case 8:
        return Resource::ACCUMULATOR;
    default:
        throw std::runtime_error("Unexpected tag value");
    }
}

// Get string representation of Resource
std::string resourceToString(Resource value) {
    switch (value) {
    case Resource::STONE:
        return "STONE";
    case Resource::ORE:
        return "ORE";
    case Resource::SAND:
        return "SAND";
    case Resource::ORGANICS:
        return "ORGANICS";
    case Resource::METAL:
        return "METAL";
    case Resource::SILICON:
        return "SILICON";
    case Resource::PLASTIC:
        return "PLASTIC";
    case Resource::CHIP:
        return "CHIP";
    case Resource::ACCUMULATOR:
        return "ACCUMULATOR";
    default:
        throw std::runtime_error("Impossible happened");
    }
}

}