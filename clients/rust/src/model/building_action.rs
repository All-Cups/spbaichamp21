use super::*;

/// TODO - Document
#[derive(Clone, Debug)]
pub struct BuildingAction {
    /// TODO - Document
    pub planet: i32,
    /// TODO - Document
    pub building_type: Option<model::BuildingType>,
}

impl trans::Trans for BuildingAction {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.planet.write_to(writer)?;
        self.building_type.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let planet: i32 = trans::Trans::read_from(reader)?;
        let building_type: Option<model::BuildingType> = trans::Trans::read_from(reader)?;
        Ok(Self {
            planet,
            building_type,
        })
    }
}