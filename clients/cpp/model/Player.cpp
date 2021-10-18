#include "Player.hpp"

namespace model {

Player::Player(int score) : score(score) { }

// Read Player from input stream
Player Player::readFrom(InputStream& stream) {
    int score = stream.readInt();
    return Player(score);
}

// Write Player to output stream
void Player::writeTo(OutputStream& stream) const {
    stream.write(score);
}

// Get string representation of Player
std::string Player::toString() const {
    std::stringstream ss;
    ss << "Player { ";
    ss << "score: ";
    ss << score;
    ss << " }";
    return ss.str();
}

bool Player::operator ==(const Player& other) const {
    return score == other.score;
}

}

size_t std::hash<model::Player>::operator ()(const model::Player& value) const {
    size_t result = 0;
    result ^= std::hash<int>{}(value.score) + 0x9e3779b9 + (result << 6) + (result >> 2);
    return result;
}