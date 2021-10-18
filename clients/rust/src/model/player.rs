use super::*;

/// Player (game participant)
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub struct Player {
    /// Current score points
    pub score: i32,
}

impl trans::Trans for Player {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.score.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let score: i32 = trans::Trans::read_from(reader)?;
        Ok(Self {
            score,
        })
    }
}