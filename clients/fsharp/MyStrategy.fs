namespace SpbAiChamp

open SpbAiChamp.Model

type MyStrategy() =
    member this.getAction(game: Game): Action = {
        Moves = [||]
        Buildings = [||]
        ChooseSpecialty = None
    }