/**
 * Resource type
 */
class Resource {
    constructor(name, tag) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * Stone
     */
    static STONE = new Resource("STONE", 0);
    /**
     * Ore
     */
    static ORE = new Resource("ORE", 1);
    /**
     * Sand
     */
    static SAND = new Resource("SAND", 2);
    /**
     * Organics
     */
    static ORGANICS = new Resource("ORGANICS", 3);
    /**
     * Metal
     */
    static METAL = new Resource("METAL", 4);
    /**
     * Silicon
     */
    static SILICON = new Resource("SILICON", 5);
    /**
     * Plastic
     */
    static PLASTIC = new Resource("PLASTIC", 6);
    /**
     * Chip
     */
    static CHIP = new Resource("CHIP", 7);
    /**
     * Accumulator
     */
    static ACCUMULATOR = new Resource("ACCUMULATOR", 8);

    /**
     * Read Resource from input stream
     */
    static async readFrom(stream) {
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
    async writeTo(stream) {
        await stream.writeInt(this.tag);
    }

    [Symbol.for('nodejs.util.inspect.custom')]() {
        return this.name;
    }
}

module.exports = Resource;