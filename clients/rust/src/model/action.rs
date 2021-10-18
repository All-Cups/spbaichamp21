use super::*;

/// Player's actions
#[derive(Clone, Debug)]
pub struct Action {
    /// List of movement orders
    pub moves: Vec<model::MoveAction>,
    /// List of building orders
    pub buildings: Vec<model::BuildingAction>,
}

impl trans::Trans for Action {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.moves.write_to(writer)?;
        self.buildings.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let moves: Vec<model::MoveAction> = trans::Trans::read_from(reader)?;
        let buildings: Vec<model::BuildingAction> = trans::Trans::read_from(reader)?;
        Ok(Self {
            moves,
            buildings,
        })
    }
}