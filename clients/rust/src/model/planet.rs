use super::*;

/// TODO - Document
#[derive(Clone, Debug)]
pub struct Planet {
    /// TODO - Document
    pub id: i32,
    /// TODO - Document
    pub x: i32,
    /// TODO - Document
    pub y: i32,
    /// TODO - Document
    pub harvestable_resource: Option<model::Resource>,
    /// TODO - Document
    pub worker_groups: Vec<model::WorkerGroup>,
    /// TODO - Document
    pub resources: std::collections::HashMap<model::Resource, i32>,
    /// TODO - Document
    pub building: Option<model::Building>,
}

impl trans::Trans for Planet {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.id.write_to(writer)?;
        self.x.write_to(writer)?;
        self.y.write_to(writer)?;
        self.harvestable_resource.write_to(writer)?;
        self.worker_groups.write_to(writer)?;
        self.resources.write_to(writer)?;
        self.building.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let id: i32 = trans::Trans::read_from(reader)?;
        let x: i32 = trans::Trans::read_from(reader)?;
        let y: i32 = trans::Trans::read_from(reader)?;
        let harvestable_resource: Option<model::Resource> = trans::Trans::read_from(reader)?;
        let worker_groups: Vec<model::WorkerGroup> = trans::Trans::read_from(reader)?;
        let resources: std::collections::HashMap<model::Resource, i32> = trans::Trans::read_from(reader)?;
        let building: Option<model::Building> = trans::Trans::read_from(reader)?;
        Ok(Self {
            id,
            x,
            y,
            harvestable_resource,
            worker_groups,
            resources,
            building,
        })
    }
}