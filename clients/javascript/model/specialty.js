/**
 * Player's specialty
 */
class Specialty {
    constructor(name, tag) {
        this.name = name;
        this.tag = tag;
    }

    /**
     * Logistics. Increased travel distance
     */
    static LOGISTICS = new Specialty("LOGISTICS", 0);
    /**
     * Production. Increased work speed
     */
    static PRODUCTION = new Specialty("PRODUCTION", 1);
    /**
     * Combat. Increased damage
     */
    static COMBAT = new Specialty("COMBAT", 2);

    /**
     * Read Specialty from input stream
     */
    static async readFrom(stream) {
        const tag = await stream.readInt();
        if (tag == Specialty.LOGISTICS.tag) {
            return Specialty.LOGISTICS;
        }
        if (tag == Specialty.PRODUCTION.tag) {
            return Specialty.PRODUCTION;
        }
        if (tag == Specialty.COMBAT.tag) {
            return Specialty.COMBAT;
        }
        throw new Error("Unexpected tag value");
    }

    /**
     * Write Specialty to output stream
     */
    async writeTo(stream) {
        await stream.writeInt(this.tag);
    }

    [Symbol.for('nodejs.util.inspect.custom')]() {
        return this.name;
    }
}

module.exports = Specialty;