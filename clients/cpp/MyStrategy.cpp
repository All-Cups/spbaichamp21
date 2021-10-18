#include "MyStrategy.hpp"
#include <exception>

MyStrategy::MyStrategy() {}

model::Action MyStrategy::getAction(const model::Game& game)
{
    return model::Action(std::vector<model::MoveAction>(), std::vector<model::BuildingAction>(), std::optional<model::Specialty>());
}