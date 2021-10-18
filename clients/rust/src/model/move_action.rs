use super::*;

/// TODO - Document
#[derive(Clone, Debug)]
pub struct MoveAction {
    /// TODO - Document
    pub start_planet: i32,
    /// TODO - Document
    pub target_planet: i32,
    /// TODO - Document
    pub worker_number: i32,
    /// TODO - Document
    pub take_resource: Option<model::Resource>,
}

impl trans::Trans for MoveAction {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.start_planet.write_to(writer)?;
        self.target_planet.write_to(writer)?;
        self.worker_number.write_to(writer)?;
        self.take_resource.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let start_planet: i32 = trans::Trans::read_from(reader)?;
        let target_planet: i32 = trans::Trans::read_from(reader)?;
        let worker_number: i32 = trans::Trans::read_from(reader)?;
        let take_resource: Option<model::Resource> = trans::Trans::read_from(reader)?;
        Ok(Self {
            start_planet,
            target_planet,
            worker_number,
            take_resource,
        })
    }
}