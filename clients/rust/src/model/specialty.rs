use super::*;

/// Player's specialty
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub enum Specialty {
    /// Logistics. Increased travel distance
    Logistics,
    /// Production. Increased work speed
    Production,
    /// Combat. Increased damage
    Combat,
}

impl trans::Trans for Specialty {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        let tag: i32 = match self {
            Self::Logistics => 0,
            Self::Production => 1,
            Self::Combat => 2,
        };
        trans::Trans::write_to(&tag, writer)
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let tag = <i32 as trans::Trans>::read_from(reader)?;
        match tag {
            0 => Ok(Self::Logistics),
            1 => Ok(Self::Production),
            2 => Ok(Self::Combat),
            _ => Err(std::io::Error::new(
                std::io::ErrorKind::Other,
                format!("Unexpected tag {:?}", tag))),
        }
    }
}