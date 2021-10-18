use super::*;

/// TODO - Document
#[derive(Clone, Debug)]
pub struct BuildingProperties {
    /// TODO - Document
    pub build_resources: std::collections::HashMap<model::Resource, i32>,
    /// TODO - Document
    pub max_health: i32,
    /// TODO - Document
    pub max_workers: i32,
    /// TODO - Document
    pub work_resources: std::collections::HashMap<model::Resource, i32>,
    /// TODO - Document
    pub produce_worker: bool,
    /// TODO - Document
    pub produce_resource: Option<model::Resource>,
    /// TODO - Document
    pub produce_amount: i32,
    /// TODO - Document
    pub produce_score: i32,
    /// TODO - Document
    pub harvest: bool,
    /// TODO - Document
    pub work_amount: i32,
}

impl trans::Trans for BuildingProperties {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.build_resources.write_to(writer)?;
        self.max_health.write_to(writer)?;
        self.max_workers.write_to(writer)?;
        self.work_resources.write_to(writer)?;
        self.produce_worker.write_to(writer)?;
        self.produce_resource.write_to(writer)?;
        self.produce_amount.write_to(writer)?;
        self.produce_score.write_to(writer)?;
        self.harvest.write_to(writer)?;
        self.work_amount.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let build_resources: std::collections::HashMap<model::Resource, i32> = trans::Trans::read_from(reader)?;
        let max_health: i32 = trans::Trans::read_from(reader)?;
        let max_workers: i32 = trans::Trans::read_from(reader)?;
        let work_resources: std::collections::HashMap<model::Resource, i32> = trans::Trans::read_from(reader)?;
        let produce_worker: bool = trans::Trans::read_from(reader)?;
        let produce_resource: Option<model::Resource> = trans::Trans::read_from(reader)?;
        let produce_amount: i32 = trans::Trans::read_from(reader)?;
        let produce_score: i32 = trans::Trans::read_from(reader)?;
        let harvest: bool = trans::Trans::read_from(reader)?;
        let work_amount: i32 = trans::Trans::read_from(reader)?;
        Ok(Self {
            build_resources,
            max_health,
            max_workers,
            work_resources,
            produce_worker,
            produce_resource,
            produce_amount,
            produce_score,
            harvest,
            work_amount,
        })
    }
}