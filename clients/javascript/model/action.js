const BuildingAction = require.main.require('./model/building-action');
const MoveAction = require.main.require('./model/move-action');
/**
 * TODO - Document
 */
class Action {
    /**
     * TODO - Document
     */
    moves;
    /**
     * TODO - Document
     */
    buildings;

    constructor(moves, buildings) {
        this.moves = moves;
        this.buildings = buildings;
    }

    /**
     * Read Action from input stream
     */
    static async readFrom(stream) {
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
        return new Action(moves, buildings);
    }

    /**
     * Write Action to output stream
     */
    async writeTo(stream) {
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
module.exports = Action