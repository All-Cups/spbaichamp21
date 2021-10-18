namespace SpbAiChamp.Model
{
    /// <summary>
    /// Player's specialty
    /// </summary>
    public enum Specialty
    {
        /// <summary>
        /// Logistics. Increased travel distance
        /// </summary>
        Logistics = 0,
        /// <summary>
        /// Production. Increased work speed
        /// </summary>
        Production = 1,
        /// <summary>
        /// Combat. Increased damage
        /// </summary>
        Combat = 2,
    }

    public static class SpecialtyHelper {
        /// <summary> Read Specialty from reader </summary>
        public static Specialty ReadFrom(System.IO.BinaryReader reader) {
            switch (reader.ReadInt32())
            {
                case 0:
                    return Specialty.Logistics;
                case 1:
                    return Specialty.Production;
                case 2:
                    return Specialty.Combat;
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }
    }
}