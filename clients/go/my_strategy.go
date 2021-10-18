package main

import (
    . "spb_ai_champ/model"
)

type MyStrategy struct{}

func NewMyStrategy() MyStrategy {
    return MyStrategy{}
}

func (strategy MyStrategy) getAction(game Game) Action {
    return Action{
        Moves: make([]MoveAction, 0),
        Buildings: make([]BuildingAction, 0),
    }
}