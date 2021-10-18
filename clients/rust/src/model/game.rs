use super::*;

/// Current game's state
#[derive(Clone, Debug)]
pub struct Game {
    /// Your player's index
    pub my_index: i32,
    /// Current tick number
    pub current_tick: i32,
    /// Max number of ticks in the game
    pub max_tick_count: i32,
    /// List of players
    pub players: Vec<model::Player>,
    /// List of planets
    pub planets: Vec<model::Planet>,
    /// List of flying worker groups
    pub flying_worker_groups: Vec<model::FlyingWorkerGroup>,
    /// Max number of flying worker groups for one player
    pub max_flying_worker_groups: i32,
    /// Max distance of direct travel between planets
    pub max_travel_distance: i32,
    /// Max number of workers performing building on one planet
    pub max_builders: i32,
    /// Properties of every building type
    pub building_properties: std::collections::HashMap<model::BuildingType, model::BuildingProperties>,
}

impl trans::Trans for Game {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        self.my_index.write_to(writer)?;
        self.current_tick.write_to(writer)?;
        self.max_tick_count.write_to(writer)?;
        self.players.write_to(writer)?;
        self.planets.write_to(writer)?;
        self.flying_worker_groups.write_to(writer)?;
        self.max_flying_worker_groups.write_to(writer)?;
        self.max_travel_distance.write_to(writer)?;
        self.max_builders.write_to(writer)?;
        self.building_properties.write_to(writer)?;
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let my_index: i32 = trans::Trans::read_from(reader)?;
        let current_tick: i32 = trans::Trans::read_from(reader)?;
        let max_tick_count: i32 = trans::Trans::read_from(reader)?;
        let players: Vec<model::Player> = trans::Trans::read_from(reader)?;
        let planets: Vec<model::Planet> = trans::Trans::read_from(reader)?;
        let flying_worker_groups: Vec<model::FlyingWorkerGroup> = trans::Trans::read_from(reader)?;
        let max_flying_worker_groups: i32 = trans::Trans::read_from(reader)?;
        let max_travel_distance: i32 = trans::Trans::read_from(reader)?;
        let max_builders: i32 = trans::Trans::read_from(reader)?;
        let building_properties: std::collections::HashMap<model::BuildingType, model::BuildingProperties> = trans::Trans::read_from(reader)?;
        Ok(Self {
            my_index,
            current_tick,
            max_tick_count,
            players,
            planets,
            flying_worker_groups,
            max_flying_worker_groups,
            max_travel_distance,
            max_builders,
            building_properties,
        })
    }
}