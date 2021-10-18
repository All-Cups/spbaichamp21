use super::*;

/// TODO - Document
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub struct WorkerGroup {
    /// TODO - Document
    pub player_index: i32,
    /// TODO - Document
    pub number: i32,
}

impl trans::Trans for WorkerGroup {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.player_index.write_to(writer)?;
        self.number.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let player_index: i32 = trans::Trans::read_from(reader)?;
        let number: i32 = trans::Trans::read_from(reader)?;
        Ok(Self {
            player_index,
            number,
        })
    }
}