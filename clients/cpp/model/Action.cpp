#include "Action.hpp"

namespace model {

Action::Action(std::vector<model::MoveAction> moves, std::vector<model::BuildingAction> buildings, std::optional<model::Specialty> chooseSpecialty) : moves(moves), buildings(buildings), chooseSpecialty(chooseSpecialty) { }

// Read Action from input stream
Action Action::readFrom(InputStream& stream) {
    std::vector<model::MoveAction> moves = std::vector<model::MoveAction>();
    size_t movesSize = stream.readInt();
    moves.reserve(movesSize);
    for (size_t movesIndex = 0; movesIndex < movesSize; movesIndex++) {
        model::MoveAction movesElement = model::MoveAction::readFrom(stream);
        moves.emplace_back(movesElement);
    }
    std::vector<model::BuildingAction> buildings = std::vector<model::BuildingAction>();
    size_t buildingsSize = stream.readInt();
    buildings.reserve(buildingsSize);
    for (size_t buildingsIndex = 0; buildingsIndex < buildingsSize; buildingsIndex++) {
        model::BuildingAction buildingsElement = model::BuildingAction::readFrom(stream);
        buildings.emplace_back(buildingsElement);
    }
    std::optional<model::Specialty> chooseSpecialty = std::optional<model::Specialty>();
    if (stream.readBool()) {
        model::Specialty chooseSpecialtyValue = readSpecialty(stream);
        chooseSpecialty.emplace(chooseSpecialtyValue);
    }
    return Action(moves, buildings, chooseSpecialty);
}

// Write Action to output stream
void Action::writeTo(OutputStream& stream) const {
    stream.write((int)(moves.size()));
    for (const model::MoveAction& movesElement : moves) {
        movesElement.writeTo(stream);
    }
    stream.write((int)(buildings.size()));
    for (const model::BuildingAction& buildingsElement : buildings) {
        buildingsElement.writeTo(stream);
    }
    if (chooseSpecialty) {
        stream.write(true);
        const model::Specialty& chooseSpecialtyValue = *chooseSpecialty;
        stream.write((int)(chooseSpecialtyValue));
    } else {
        stream.write(false);
    }
}

// Get string representation of Action
std::string Action::toString() const {
    std::stringstream ss;
    ss << "Action { ";
    ss << "moves: ";
    ss << "[ ";
    for (size_t movesIndex = 0; movesIndex < moves.size(); movesIndex++) {
        const model::MoveAction& movesElement = moves[movesIndex];
        if (movesIndex != 0) {
            ss << ", ";
        }
        ss << movesElement.toString();
    }
    ss << " ]";
    ss << ", ";
    ss << "buildings: ";
    ss << "[ ";
    for (size_t buildingsIndex = 0; buildingsIndex < buildings.size(); buildingsIndex++) {
        const model::BuildingAction& buildingsElement = buildings[buildingsIndex];
        if (buildingsIndex != 0) {
            ss << ", ";
        }
        ss << buildingsElement.toString();
    }
    ss << " ]";
    ss << ", ";
    ss << "chooseSpecialty: ";
    if (chooseSpecialty) {
        const model::Specialty& chooseSpecialtyValue = *chooseSpecialty;
        ss << specialtyToString(chooseSpecialtyValue);
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}