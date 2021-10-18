namespace SpbAiChamp.Model
{
    /// <summary>
    /// Building type
    /// </summary>
    public enum BuildingType
    {
        /// <summary>
        /// Quarry harvests stone
        /// </summary>
        Quarry = 0,
        /// <summary>
        /// Mines harvests ore
        /// </summary>
        Mines = 1,
        /// <summary>
        /// Career harvest sand
        /// </summary>
        Career = 2,
        /// <summary>
        /// Farm harvests organics
        /// </summary>
        Farm = 3,
        /// <summary>
        /// Foundry produces metal
        /// </summary>
        Foundry = 4,
        /// <summary>
        /// Furnace produces silicon
        /// </summary>
        Furnace = 5,
        /// <summary>
        /// Bioreactor produces plastic
        /// </summary>
        Bioreactor = 6,
        /// <summary>
        /// Chip factory produces chips
        /// </summary>
        ChipFactory = 7,
        /// <summary>
        /// Accumulator factory produces accumulators
        /// </summary>
        AccumulatorFactory = 8,
        /// <summary>
        /// Replicator produces new workers
        /// </summary>
        Replicator = 9,
    }

    public static class BuildingTypeHelper {
        /// <summary> Read BuildingType from reader </summary>
        public static BuildingType ReadFrom(System.IO.BinaryReader reader) {
            switch (reader.ReadInt32())
            {
                case 0:
                    return BuildingType.Quarry;
                case 1:
                    return BuildingType.Mines;
                case 2:
                    return BuildingType.Career;
                case 3:
                    return BuildingType.Farm;
                case 4:
                    return BuildingType.Foundry;
                case 5:
                    return BuildingType.Furnace;
                case 6:
                    return BuildingType.Bioreactor;
                case 7:
                    return BuildingType.ChipFactory;
                case 8:
                    return BuildingType.AccumulatorFactory;
                case 9:
                    return BuildingType.Replicator;
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }
    }
}