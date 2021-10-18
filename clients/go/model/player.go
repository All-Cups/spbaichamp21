package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// Player (game participant)
type Player struct {
    // Team index
    TeamIndex int32
    // Current score points
    Score int32
    // Player's specialty
    Specialty *Specialty
}

func NewPlayer(teamIndex int32, score int32, specialty *Specialty) Player {
    return Player {
        TeamIndex: teamIndex,
        Score: score,
        Specialty: specialty,
    }
}

// Read Player from reader
func ReadPlayer(reader io.Reader) Player {
    var teamIndex int32
    teamIndex = ReadInt32(reader)
    var score int32
    score = ReadInt32(reader)
    var specialty *Specialty
    if ReadBool(reader) {
        var specialtyValue Specialty
        specialtyValue = ReadSpecialty(reader)
        specialty = &specialtyValue
    } else {
        specialty = nil
    }
    return Player {
        TeamIndex: teamIndex,
        Score: score,
        Specialty: specialty,
    }
}

// Write Player to writer
func (player Player) Write(writer io.Writer) {
    teamIndex := player.TeamIndex
    WriteInt32(writer, teamIndex)
    score := player.Score
    WriteInt32(writer, score)
    specialty := player.Specialty
    if specialty == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        specialtyValue := *specialty
        WriteInt32(writer, int32(specialtyValue))
    }
}

// Get string representation of Player
func (player Player) String() string {
    stringResult := "{ "
    stringResult += "TeamIndex: "
    teamIndex := player.TeamIndex
    stringResult += fmt.Sprint(teamIndex)
    stringResult += ", "
    stringResult += "Score: "
    score := player.Score
    stringResult += fmt.Sprint(score)
    stringResult += ", "
    stringResult += "Specialty: "
    specialty := player.Specialty
    if specialty == nil {
        stringResult += "nil"
    } else {
        specialtyValue := *specialty
        stringResult += SpecialtyToString(specialtyValue)
    }
    stringResult += " }"
    return stringResult
}