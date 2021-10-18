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
    /// Additional distance of direct travel between planets for player with Logistics specialty
    pub logistics_upgrade: i32,
    /// Additional work done by player with Production specialty (in percent)
    pub production_upgrade: i32,
    /// Additional strength workers for player with Combat specialty (in percent)
    pub combat_upgrade: i32,
    /// Max number of workers performing building on one planet
    pub max_builders: i32,
    /// Properties of every building type
    pub building_properties: std::collections::HashMap<model::BuildingType, model::BuildingProperties>,
    /// Whether choosing specialties is allowed
    pub specialties_allowed: bool,
    /// View distance
    pub view_distance: Option<i32>,
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
        self.logistics_upgrade.write_to(writer)?;
        self.production_upgrade.write_to(writer)?;
        self.combat_upgrade.write_to(writer)?;
        self.max_builders.write_to(writer)?;
        self.building_properties.write_to(writer)?;
        self.specialties_allowed.write_to(writer)?;
        self.view_distance.write_to(writer)?;
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
        let logistics_upgrade: i32 = trans::Trans::read_from(reader)?;
        let production_upgrade: i32 = trans::Trans::read_from(reader)?;
        let combat_upgrade: i32 = trans::Trans::read_from(reader)?;
        let max_builders: i32 = trans::Trans::read_from(reader)?;
        let building_properties: std::collections::HashMap<model::BuildingType, model::BuildingProperties> = trans::Trans::read_from(reader)?;
        let specialties_allowed: bool = trans::Trans::read_from(reader)?;
        let view_distance: Option<i32> = trans::Trans::read_from(reader)?;
        Ok(Self {
            my_index,
            current_tick,
            max_tick_count,
            players,
            planets,
            flying_worker_groups,
            max_flying_worker_groups,
            max_travel_distance,
            logistics_upgrade,
            production_upgrade,
            combat_upgrade,
            max_builders,
            building_properties,
            specialties_allowed,
            view_distance,
        })
    }
}