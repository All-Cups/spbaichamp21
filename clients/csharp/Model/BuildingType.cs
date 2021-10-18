namespace SpbAiChamp.Model
{
    /// <summary>
    /// TODO - Document
    /// </summary>
    public enum BuildingType
    {
        /// <summary>
        /// TODO - Document
        /// </summary>
        Quarry = 0,
        /// <summary>
        /// TODO - Document
        /// </summary>
        Mines = 1,
        /// <summary>
        /// TODO - Document
        /// </summary>
        Career = 2,
        /// <summary>
        /// TODO - Document
        /// </summary>
        Farm = 3,
        /// <summary>
        /// TODO - Document
        /// </summary>
        Foundry = 4,
        /// <summary>
        /// TODO - Document
        /// </summary>
        Furnace = 5,
        /// <summary>
        /// TODO - Document
        /// </summary>
        Bioreactor = 6,
        /// <summary>
        /// TODO - Document
        /// </summary>
        ChipFactory = 7,
        /// <summary>
        /// TODO - Document
        /// </summary>
        AccumulatorFactory = 8,
        /// <summary>
        /// TODO - Document
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