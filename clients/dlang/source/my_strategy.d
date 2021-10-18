import model;
import std.typecons : Nullable;

class MyStrategy
{
    Action getAction(Game game)
    {
        return Action(new MoveAction[0], new BuildingAction[0], Nullable!Specialty.init);
    }
}