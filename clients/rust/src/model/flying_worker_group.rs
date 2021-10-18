use super::*;

/// A flying worker group
#[derive(Clone, Debug)]
pub struct FlyingWorkerGroup {
    /// Index of player controlling workers
    pub player_index: i32,
    /// Number of workers in the group
    pub number: i32,
    /// Tick when workers left previous planet on their path
    pub departure_tick: i32,
    /// Id of the previous planet on the path
    pub departure_planet: i32,
    /// Tick when workers will arrive to the next planet in their path
    pub next_planet_arrival_tick: i32,
    /// Id of the next planet in the path
    pub next_planet: i32,
    /// Id of the target planet
    pub target_planet: i32,
    /// Resource that workers are carrying
    pub resource: Option<model::Resource>,
}

impl trans::Trans for FlyingWorkerGroup {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.player_index.write_to(writer)?;
        self.number.write_to(writer)?;
        self.departure_tick.write_to(writer)?;
        self.departure_planet.write_to(writer)?;
        self.next_planet_arrival_tick.write_to(writer)?;
        self.next_planet.write_to(writer)?;
        self.target_planet.write_to(writer)?;
        self.resource.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let player_index: i32 = trans::Trans::read_from(reader)?;
        let number: i32 = trans::Trans::read_from(reader)?;
        let departure_tick: i32 = trans::Trans::read_from(reader)?;
        let departure_planet: i32 = trans::Trans::read_from(reader)?;
        let next_planet_arrival_tick: i32 = trans::Trans::read_from(reader)?;
        let next_planet: i32 = trans::Trans::read_from(reader)?;
        let target_planet: i32 = trans::Trans::read_from(reader)?;
        let resource: Option<model::Resource> = trans::Trans::read_from(reader)?;
        Ok(Self {
            player_index,
            number,
            departure_tick,
            departure_planet,
            next_planet_arrival_tick,
            next_planet,
            target_planet,
            resource,
        })
    }
}