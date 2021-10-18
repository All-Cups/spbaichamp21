/**
 * TODO - Document
 */
class BuildingType {
    constructor(name, tag) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * TODO - Document
     */
    static QUARRY = new BuildingType("QUARRY", 0);
    /**
     * TODO - Document
     */
    static MINES = new BuildingType("MINES", 1);
    /**
     * TODO - Document
     */
    static CAREER = new BuildingType("CAREER", 2);
    /**
     * TODO - Document
     */
    static FARM = new BuildingType("FARM", 3);
    /**
     * TODO - Document
     */
    static FOUNDRY = new BuildingType("FOUNDRY", 4);
    /**
     * TODO - Document
     */
    static FURNACE = new BuildingType("FURNACE", 5);
    /**
     * TODO - Document
     */
    static BIOREACTOR = new BuildingType("BIOREACTOR", 6);
    /**
     * TODO - Document
     */
    static CHIP_FACTORY = new BuildingType("CHIP_FACTORY", 7);
    /**
     * TODO - Document
     */
    static ACCUMULATOR_FACTORY = new BuildingType("ACCUMULATOR_FACTORY", 8);
    /**
     * TODO - Document
     */
    static REPLICATOR = new BuildingType("REPLICATOR", 9);

    /**
     * Read BuildingType from input stream
     */
    static async readFrom(stream) {
        const tag = await stream.readInt();
        if (tag == BuildingType.QUARRY.tag) {
            return BuildingType.QUARRY;
        }
        if (tag == BuildingType.MINES.tag) {
            return BuildingType.MINES;
        }
        if (tag == BuildingType.CAREER.tag) {
            return BuildingType.CAREER;
        }
        if (tag == BuildingType.FARM.tag) {
            return BuildingType.FARM;
        }
        if (tag == BuildingType.FOUNDRY.tag) {
            return BuildingType.FOUNDRY;
        }
        if (tag == BuildingType.FURNACE.tag) {
            return BuildingType.FURNACE;
        }
        if (tag == BuildingType.BIOREACTOR.tag) {
            return BuildingType.BIOREACTOR;
        }
        if (tag == BuildingType.CHIP_FACTORY.tag) {
            return BuildingType.CHIP_FACTORY;
        }
        if (tag == BuildingType.ACCUMULATOR_FACTORY.tag) {
            return BuildingType.ACCUMULATOR_FACTORY;
        }
        if (tag == BuildingType.REPLICATOR.tag) {
            return BuildingType.REPLICATOR;
        }
        throw new Error("Unexpected tag value");
    }

    /**
     * Write BuildingType to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(this.tag);
    }

    [Symbol.for('nodejs.util.inspect.custom')]() {
        return this.name;
    }
}

module.exports = BuildingType;