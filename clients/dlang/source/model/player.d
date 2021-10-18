module model.player;

import stream;
import std.conv;
import std.typecons : Nullable;
import model.specialty;

/// Player (game participant)
struct Player {
    /// Team index
    int teamIndex;
    /// Current score points
    int score;
    /// Player's specialty
    Nullable!(model.Specialty) specialty;

    this(int teamIndex, int score, Nullable!(model.Specialty) specialty) {
        this.teamIndex = teamIndex;
        this.score = score;
        this.specialty = specialty;
    }

    /// Read Player from reader
    static Player readFrom(Stream reader) {
        int teamIndex;
        teamIndex = reader.readInt();
        int score;
        score = reader.readInt();
        Nullable!(model.Specialty) specialty;
        if (reader.readBool()) {
            specialty = readSpecialty(reader);
        } else {
            specialty.nullify();
        }
        return Player(teamIndex, score, specialty);
    }

    /// Write Player to writer
    void writeTo(Stream writer) const {
        writer.write(teamIndex);
        writer.write(score);
        if (specialty.isNull()) {
            writer.write(false);
        } else {
            writer.write(true);
            auto specialtyValue = specialty.get;
            writer.write(cast(int)(specialtyValue));
        }
    }
}