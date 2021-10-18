package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// TODO - Document
type Player struct {
    // TODO - Document
    Score int32
}

func NewPlayer(score int32) Player {
    return Player {
        Score: score,
    }
}

// Read Player from reader
func ReadPlayer(reader io.Reader) Player {
    var score int32
    score = ReadInt32(reader)
    return Player {
        Score: score,
    }
}

// Write Player to writer
func (player Player) Write(writer io.Writer) {
    score := player.Score
    WriteInt32(writer, score)
}

// Get string representation of Player
func (player Player) String() string {
    stringResult := "{ "
    stringResult += "Score: "
    score := player.Score
    stringResult += fmt.Sprint(score)
    stringResult += " }"
    return stringResult
}