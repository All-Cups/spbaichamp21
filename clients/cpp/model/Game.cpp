#include "Game.hpp"

namespace model {

Game::Game(int myIndex, int currentTick, int maxTickCount, std::vector<model::Player> players, std::vector<model::Planet> planets, std::vector<model::FlyingWorkerGroup> flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int logisticsUpgrade, int productionUpgrade, int combatUpgrade, int maxBuilders, std::unordered_map<model::BuildingType, model::BuildingProperties> buildingProperties, bool specialtiesAllowed, std::optional<int> viewDistance) : myIndex(myIndex), currentTick(currentTick), maxTickCount(maxTickCount), players(players), planets(planets), flyingWorkerGroups(flyingWorkerGroups), maxFlyingWorkerGroups(maxFlyingWorkerGroups), maxTravelDistance(maxTravelDistance), logisticsUpgrade(logisticsUpgrade), productionUpgrade(productionUpgrade), combatUpgrade(combatUpgrade), maxBuilders(maxBuilders), buildingProperties(buildingProperties), specialtiesAllowed(specialtiesAllowed), viewDistance(viewDistance) { }

// Read Game from input stream
Game Game::readFrom(InputStream& stream) {
    int myIndex = stream.readInt();
    int currentTick = stream.readInt();
    int maxTickCount = stream.readInt();
    std::vector<model::Player> players = std::vector<model::Player>();
    size_t playersSize = stream.readInt();
    players.reserve(playersSize);
    for (size_t playersIndex = 0; playersIndex < playersSize; playersIndex++) {
        model::Player playersElement = model::Player::readFrom(stream);
        players.emplace_back(playersElement);
    }
    std::vector<model::Planet> planets = std::vector<model::Planet>();
    size_t planetsSize = stream.readInt();
    planets.reserve(planetsSize);
    for (size_t planetsIndex = 0; planetsIndex < planetsSize; planetsIndex++) {
        model::Planet planetsElement = model::Planet::readFrom(stream);
        planets.emplace_back(planetsElement);
    }
    std::vector<model::FlyingWorkerGroup> flyingWorkerGroups = std::vector<model::FlyingWorkerGroup>();
    size_t flyingWorkerGroupsSize = stream.readInt();
    flyingWorkerGroups.reserve(flyingWorkerGroupsSize);
    for (size_t flyingWorkerGroupsIndex = 0; flyingWorkerGroupsIndex < flyingWorkerGroupsSize; flyingWorkerGroupsIndex++) {
        model::FlyingWorkerGroup flyingWorkerGroupsElement = model::FlyingWorkerGroup::readFrom(stream);
        flyingWorkerGroups.emplace_back(flyingWorkerGroupsElement);
    }
    int maxFlyingWorkerGroups = stream.readInt();
    int maxTravelDistance = stream.readInt();
    int logisticsUpgrade = stream.readInt();
    int productionUpgrade = stream.readInt();
    int combatUpgrade = stream.readInt();
    int maxBuilders = stream.readInt();
    size_t buildingPropertiesSize = stream.readInt();
    std::unordered_map<model::BuildingType, model::BuildingProperties> buildingProperties = std::unordered_map<model::BuildingType, model::BuildingProperties>();
    buildingProperties.reserve(buildingPropertiesSize);
    for (size_t buildingPropertiesIndex = 0; buildingPropertiesIndex < buildingPropertiesSize; buildingPropertiesIndex++) {
        model::BuildingType buildingPropertiesKey = readBuildingType(stream);
        model::BuildingProperties buildingPropertiesValue = model::BuildingProperties::readFrom(stream);
        buildingProperties.emplace(std::make_pair(buildingPropertiesKey, buildingPropertiesValue));
    }
    bool specialtiesAllowed = stream.readBool();
    std::optional<int> viewDistance = std::optional<int>();
    if (stream.readBool()) {
        int viewDistanceValue = stream.readInt();
        viewDistance.emplace(viewDistanceValue);
    }
    return Game(myIndex, currentTick, maxTickCount, players, planets, flyingWorkerGroups, maxFlyingWorkerGroups, maxTravelDistance, logisticsUpgrade, productionUpgrade, combatUpgrade, maxBuilders, buildingProperties, specialtiesAllowed, viewDistance);
}

// Write Game to output stream
void Game::writeTo(OutputStream& stream) const {
    stream.write(myIndex);
    stream.write(currentTick);
    stream.write(maxTickCount);
    stream.write((int)(players.size()));
    for (const model::Player& playersElement : players) {
        playersElement.writeTo(stream);
    }
    stream.write((int)(planets.size()));
    for (const model::Planet& planetsElement : planets) {
        planetsElement.writeTo(stream);
    }
    stream.write((int)(flyingWorkerGroups.size()));
    for (const model::FlyingWorkerGroup& flyingWorkerGroupsElement : flyingWorkerGroups) {
        flyingWorkerGroupsElement.writeTo(stream);
    }
    stream.write(maxFlyingWorkerGroups);
    stream.write(maxTravelDistance);
    stream.write(logisticsUpgrade);
    stream.write(productionUpgrade);
    stream.write(combatUpgrade);
    stream.write(maxBuilders);
    stream.write((int)(buildingProperties.size()));
    for (const auto& buildingPropertiesEntry : buildingProperties) {
        const model::BuildingType& buildingPropertiesKey = buildingPropertiesEntry.first;
        const model::BuildingProperties& buildingPropertiesValue = buildingPropertiesEntry.second;
        stream.write((int)(buildingPropertiesKey));
        buildingPropertiesValue.writeTo(stream);
    }
    stream.write(specialtiesAllowed);
    if (viewDistance) {
        stream.write(true);
        const int& viewDistanceValue = *viewDistance;
        stream.write(viewDistanceValue);
    } else {
        stream.write(false);
    }
}

// Get string representation of Game
std::string Game::toString() const {
    std::stringstream ss;
    ss << "Game { ";
    ss << "myIndex: ";
    ss << myIndex;
    ss << ", ";
    ss << "currentTick: ";
    ss << currentTick;
    ss << ", ";
    ss << "maxTickCount: ";
    ss << maxTickCount;
    ss << ", ";
    ss << "players: ";
    ss << "[ ";
    for (size_t playersIndex = 0; playersIndex < players.size(); playersIndex++) {
        const model::Player& playersElement = players[playersIndex];
        if (playersIndex != 0) {
            ss << ", ";
        }
        ss << playersElement.toString();
    }
    ss << " ]";
    ss << ", ";
    ss << "planets: ";
    ss << "[ ";
    for (size_t planetsIndex = 0; planetsIndex < planets.size(); planetsIndex++) {
        const model::Planet& planetsElement = planets[planetsIndex];
        if (planetsIndex != 0) {
            ss << ", ";
        }
        ss << planetsElement.toString();
    }
    ss << " ]";
    ss << ", ";
    ss << "flyingWorkerGroups: ";
    ss << "[ ";
    for (size_t flyingWorkerGroupsIndex = 0; flyingWorkerGroupsIndex < flyingWorkerGroups.size(); flyingWorkerGroupsIndex++) {
        const model::FlyingWorkerGroup& flyingWorkerGroupsElement = flyingWorkerGroups[flyingWorkerGroupsIndex];
        if (flyingWorkerGroupsIndex != 0) {
            ss << ", ";
        }
        ss << flyingWorkerGroupsElement.toString();
    }
    ss << " ]";
    ss << ", ";
    ss << "maxFlyingWorkerGroups: ";
    ss << maxFlyingWorkerGroups;
    ss << ", ";
    ss << "maxTravelDistance: ";
    ss << maxTravelDistance;
    ss << ", ";
    ss << "logisticsUpgrade: ";
    ss << logisticsUpgrade;
    ss << ", ";
    ss << "productionUpgrade: ";
    ss << productionUpgrade;
    ss << ", ";
    ss << "combatUpgrade: ";
    ss << combatUpgrade;
    ss << ", ";
    ss << "maxBuilders: ";
    ss << maxBuilders;
    ss << ", ";
    ss << "buildingProperties: ";
    ss << "{ ";
    size_t buildingPropertiesIndex = 0;
    for (const auto& buildingPropertiesEntry : buildingProperties) {
        if (buildingPropertiesIndex != 0) {
            ss << ", ";
        }
        const model::BuildingType& buildingPropertiesKey = buildingPropertiesEntry.first;
        const model::BuildingProperties& buildingPropertiesValue = buildingPropertiesEntry.second;
        ss << buildingTypeToString(buildingPropertiesKey);
        ss << ": ";
        ss << buildingPropertiesValue.toString();
        buildingPropertiesIndex++;
    }
    ss << " }";
    ss << ", ";
    ss << "specialtiesAllowed: ";
    ss << specialtiesAllowed;
    ss << ", ";
    ss << "viewDistance: ";
    if (viewDistance) {
        const int& viewDistanceValue = *viewDistance;
        ss << viewDistanceValue;
    } else {
        ss << "none";
    }
    ss << " }";
    return ss.str();
}

}