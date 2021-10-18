package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// Group of workers on a planet
type WorkerGroup struct {
    // Index of player controlling the workers
    PlayerIndex int32
    // Number of workers in the group
    Number int32
}

func NewWorkerGroup(playerIndex int32, number int32) WorkerGroup {
    return WorkerGroup {
        PlayerIndex: playerIndex,
        Number: number,
    }
}

// Read WorkerGroup from reader
func ReadWorkerGroup(reader io.Reader) WorkerGroup {
    var playerIndex int32
    playerIndex = ReadInt32(reader)
    var number int32
    number = ReadInt32(reader)
    return WorkerGroup {
        PlayerIndex: playerIndex,
        Number: number,
    }
}

// Write WorkerGroup to writer
func (workerGroup WorkerGroup) Write(writer io.Writer) {
    playerIndex := workerGroup.PlayerIndex
    WriteInt32(writer, playerIndex)
    number := workerGroup.Number
    WriteInt32(writer, number)
}

// Get string representation of WorkerGroup
func (workerGroup WorkerGroup) String() string {
    stringResult := "{ "
    stringResult += "PlayerIndex: "
    playerIndex := workerGroup.PlayerIndex
    stringResult += fmt.Sprint(playerIndex)
    stringResult += ", "
    stringResult += "Number: "
    number := workerGroup.Number
    stringResult += fmt.Sprint(number)
    stringResult += " }"
    return stringResult
}