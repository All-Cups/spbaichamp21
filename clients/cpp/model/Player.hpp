#ifndef __MODEL_PLAYER_HPP__
#define __MODEL_PLAYER_HPP__

#include "Stream.hpp"
#include "model/Specialty.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>

namespace model {

// Player (game participant)
class Player {
public:
    // Team index
    int teamIndex;
    // Current score points
    int score;
    // Player's specialty
    std::optional<model::Specialty> specialty;

    Player(int teamIndex, int score, std::optional<model::Specialty> specialty);

    // Read Player from input stream
    static Player readFrom(InputStream& stream);

    // Write Player to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of Player
    std::string toString() const;
};

}

#endif