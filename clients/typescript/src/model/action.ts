import { BuildingAction } from "./building-action";
import { MoveAction } from "./move-action";
import { Stream } from "../stream";

/**
 * Player's actions
 */
export class Action {
    /**
     * List of movement orders
     */
    moves: Array<MoveAction>
    /**
     * List of building orders
     */
    buildings: Array<BuildingAction>

    constructor(moves: Array<MoveAction>, buildings: Array<BuildingAction>) {
        this.moves = moves;
        this.buildings = buildings;
    }

    /**
     * Read Action from input stream
     */
    static async readFrom(stream: Stream): Promise<Action> {
        let moves;
        moves = [];
        for (let movesCount = await stream.readInt(); movesCount > 0; movesCount--) {
            let movesElement;
            movesElement = await MoveAction.readFrom(stream);
            moves.push(movesElement);
        }
        let buildings;
        buildings = [];
        for (let buildingsCount = await stream.readInt(); buildingsCount > 0; buildingsCount--) {
            let buildingsElement;
            buildingsElement = await BuildingAction.readFrom(stream);
            buildings.push(buildingsElement);
        }
        return new Action(moves, buildings)
    }

    /**
     * Write Action to output stream
     */
    async writeTo(stream: Stream) {
        let moves = this.moves;
        await stream.writeInt(moves.length);
        for (let movesElement of moves) {
            await movesElement.writeTo(stream);
        }
        let buildings = this.buildings;
        await stream.writeInt(buildings.length);
        for (let buildingsElement of buildings) {
            await buildingsElement.writeTo(stream);
        }
    }
}