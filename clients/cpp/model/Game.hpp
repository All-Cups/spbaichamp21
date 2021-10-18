#ifndef __MODEL_GAME_HPP__
#define __MODEL_GAME_HPP__

#include "Stream.hpp"
#include "model/Building.hpp"
#include "model/BuildingProperties.hpp"
#include "model/BuildingType.hpp"
#include "model/FlyingWorkerGroup.hpp"
#include "model/Planet.hpp"
#include "model/Player.hpp"
#include "model/Resource.hpp"
#include "model/WorkerGroup.hpp"
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <vector>

namespace model {

// TODO - Document
class Game {
public:
    // TODO - Document
    int myIndex;
    // TODO - Document
    int currentTick;
    // TODO - Document
    int maxTickCount;
    // TODO - Document
    std::vector<model::Player> players;
    // TODO - Document
    std::vector<model::Planet> planets;
    // TODO - Document
    std::vector<model::FlyingWorkerGroup> flyingWorkerGroups;
    // TODO - Document
    int maxFlyingWorkerGroups;
    // TODO - Document
    int maxTravelDistance;
    // TODO - Document
    int maxBuilders;
    // TODO - Document
    std::unordered_map<model::BuildingType, model::BuildingProperties> buildingProperties;

    Game(int myIndex, int currentTick, int maxTickCount, std::vector<model::Player> players, std::vector<model::Planet> planets, std::vector<model::FlyingWorkerGroup> flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int maxBuilders, std::unordered_map<model::BuildingType, model::BuildingProperties> buildingProperties);

    // Read Game from input stream
    static Game readFrom(InputStream& stream);

    // Write Game to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of Game
    std::string toString() const;
};

}

#endif