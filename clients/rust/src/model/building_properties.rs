use super::*;

/// Building properties
#[derive(Clone, Debug)]
pub struct BuildingProperties {
    /// Building type that this building can be upgraded from
    pub base_building: Option<model::BuildingType>,
    /// Resources required for building
    pub build_resources: std::collections::HashMap<model::Resource, i32>,
    /// Max health points of the building
    pub max_health: i32,
    /// Max number of workers in the building
    pub max_workers: i32,
    /// Resources required to start another task
    pub work_resources: std::collections::HashMap<model::Resource, i32>,
    /// Whether performing a task spawn new workers
    pub produce_worker: bool,
    /// Resource produced when performing a task
    pub produce_resource: Option<model::Resource>,
    /// Amount of resources/workers produced when performing one task
    pub produce_amount: i32,
    /// Score points given for performing one task
    pub produce_score: i32,
    /// Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
    pub harvest: bool,
    /// Amount of work needed to finish one task
    pub work_amount: i32,
}

impl trans::Trans for BuildingProperties {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.base_building.write_to(writer)?;
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
        let base_building: Option<model::BuildingType> = trans::Trans::read_from(reader)?;
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
            base_building,
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