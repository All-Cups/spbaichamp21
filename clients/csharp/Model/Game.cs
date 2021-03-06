namespace SpbAiChamp.Model
{
    /// <summary>
    /// Current game's state
    /// </summary>
    public struct Game
    {
        /// <summary>
        /// Your player's index
        /// </summary>
        public int MyIndex { get; set; }
        /// <summary>
        /// Current tick number
        /// </summary>
        public int CurrentTick { get; set; }
        /// <summary>
        /// Max number of ticks in the game
        /// </summary>
        public int MaxTickCount { get; set; }
        /// <summary>
        /// List of players
        /// </summary>
        public Model.Player[] Players { get; set; }
        /// <summary>
        /// List of planets
        /// </summary>
        public Model.Planet[] Planets { get; set; }
        /// <summary>
        /// List of flying worker groups
        /// </summary>
        public Model.FlyingWorkerGroup[] FlyingWorkerGroups { get; set; }
        /// <summary>
        /// Max number of flying worker groups for one player
        /// </summary>
        public int MaxFlyingWorkerGroups { get; set; }
        /// <summary>
        /// Max distance of direct travel between planets
        /// </summary>
        public int MaxTravelDistance { get; set; }
        /// <summary>
        /// Additional distance of direct travel between planets for player with Logistics specialty
        /// </summary>
        public int LogisticsUpgrade { get; set; }
        /// <summary>
        /// Additional work done by player with Production specialty (in percent)
        /// </summary>
        public int ProductionUpgrade { get; set; }
        /// <summary>
        /// Additional strength workers for player with Combat specialty (in percent)
        /// </summary>
        public int CombatUpgrade { get; set; }
        /// <summary>
        /// Max number of workers performing building on one planet
        /// </summary>
        public int MaxBuilders { get; set; }
        /// <summary>
        /// Properties of every building type
        /// </summary>
        public System.Collections.Generic.IDictionary<Model.BuildingType, Model.BuildingProperties> BuildingProperties { get; set; }
        /// <summary>
        /// Whether choosing specialties is allowed
        /// </summary>
        public bool SpecialtiesAllowed { get; set; }
        /// <summary>
        /// View distance
        /// </summary>
        public int? ViewDistance { get; set; }
    
        public Game(int myIndex, int currentTick, int maxTickCount, Model.Player[] players, Model.Planet[] planets, Model.FlyingWorkerGroup[] flyingWorkerGroups, int maxFlyingWorkerGroups, int maxTravelDistance, int logisticsUpgrade, int productionUpgrade, int combatUpgrade, int maxBuilders, System.Collections.Generic.IDictionary<Model.BuildingType, Model.BuildingProperties> buildingProperties, bool specialtiesAllowed, int? viewDistance)
        {
            this.MyIndex = myIndex;
            this.CurrentTick = currentTick;
            this.MaxTickCount = maxTickCount;
            this.Players = players;
            this.Planets = planets;
            this.FlyingWorkerGroups = flyingWorkerGroups;
            this.MaxFlyingWorkerGroups = maxFlyingWorkerGroups;
            this.MaxTravelDistance = maxTravelDistance;
            this.LogisticsUpgrade = logisticsUpgrade;
            this.ProductionUpgrade = productionUpgrade;
            this.CombatUpgrade = combatUpgrade;
            this.MaxBuilders = maxBuilders;
            this.BuildingProperties = buildingProperties;
            this.SpecialtiesAllowed = specialtiesAllowed;
            this.ViewDistance = viewDistance;
        }
    
        /// <summary> Read Game from reader </summary>
        public static Game ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Game();
            result.MyIndex = reader.ReadInt32();
            result.CurrentTick = reader.ReadInt32();
            result.MaxTickCount = reader.ReadInt32();
            result.Players = new Model.Player[reader.ReadInt32()];
            for (int playersIndex = 0; playersIndex < result.Players.Length; playersIndex++)
            {
                result.Players[playersIndex] = Model.Player.ReadFrom(reader);
            }
            result.Planets = new Model.Planet[reader.ReadInt32()];
            for (int planetsIndex = 0; planetsIndex < result.Planets.Length; planetsIndex++)
            {
                result.Planets[planetsIndex] = Model.Planet.ReadFrom(reader);
            }
            result.FlyingWorkerGroups = new Model.FlyingWorkerGroup[reader.ReadInt32()];
            for (int flyingWorkerGroupsIndex = 0; flyingWorkerGroupsIndex < result.FlyingWorkerGroups.Length; flyingWorkerGroupsIndex++)
            {
                result.FlyingWorkerGroups[flyingWorkerGroupsIndex] = Model.FlyingWorkerGroup.ReadFrom(reader);
            }
            result.MaxFlyingWorkerGroups = reader.ReadInt32();
            result.MaxTravelDistance = reader.ReadInt32();
            result.LogisticsUpgrade = reader.ReadInt32();
            result.ProductionUpgrade = reader.ReadInt32();
            result.CombatUpgrade = reader.ReadInt32();
            result.MaxBuilders = reader.ReadInt32();
            int buildingPropertiesSize = reader.ReadInt32();
            result.BuildingProperties = new System.Collections.Generic.Dictionary<Model.BuildingType, Model.BuildingProperties>(buildingPropertiesSize);
            for (int buildingPropertiesIndex = 0; buildingPropertiesIndex < buildingPropertiesSize; buildingPropertiesIndex++)
            {
                Model.BuildingType buildingPropertiesKey;
                Model.BuildingProperties buildingPropertiesValue;
                buildingPropertiesKey = BuildingTypeHelper.ReadFrom(reader);
                buildingPropertiesValue = Model.BuildingProperties.ReadFrom(reader);
                result.BuildingProperties.Add(buildingPropertiesKey, buildingPropertiesValue);
            }
            result.SpecialtiesAllowed = reader.ReadBoolean();
            if (reader.ReadBoolean())
            {
                result.ViewDistance = reader.ReadInt32();
            } else
            {
                result.ViewDistance = null;
            }
            return result;
        }
    
        /// <summary> Write Game to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(MyIndex);
            writer.Write(CurrentTick);
            writer.Write(MaxTickCount);
            writer.Write(Players.Length);
            foreach (var playersElement in Players)
            {
                playersElement.WriteTo(writer);
            }
            writer.Write(Planets.Length);
            foreach (var planetsElement in Planets)
            {
                planetsElement.WriteTo(writer);
            }
            writer.Write(FlyingWorkerGroups.Length);
            foreach (var flyingWorkerGroupsElement in FlyingWorkerGroups)
            {
                flyingWorkerGroupsElement.WriteTo(writer);
            }
            writer.Write(MaxFlyingWorkerGroups);
            writer.Write(MaxTravelDistance);
            writer.Write(LogisticsUpgrade);
            writer.Write(ProductionUpgrade);
            writer.Write(CombatUpgrade);
            writer.Write(MaxBuilders);
            writer.Write(BuildingProperties.Count);
            foreach (var buildingPropertiesEntry in BuildingProperties)
            {
                var buildingPropertiesKey = buildingPropertiesEntry.Key;
                var buildingPropertiesValue = buildingPropertiesEntry.Value;
                writer.Write((int) (buildingPropertiesKey));
                buildingPropertiesValue.WriteTo(writer);
            }
            writer.Write(SpecialtiesAllowed);
            if (!ViewDistance.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write(ViewDistance.Value);
            }
        }
    
        /// <summary> Get string representation of Game </summary>
        public override string ToString() {
            string stringResult = "Game { ";
            stringResult += "MyIndex: ";
            stringResult += MyIndex.ToString();
            stringResult += ", ";
            stringResult += "CurrentTick: ";
            stringResult += CurrentTick.ToString();
            stringResult += ", ";
            stringResult += "MaxTickCount: ";
            stringResult += MaxTickCount.ToString();
            stringResult += ", ";
            stringResult += "Players: ";
            stringResult += "[ ";
            int playersIndex = 0;
            foreach (var playersElement in Players)
            {
                if (playersIndex != 0) {
                    stringResult += ", ";
                }
                stringResult += playersElement.ToString();
                playersIndex++;
            }
            stringResult += " ]";
            stringResult += ", ";
            stringResult += "Planets: ";
            stringResult += "[ ";
            int planetsIndex = 0;
            foreach (var planetsElement in Planets)
            {
                if (planetsIndex != 0) {
                    stringResult += ", ";
                }
                stringResult += planetsElement.ToString();
                planetsIndex++;
            }
            stringResult += " ]";
            stringResult += ", ";
            stringResult += "FlyingWorkerGroups: ";
            stringResult += "[ ";
            int flyingWorkerGroupsIndex = 0;
            foreach (var flyingWorkerGroupsElement in FlyingWorkerGroups)
            {
                if (flyingWorkerGroupsIndex != 0) {
                    stringResult += ", ";
                }
                stringResult += flyingWorkerGroupsElement.ToString();
                flyingWorkerGroupsIndex++;
            }
            stringResult += " ]";
            stringResult += ", ";
            stringResult += "MaxFlyingWorkerGroups: ";
            stringResult += MaxFlyingWorkerGroups.ToString();
            stringResult += ", ";
            stringResult += "MaxTravelDistance: ";
            stringResult += MaxTravelDistance.ToString();
            stringResult += ", ";
            stringResult += "LogisticsUpgrade: ";
            stringResult += LogisticsUpgrade.ToString();
            stringResult += ", ";
            stringResult += "ProductionUpgrade: ";
            stringResult += ProductionUpgrade.ToString();
            stringResult += ", ";
            stringResult += "CombatUpgrade: ";
            stringResult += CombatUpgrade.ToString();
            stringResult += ", ";
            stringResult += "MaxBuilders: ";
            stringResult += MaxBuilders.ToString();
            stringResult += ", ";
            stringResult += "BuildingProperties: ";
            stringResult += "{ ";
            int buildingPropertiesIndex = 0;
            foreach (var buildingPropertiesEntry in BuildingProperties)
            {
                if (buildingPropertiesIndex != 0) {
                    stringResult += ", ";
                }
                var buildingPropertiesKey = buildingPropertiesEntry.Key;
                stringResult += buildingPropertiesKey.ToString();
                stringResult += ": ";
                var buildingPropertiesValue = buildingPropertiesEntry.Value;
                stringResult += buildingPropertiesValue.ToString();
                buildingPropertiesIndex++;
            }
            stringResult += " }";
            stringResult += ", ";
            stringResult += "SpecialtiesAllowed: ";
            stringResult += SpecialtiesAllowed.ToString();
            stringResult += ", ";
            stringResult += "ViewDistance: ";
            if (!ViewDistance.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += ViewDistance.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}