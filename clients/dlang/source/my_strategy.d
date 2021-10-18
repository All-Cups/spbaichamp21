import model;

class MyStrategy
{
    Action getAction(Game game)
    {
        return Action(new MoveAction[0], new BuildingAction[0]);
    }
}