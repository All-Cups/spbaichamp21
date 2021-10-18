using SpbAiChamp.Model;

namespace SpbAiChamp
{
    public class MyStrategy
    {
        public Action GetAction(Game game)
        {
            return new Action(new MoveAction[0], new BuildingAction[0], null);
        }
    }
}