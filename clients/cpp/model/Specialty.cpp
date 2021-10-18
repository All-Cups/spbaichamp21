#include "Specialty.hpp"
#include <stdexcept>

namespace model {

// Read Specialty from input stream
Specialty readSpecialty(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return Specialty::LOGISTICS;
    case 1:
        return Specialty::PRODUCTION;
    case 2:
        return Specialty::COMBAT;
    default:
        throw std::runtime_error("Unexpected tag value");
    }
}

// Get string representation of Specialty
std::string specialtyToString(Specialty value) {
    switch (value) {
    case Specialty::LOGISTICS:
        return "LOGISTICS";
    case Specialty::PRODUCTION:
        return "PRODUCTION";
    case Specialty::COMBAT:
        return "COMBAT";
    default:
        throw std::runtime_error("Impossible happened");
    }
}

}