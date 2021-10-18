#ifndef __MODEL_SPECIALTY_HPP__
#define __MODEL_SPECIALTY_HPP__

#include "Stream.hpp"

namespace model {

// Player's specialty
enum class Specialty {
    // Logistics. Increased travel distance
    LOGISTICS = 0,
    // Production. Increased work speed
    PRODUCTION = 1,
    // Combat. Increased damage
    COMBAT = 2
};

// Read Specialty from input stream
Specialty readSpecialty(InputStream& stream);

// Get string representation of Specialty
std::string specialtyToString(Specialty value);

}

#endif