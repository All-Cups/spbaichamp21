use super::*;

/// A building
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub struct Building {
    /// Building's type
    pub building_type: model::BuildingType,
    /// Current health
    pub health: i32,
    /// Amount of work done for current task
    pub work_done: i32,
}

impl trans::Trans for Building {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.building_type.write_to(writer)?;
        self.health.write_to(writer)?;
        self.work_done.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let building_type: model::BuildingType = trans::Trans::read_from(reader)?;
        let health: i32 = trans::Trans::read_from(reader)?;
        let work_done: i32 = trans::Trans::read_from(reader)?;
        Ok(Self {
            building_type,
            health,
            work_done,
        })
    }
}