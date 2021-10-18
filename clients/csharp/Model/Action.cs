namespace SpbAiChamp.Model
{
    /// <summary>
    /// Player's actions
    /// </summary>
    public struct Action
    {
        /// <summary>
        /// List of movement orders
        /// </summary>
        public Model.MoveAction[] Moves { get; set; }
        /// <summary>
        /// List of building orders
        /// </summary>
        public Model.BuildingAction[] Buildings { get; set; }
        /// <summary>
        /// Choosing specialty
        /// </summary>
        public Model.Specialty? ChooseSpecialty { get; set; }
    
        public Action(Model.MoveAction[] moves, Model.BuildingAction[] buildings, Model.Specialty? chooseSpecialty)
        {
            this.Moves = moves;
            this.Buildings = buildings;
            this.ChooseSpecialty = chooseSpecialty;
        }
    
        /// <summary> Read Action from reader </summary>
        public static Action ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Action();
            result.Moves = new Model.MoveAction[reader.ReadInt32()];
            for (int movesIndex = 0; movesIndex < result.Moves.Length; movesIndex++)
            {
                result.Moves[movesIndex] = Model.MoveAction.ReadFrom(reader);
            }
            result.Buildings = new Model.BuildingAction[reader.ReadInt32()];
            for (int buildingsIndex = 0; buildingsIndex < result.Buildings.Length; buildingsIndex++)
            {
                result.Buildings[buildingsIndex] = Model.BuildingAction.ReadFrom(reader);
            }
            if (reader.ReadBoolean())
            {
                result.ChooseSpecialty = SpecialtyHelper.ReadFrom(reader);
            } else
            {
                result.ChooseSpecialty = null;
            }
            return result;
        }
    
        /// <summary> Write Action to writer </summary>
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Moves.Length);
            foreach (var movesElement in Moves)
            {
                movesElement.WriteTo(writer);
            }
            writer.Write(Buildings.Length);
            foreach (var buildingsElement in Buildings)
            {
                buildingsElement.WriteTo(writer);
            }
            if (!ChooseSpecialty.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                writer.Write((int) (ChooseSpecialty.Value));
            }
        }
    
        /// <summary> Get string representation of Action </summary>
        public override string ToString() {
            string stringResult = "Action { ";
            stringResult += "Moves: ";
            stringResult += "[ ";
            int movesIndex = 0;
            foreach (var movesElement in Moves)
            {
                if (movesIndex != 0) {
                    stringResult += ", ";
                }
                stringResult += movesElement.ToString();
                movesIndex++;
            }
            stringResult += " ]";
            stringResult += ", ";
            stringResult += "Buildings: ";
            stringResult += "[ ";
            int buildingsIndex = 0;
            foreach (var buildingsElement in Buildings)
            {
                if (buildingsIndex != 0) {
                    stringResult += ", ";
                }
                stringResult += buildingsElement.ToString();
                buildingsIndex++;
            }
            stringResult += " ]";
            stringResult += ", ";
            stringResult += "ChooseSpecialty: ";
            if (!ChooseSpecialty.HasValue)
            {
                stringResult += "null";
            } else
            {
                stringResult += ChooseSpecialty.Value.ToString();
            }
            stringResult += " }";
            return stringResult;
        }
    }
}