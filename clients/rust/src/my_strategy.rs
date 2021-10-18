use spb_ai_champ::model::*;

pub struct MyStrategy {}

impl MyStrategy {
    pub fn new() -> Self {
        Self {}
    }
    pub fn get_action(&mut self, game: &Game) -> Action {
        Action {
            moves: vec![],
            buildings: vec![],
            choose_specialty: None,
        }
    }
}
