#include "Player.hpp"

namespace model {

Player::Player(int teamIndex, int score, std::optional<model::Specialty> specialty) : teamIndex(teamIndex), score(score), specialty(specialty) { }

// Read Player from input stream
Player Player::readFrom(InputStream& stream) {
    int teamIndex = stream.readInt();
    int score = stream.readInt();
    std::optional<model::Specialty> specialty = std::optional<model::Specialty>();
    if (stream.readBool()) {
        model::Specialty specialtyValue = readSpecialty(stream);
        specialty.emplace(specialtyValue);
    }
    return Player(teamIndex, score, specialty);
}

// Write Player to output stream
void Player::writeTo(OutputStream& stream) const {
    stream.write(teamIndex);
    stream.write(score);
    if (specialty) {
        stream.write(true);
        const model::Specialty& specialtyValue = *specialty;
        stream.write((int)(specialtyValue));
    } else {
        stream.write(false);
    }
}

// Get string representation of Player
std::string Player::toString() const {
    std::stringstream ss;
    ss << "Player { ";
    ss << "teamIndex: ";
    ss << teamIndex;
    ss << ", ";
    ss << "score: ";
    ss << score;
    ss << ", ";
    ss << "specialty: ";
    if (specialty) {
        const model::Specialty& specialtyValue = *specialty;
        ss << specialtyToString(specialtyValue);
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}