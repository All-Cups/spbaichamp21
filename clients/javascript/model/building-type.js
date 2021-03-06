/**
 * Building type
 */
class BuildingType {
    constructor(name, tag) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * Quarry harvests stone
     */
    static QUARRY = new BuildingType("QUARRY", 0);
    /**
     * Mines harvests ore
     */
    static MINES = new BuildingType("MINES", 1);
    /**
     * Career harvest sand
     */
    static CAREER = new BuildingType("CAREER", 2);
    /**
     * Farm harvests organics
     */
    static FARM = new BuildingType("FARM", 3);
    /**
     * Foundry produces metal
     */
    static FOUNDRY = new BuildingType("FOUNDRY", 4);
    /**
     * Furnace produces silicon
     */
    static FURNACE = new BuildingType("FURNACE", 5);
    /**
     * Bioreactor produces plastic
     */
    static BIOREACTOR = new BuildingType("BIOREACTOR", 6);
    /**
     * Chip factory produces chips
     */
    static CHIP_FACTORY = new BuildingType("CHIP_FACTORY", 7);
    /**
     * Accumulator factory produces accumulators
     */
    static ACCUMULATOR_FACTORY = new BuildingType("ACCUMULATOR_FACTORY", 8);
    /**
     * Replicator produces new workers
     */
    static REPLICATOR = new BuildingType("REPLICATOR", 9);
    /**
     * Second level replicator
     */
    static REPLICATOR2 = new BuildingType("REPLICATOR2", 10);
    /**
     * Third level replicator
     */
    static REPLICATOR3 = new BuildingType("REPLICATOR3", 11);

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
        if (tag == BuildingType.REPLICATOR2.tag) {
            return BuildingType.REPLICATOR2;
        }
        if (tag == BuildingType.REPLICATOR3.tag) {
            return BuildingType.REPLICATOR3;
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