use super::*;

/// Message sent from server
#[derive(Clone, Debug)]
pub enum ServerMessage {
    /// Get action for next tick
    GetAction {
        /// Player's view
        player_view: model::Game,
        /// Whether app is running with debug interface available
        debug_available: bool,
    },
    /// Signifies end of the game
    Finish {
    },
    /// Debug update
    DebugUpdate {
        /// Player's view
        player_view: model::Game,
    },
}

impl trans::Trans for ServerMessage {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        match self {
            Self::GetAction {
                player_view,
                debug_available,
            } => {
                <i32 as trans::Trans>::write_to(&0, writer)?;
                player_view.write_to(writer)?;
                debug_available.write_to(writer)?;
            }
            Self::Finish {
            } => {
                <i32 as trans::Trans>::write_to(&1, writer)?;
            }
            Self::DebugUpdate {
                player_view,
            } => {
                <i32 as trans::Trans>::write_to(&2, writer)?;
                player_view.write_to(writer)?;
            }
        }
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let tag = <i32 as trans::Trans>::read_from(reader)?;
        match tag {
            0 => {
                let player_view: model::Game = trans::Trans::read_from(reader)?;
                let debug_available: bool = trans::Trans::read_from(reader)?;
                Ok(Self::GetAction {
                    player_view,
                    debug_available,
                })
            }
            1 => {
                Ok(Self::Finish {
                })
            }
            2 => {
                let player_view: model::Game = trans::Trans::read_from(reader)?;
                Ok(Self::DebugUpdate {
                    player_view,
                })
            }
            _ => Err(std::io::Error::new(
                std::io::ErrorKind::Other,
                format!("Unexpected tag {:?}", tag))),
        }
    }
}