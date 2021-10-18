package model

import "fmt"
import "io"
import . "spb_ai_champ/stream"

// Current game's state
type Game struct {
    // Your player's index
    MyIndex int32
    // Current tick number
    CurrentTick int32
    // Max number of ticks in the game
    MaxTickCount int32
    // List of players
    Players []Player
    // List of planets
    Planets []Planet
    // List of flying worker groups
    FlyingWorkerGroups []FlyingWorkerGroup
    // Max number of flying worker groups for one player
    MaxFlyingWorkerGroups int32
    // Max distance of direct travel between planets
    MaxTravelDistance int32
    // Additional distance of direct travel between planets for player with Logistics specialty
    LogisticsUpgrade int32
    // Additional work done by player with Production specialty (in percent)
    ProductionUpgrade int32
    // Additional strength workers for player with Combat specialty (in percent)
    CombatUpgrade int32
    // Max number of workers performing building on one planet
    MaxBuilders int32
    // Properties of every building type
    BuildingProperties map[BuildingType]BuildingProperties
    // Whether choosing specialties is allowed
    SpecialtiesAllowed bool
    // View distance
    ViewDistance *int32
}

func NewGame(myIndex int32, currentTick int32, maxTickCount int32, players []Player, planets []Planet, flyingWorkerGroups []FlyingWorkerGroup, maxFlyingWorkerGroups int32, maxTravelDistance int32, logisticsUpgrade int32, productionUpgrade int32, combatUpgrade int32, maxBuilders int32, buildingProperties map[BuildingType]BuildingProperties, specialtiesAllowed bool, viewDistance *int32) Game {
    return Game {
        MyIndex: myIndex,
        CurrentTick: currentTick,
        MaxTickCount: maxTickCount,
        Players: players,
        Planets: planets,
        FlyingWorkerGroups: flyingWorkerGroups,
        MaxFlyingWorkerGroups: maxFlyingWorkerGroups,
        MaxTravelDistance: maxTravelDistance,
        LogisticsUpgrade: logisticsUpgrade,
        ProductionUpgrade: productionUpgrade,
        CombatUpgrade: combatUpgrade,
        MaxBuilders: maxBuilders,
        BuildingProperties: buildingProperties,
        SpecialtiesAllowed: specialtiesAllowed,
        ViewDistance: viewDistance,
    }
}

// Read Game from reader
func ReadGame(reader io.Reader) Game {
    var myIndex int32
    myIndex = ReadInt32(reader)
    var currentTick int32
    currentTick = ReadInt32(reader)
    var maxTickCount int32
    maxTickCount = ReadInt32(reader)
    var players []Player
    players = make([]Player, ReadInt32(reader))
    for playersIndex := range players {
        var playersElement Player
        playersElement = ReadPlayer(reader)
        players[playersIndex] = playersElement
    }
    var planets []Planet
    planets = make([]Planet, ReadInt32(reader))
    for planetsIndex := range planets {
        var planetsElement Planet
        planetsElement = ReadPlanet(reader)
        planets[planetsIndex] = planetsElement
    }
    var flyingWorkerGroups []FlyingWorkerGroup
    flyingWorkerGroups = make([]FlyingWorkerGroup, ReadInt32(reader))
    for flyingWorkerGroupsIndex := range flyingWorkerGroups {
        var flyingWorkerGroupsElement FlyingWorkerGroup
        flyingWorkerGroupsElement = ReadFlyingWorkerGroup(reader)
        flyingWorkerGroups[flyingWorkerGroupsIndex] = flyingWorkerGroupsElement
    }
    var maxFlyingWorkerGroups int32
    maxFlyingWorkerGroups = ReadInt32(reader)
    var maxTravelDistance int32
    maxTravelDistance = ReadInt32(reader)
    var logisticsUpgrade int32
    logisticsUpgrade = ReadInt32(reader)
    var productionUpgrade int32
    productionUpgrade = ReadInt32(reader)
    var combatUpgrade int32
    combatUpgrade = ReadInt32(reader)
    var maxBuilders int32
    maxBuilders = ReadInt32(reader)
    var buildingProperties map[BuildingType]BuildingProperties
    buildingPropertiesSize := ReadInt32(reader)
    buildingProperties = make(map[BuildingType]BuildingProperties)
    for buildingPropertiesIndex := int32(0); buildingPropertiesIndex < buildingPropertiesSize; buildingPropertiesIndex++ {
        var buildingPropertiesKey BuildingType
        buildingPropertiesKey = ReadBuildingType(reader)
        var buildingPropertiesValue BuildingProperties
        buildingPropertiesValue = ReadBuildingProperties(reader)
        buildingProperties[buildingPropertiesKey] = buildingPropertiesValue
    }
    var specialtiesAllowed bool
    specialtiesAllowed = ReadBool(reader)
    var viewDistance *int32
    if ReadBool(reader) {
        var viewDistanceValue int32
        viewDistanceValue = ReadInt32(reader)
        viewDistance = &viewDistanceValue
    } else {
        viewDistance = nil
    }
    return Game {
        MyIndex: myIndex,
        CurrentTick: currentTick,
        MaxTickCount: maxTickCount,
        Players: players,
        Planets: planets,
        FlyingWorkerGroups: flyingWorkerGroups,
        MaxFlyingWorkerGroups: maxFlyingWorkerGroups,
        MaxTravelDistance: maxTravelDistance,
        LogisticsUpgrade: logisticsUpgrade,
        ProductionUpgrade: productionUpgrade,
        CombatUpgrade: combatUpgrade,
        MaxBuilders: maxBuilders,
        BuildingProperties: buildingProperties,
        SpecialtiesAllowed: specialtiesAllowed,
        ViewDistance: viewDistance,
    }
}

// Write Game to writer
func (game Game) Write(writer io.Writer) {
    myIndex := game.MyIndex
    WriteInt32(writer, myIndex)
    currentTick := game.CurrentTick
    WriteInt32(writer, currentTick)
    maxTickCount := game.MaxTickCount
    WriteInt32(writer, maxTickCount)
    players := game.Players
    WriteInt32(writer, int32(len(players)))
    for _, playersElement := range players {
        playersElement.Write(writer)
    }
    planets := game.Planets
    WriteInt32(writer, int32(len(planets)))
    for _, planetsElement := range planets {
        planetsElement.Write(writer)
    }
    flyingWorkerGroups := game.FlyingWorkerGroups
    WriteInt32(writer, int32(len(flyingWorkerGroups)))
    for _, flyingWorkerGroupsElement := range flyingWorkerGroups {
        flyingWorkerGroupsElement.Write(writer)
    }
    maxFlyingWorkerGroups := game.MaxFlyingWorkerGroups
    WriteInt32(writer, maxFlyingWorkerGroups)
    maxTravelDistance := game.MaxTravelDistance
    WriteInt32(writer, maxTravelDistance)
    logisticsUpgrade := game.LogisticsUpgrade
    WriteInt32(writer, logisticsUpgrade)
    productionUpgrade := game.ProductionUpgrade
    WriteInt32(writer, productionUpgrade)
    combatUpgrade := game.CombatUpgrade
    WriteInt32(writer, combatUpgrade)
    maxBuilders := game.MaxBuilders
    WriteInt32(writer, maxBuilders)
    buildingProperties := game.BuildingProperties
    WriteInt32(writer, int32(len(buildingProperties)))
    for buildingPropertiesKey, buildingPropertiesValue := range buildingProperties {
        WriteInt32(writer, int32(buildingPropertiesKey))
        buildingPropertiesValue.Write(writer)
    }
    specialtiesAllowed := game.SpecialtiesAllowed
    WriteBool(writer, specialtiesAllowed)
    viewDistance := game.ViewDistance
    if viewDistance == nil {
        WriteBool(writer, false)
    } else {
        WriteBool(writer, true)
        viewDistanceValue := *viewDistance
        WriteInt32(writer, viewDistanceValue)
    }
}

// Get string representation of Game
func (game Game) String() string {
    stringResult := "{ "
    stringResult += "MyIndex: "
    myIndex := game.MyIndex
    stringResult += fmt.Sprint(myIndex)
    stringResult += ", "
    stringResult += "CurrentTick: "
    currentTick := game.CurrentTick
    stringResult += fmt.Sprint(currentTick)
    stringResult += ", "
    stringResult += "MaxTickCount: "
    maxTickCount := game.MaxTickCount
    stringResult += fmt.Sprint(maxTickCount)
    stringResult += ", "
    stringResult += "Players: "
    players := game.Players
    stringResult += "[ "
    for playersIndex, playersElement := range players {
        if playersIndex != 0 {
            stringResult += ", "
        }
        stringResult += playersElement.String()
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "Planets: "
    planets := game.Planets
    stringResult += "[ "
    for planetsIndex, planetsElement := range planets {
        if planetsIndex != 0 {
            stringResult += ", "
        }
        stringResult += planetsElement.String()
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "FlyingWorkerGroups: "
    flyingWorkerGroups := game.FlyingWorkerGroups
    stringResult += "[ "
    for flyingWorkerGroupsIndex, flyingWorkerGroupsElement := range flyingWorkerGroups {
        if flyingWorkerGroupsIndex != 0 {
            stringResult += ", "
        }
        stringResult += flyingWorkerGroupsElement.String()
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "MaxFlyingWorkerGroups: "
    maxFlyingWorkerGroups := game.MaxFlyingWorkerGroups
    stringResult += fmt.Sprint(maxFlyingWorkerGroups)
    stringResult += ", "
    stringResult += "MaxTravelDistance: "
    maxTravelDistance := game.MaxTravelDistance
    stringResult += fmt.Sprint(maxTravelDistance)
    stringResult += ", "
    stringResult += "LogisticsUpgrade: "
    logisticsUpgrade := game.LogisticsUpgrade
    stringResult += fmt.Sprint(logisticsUpgrade)
    stringResult += ", "
    stringResult += "ProductionUpgrade: "
    productionUpgrade := game.ProductionUpgrade
    stringResult += fmt.Sprint(productionUpgrade)
    stringResult += ", "
    stringResult += "CombatUpgrade: "
    combatUpgrade := game.CombatUpgrade
    stringResult += fmt.Sprint(combatUpgrade)
    stringResult += ", "
    stringResult += "MaxBuilders: "
    maxBuilders := game.MaxBuilders
    stringResult += fmt.Sprint(maxBuilders)
    stringResult += ", "
    stringResult += "BuildingProperties: "
    buildingProperties := game.BuildingProperties
    stringResult += "map[ "
    buildingPropertiesIndex := 0
    for buildingPropertiesKey, buildingPropertiesValue := range buildingProperties {
        if buildingPropertiesIndex != 0 {
            stringResult += ", "
        }
        stringResult += BuildingTypeToString(buildingPropertiesKey)
        stringResult += ": "
        stringResult += buildingPropertiesValue.String()
        buildingPropertiesIndex++
    }
    stringResult += " ]"
    stringResult += ", "
    stringResult += "SpecialtiesAllowed: "
    specialtiesAllowed := game.SpecialtiesAllowed
    stringResult += fmt.Sprint(specialtiesAllowed)
    stringResult += ", "
    stringResult += "ViewDistance: "
    viewDistance := game.ViewDistance
    if viewDistance == nil {
        stringResult += "nil"
    } else {
        viewDistanceValue := *viewDistance
        stringResult += fmt.Sprint(viewDistanceValue)
    }
    stringResult += " }"
    return stringResult
}