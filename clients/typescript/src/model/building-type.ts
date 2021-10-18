import { Stream } from "../stream";

/**
 * TODO - Document
 */
export class BuildingType {
    readonly name: string;
    readonly tag: number;

    constructor(name: string, tag: number) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * TODO - Document
     */
    static readonly QUARRY = new BuildingType("QUARRY", 0);
    /**
     * TODO - Document
     */
    static readonly MINES = new BuildingType("MINES", 1);
    /**
     * TODO - Document
     */
    static readonly CAREER = new BuildingType("CAREER", 2);
    /**
     * TODO - Document
     */
    static readonly FARM = new BuildingType("FARM", 3);
    /**
     * TODO - Document
     */
    static readonly FOUNDRY = new BuildingType("FOUNDRY", 4);
    /**
     * TODO - Document
     */
    static readonly FURNACE = new BuildingType("FURNACE", 5);
    /**
     * TODO - Document
     */
    static readonly BIOREACTOR = new BuildingType("BIOREACTOR", 6);
    /**
     * TODO - Document
     */
    static readonly CHIP_FACTORY = new BuildingType("CHIP_FACTORY", 7);
    /**
     * TODO - Document
     */
    static readonly ACCUMULATOR_FACTORY = new BuildingType("ACCUMULATOR_FACTORY", 8);
    /**
     * TODO - Document
     */
    static readonly REPLICATOR = new BuildingType("REPLICATOR", 9);

    /**
     * Read BuildingType from input stream
     */
    static async readFrom(stream: Stream): Promise<BuildingType> {
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
    async writeTo(stream: Stream) {
        await stream.writeInt(this.tag);
    }

    [Symbol.for('nodejs.util.inspect.custom')]() {
        return this.name;
    }
}