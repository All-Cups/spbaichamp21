module model.player;

import stream;
import std.conv;
import std.typecons : Nullable;


/// Player (game participant)
struct Player {
    /// Current score points
    int score;

    this(int score) {
        this.score = score;
    }

    /// Read Player from reader
    static Player readFrom(Stream reader) {
        int score;
        score = reader.readInt();
        return Player(score);
    }

    /// Write Player to writer
    void writeTo(Stream writer) const {
        writer.write(score);
    }
}