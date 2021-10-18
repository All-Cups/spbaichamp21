import { Game } from "./model/game";
import { Action } from "./model/action";

export class MyStrategy {
    async getAction(game: Game): Promise<Action> {
        return new Action([], []);
    }
}