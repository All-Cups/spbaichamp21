package model

import "io"

// TODO - Document
type DebugData struct {
}

func NewDebugData() DebugData {
    return DebugData {
    }
}

// Read DebugData from reader
func ReadDebugData(reader io.Reader) DebugData {
    return DebugData {
    }
}

// Write DebugData to writer
func (debugData DebugData) Write(writer io.Writer) {
}

// Get string representation of DebugData
func (debugData DebugData) String() string {
    stringResult := "{ "
    stringResult += " }"
    return stringResult
}