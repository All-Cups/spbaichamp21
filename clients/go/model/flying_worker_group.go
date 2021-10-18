package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// A flying worker group
type FlyingWorkerGroup struct {
    // Index of player controlling workers
    PlayerIndex int32
    // Number of workers in the group
    Number int32
    // Tick when workers left previous planet on their path
    DepartureTick int32
    // Id of the previous planet on the path
    DeparturePlanet int32
    // Tick when workers will arrive to the next planet in their path
    NextPlanetArrivalTick int32
    // Id of the next planet in the path
    NextPlanet int32
    // Id of the target planet
    TargetPlanet int32
    // Resource that workers are carrying
    Resource *Resource
}

func NewFlyingWorkerGroup(playerIndex int32, number int32, departureTick int32, departurePlanet int32, nextPlanetArrivalTick int32, nextPlanet int32, targetPlanet int32, resource *Resource) FlyingWorkerGroup {
    return FlyingWorkerGroup {
        PlayerIndex: playerIndex,
        Number: number,
        DepartureTick: departureTick,
        DeparturePlanet: departurePlanet,
        NextPlanetArrivalTick: nextPlanetArrivalTick,
        NextPlanet: nextPlanet,
        TargetPlanet: targetPlanet,
        Resource: resource,
    }
}

// Read FlyingWorkerGroup from reader
func ReadFlyingWorkerGroup(reader io.Reader) FlyingWorkerGroup {
    var playerIndex int32
    playerIndex = ReadInt32(reader)
    var number int32
    number = ReadInt32(reader)
    var departureTick int32
    departureTick = ReadInt32(reader)
    var departurePlanet int32
    departurePlanet = ReadInt32(reader)
    var nextPlanetArrivalTick int32
    nextPlanetArrivalTick = ReadInt32(reader)
    var nextPlanet int32
    nextPlanet = ReadInt32(reader)
    var targetPlanet int32
    targetPlanet = ReadInt32(reader)
    var resource *Resource
    if ReadBool(reader) {
        var resourceValue Resource
        resourceValue = ReadResource(reader)
        resource = &resourceValue
    } else {
        resource = nil
    }
    return FlyingWorkerGroup {
        PlayerIndex: playerIndex,
        Number: number,
        DepartureTick: departureTick,
        DeparturePlanet: departurePlanet,
        NextPlanetArrivalTick: nextPlanetArrivalTick,
        NextPlanet: nextPlanet,
        TargetPlanet: targetPlanet,
        Resource: resource,
    }
}

// Write FlyingWorkerGroup to writer
func (flyingWorkerGroup FlyingWorkerGroup) Write(writer io.Writer) {
    playerIndex := flyingWorkerGroup.PlayerIndex
    WriteInt32(writer, playerIndex)
    number := flyingWorkerGroup.Number
    WriteInt32(writer, number)
    departureTick := flyingWorkerGroup.DepartureTick
    WriteInt32(writer, departureTick)
    departurePlanet := flyingWorkerGroup.DeparturePlanet
    WriteInt32(writer, departurePlanet)
    nextPlanetArrivalTick := flyingWorkerGroup.NextPlanetArrivalTick
    WriteInt32(writer, nextPlanetArrivalTick)
    nextPlanet := flyingWorkerGroup.NextPlanet
    WriteInt32(writer, nextPlanet)
    targetPlanet := flyingWorkerGroup.TargetPlanet
    WriteInt32(writer, targetPlanet)
    resource := flyingWorkerGroup.Resource
    if resource == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        resourceValue := *resource
        WriteInt32(writer, int32(resourceValue))
    }
}

// Get string representation of FlyingWorkerGroup
func (flyingWorkerGroup FlyingWorkerGroup) String() string {
    stringResult := "{ "
    stringResult += "PlayerIndex: "
    playerIndex := flyingWorkerGroup.PlayerIndex
    stringResult += fmt.Sprint(playerIndex)
    stringResult += ", "
    stringResult += "Number: "
    number := flyingWorkerGroup.Number
    stringResult += fmt.Sprint(number)
    stringResult += ", "
    stringResult += "DepartureTick: "
    departureTick := flyingWorkerGroup.DepartureTick
    stringResult += fmt.Sprint(departureTick)
    stringResult += ", "
    stringResult += "DeparturePlanet: "
    departurePlanet := flyingWorkerGroup.DeparturePlanet
    stringResult += fmt.Sprint(departurePlanet)
    stringResult += ", "
    stringResult += "NextPlanetArrivalTick: "
    nextPlanetArrivalTick := flyingWorkerGroup.NextPlanetArrivalTick
    stringResult += fmt.Sprint(nextPlanetArrivalTick)
    stringResult += ", "
    stringResult += "NextPlanet: "
    nextPlanet := flyingWorkerGroup.NextPlanet
    stringResult += fmt.Sprint(nextPlanet)
    stringResult += ", "
    stringResult += "TargetPlanet: "
    targetPlanet := flyingWorkerGroup.TargetPlanet
    stringResult += fmt.Sprint(targetPlanet)
    stringResult += ", "
    stringResult += "Resource: "
    resource := flyingWorkerGroup.Resource
    if resource == nil {
        stringResult += "nil"
    } else {
        resourceValue := *resource
        stringResult += ResourceToString(resourceValue)
    }
    stringResult += " }"
    return stringResult
}