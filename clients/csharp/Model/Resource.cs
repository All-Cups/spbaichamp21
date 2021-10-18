namespace SpbAiChamp.Model
{
    /// <summary>
    /// Resource type
    /// </summary>
    public enum Resource
    {
        /// <summary>
        /// Stone
        /// </summary>
        Stone = 0,
        /// <summary>
        /// Ore
        /// </summary>
        Ore = 1,
        /// <summary>
        /// Sand
        /// </summary>
        Sand = 2,
        /// <summary>
        /// Organics
        /// </summary>
        Organics = 3,
        /// <summary>
        /// Metal
        /// </summary>
        Metal = 4,
        /// <summary>
        /// Silicon
        /// </summary>
        Silicon = 5,
        /// <summary>
        /// Plastic
        /// </summary>
        Plastic = 6,
        /// <summary>
        /// Chip
        /// </summary>
        Chip = 7,
        /// <summary>
        /// Accumulator
        /// </summary>
        Accumulator = 8,
    }

    public static class ResourceHelper {
        /// <summary> Read Resource from reader </summary>
        public static Resource ReadFrom(System.IO.BinaryReader reader) {
            switch (reader.ReadInt32())
            {
                case 0:
                    return Resource.Stone;
                case 1:
                    return Resource.Ore;
                case 2:
                    return Resource.Sand;
                case 3:
                    return Resource.Organics;
                case 4:
                    return Resource.Metal;
                case 5:
                    return Resource.Silicon;
                case 6:
                    return Resource.Plastic;
                case 7:
                    return Resource.Chip;
                case 8:
                    return Resource.Accumulator;
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }
    }
}