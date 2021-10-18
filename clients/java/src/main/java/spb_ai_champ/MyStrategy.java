package spb_ai_champ;

import spb_ai_champ.model.*;

public class MyStrategy {
    public Action getAction(Game game) {
        return new Action(new MoveAction[0], new BuildingAction[0], null);
    }
}