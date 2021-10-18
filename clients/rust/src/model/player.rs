use super::*;

/// Player (game participant)
#[derive(Clone, Debug)]
pub struct Player {
    /// Team index
    pub team_index: i32,
    /// Current score points
    pub score: i32,
    /// Player's specialty
    pub specialty: Option<model::Specialty>,
}

impl trans::Trans for Player {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.team_index.write_to(writer)?;
        self.score.write_to(writer)?;
        self.specialty.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let team_index: i32 = trans::Trans::read_from(reader)?;
        let score: i32 = trans::Trans::read_from(reader)?;
        let specialty: Option<model::Specialty> = trans::Trans::read_from(reader)?;
        Ok(Self {
            team_index,
            score,
            specialty,
        })
    }
}