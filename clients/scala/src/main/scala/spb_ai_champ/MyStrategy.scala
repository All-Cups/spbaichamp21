import spb_ai_champ.model._

class MyStrategy {
  def getAction(game: Game): Action = Action(Seq.empty[MoveAction], Seq.empty[BuildingAction], None)
}