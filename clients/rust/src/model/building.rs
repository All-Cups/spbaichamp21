use super::*;

/// TODO - Document
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub struct Building {
    /// TODO - Document
    pub building_type: model::BuildingType,
    /// TODO - Document
    pub health: i32,
    /// TODO - Document
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