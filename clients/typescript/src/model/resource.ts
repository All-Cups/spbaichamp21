import { Stream } from "../stream";

/**
 * Resource type
 */
export class Resource {
    readonly name: string;
    readonly tag: number;

    constructor(name: string, tag: number) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * Stone
     */
    static readonly STONE = new Resource("STONE", 0);
    /**
     * Ore
     */
    static readonly ORE = new Resource("ORE", 1);
    /**
     * Sand
     */
    static readonly SAND = new Resource("SAND", 2);
    /**
     * Organics
     */
    static readonly ORGANICS = new Resource("ORGANICS", 3);
    /**
     * Metal
     */
    static readonly METAL = new Resource("METAL", 4);
    /**
     * Silicon
     */
    static readonly SILICON = new Resource("SILICON", 5);
    /**
     * Plastic
     */
    static readonly PLASTIC = new Resource("PLASTIC", 6);
    /**
     * Chip
     */
    static readonly CHIP = new Resource("CHIP", 7);
    /**
     * Accumulator
     */
    static readonly ACCUMULATOR = new Resource("ACCUMULATOR", 8);

    /**
     * Read Resource from input stream
     */
    static async readFrom(stream: Stream): Promise<Resource> {
        const tag = await stream.readInt();
        if (tag == Resource.STONE.tag) {
            return Resource.STONE;
        }
        if (tag == Resource.ORE.tag) {
            return Resource.ORE;
        }
        if (tag == Resource.SAND.tag) {
            return Resource.SAND;
        }
        if (tag == Resource.ORGANICS.tag) {
            return Resource.ORGANICS;
        }
        if (tag == Resource.METAL.tag) {
            return Resource.METAL;
        }
        if (tag == Resource.SILICON.tag) {
            return Resource.SILICON;
        }
        if (tag == Resource.PLASTIC.tag) {
            return Resource.PLASTIC;
        }
        if (tag == Resource.CHIP.tag) {
            return Resource.CHIP;
        }
        if (tag == Resource.ACCUMULATOR.tag) {
            return Resource.ACCUMULATOR;
        }
        throw new Error("Unexpected tag value");
    }

    /**
     * Write Resource to output stream
     */
    async writeTo(stream: Stream) {
        await stream.writeInt(this.tag);
    }

    [Symbol.for('nodejs.util.inspect.custom')]() {
        return this.name;
    }
}