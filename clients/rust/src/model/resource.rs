use super::*;

/// TODO - Document
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub enum Resource {
    /// TODO - Document
    Stone,
    /// TODO - Document
    Ore,
    /// TODO - Document
    Sand,
    /// TODO - Document
    Organics,
    /// TODO - Document
    Metal,
    /// TODO - Document
    Silicon,
    /// TODO - Document
    Plastic,
    /// TODO - Document
    Chip,
    /// TODO - Document
    Accumulator,
}

impl trans::Trans for Resource {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        let tag: i32 = match self {
            Self::Stone => 0,
            Self::Ore => 1,
            Self::Sand => 2,
            Self::Organics => 3,
            Self::Metal => 4,
            Self::Silicon => 5,
            Self::Plastic => 6,
            Self::Chip => 7,
            Self::Accumulator => 8,
        };
        trans::Trans::write_to(&tag, writer)
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let tag = <i32 as trans::Trans>::read_from(reader)?;
        match tag {
            0 => Ok(Self::Stone),
            1 => Ok(Self::Ore),
            2 => Ok(Self::Sand),
            3 => Ok(Self::Organics),
            4 => Ok(Self::Metal),
            5 => Ok(Self::Silicon),
            6 => Ok(Self::Plastic),
            7 => Ok(Self::Chip),
            8 => Ok(Self::Accumulator),
            _ => Err(std::io::Error::new(
                std::io::ErrorKind::Other,
                format!("Unexpected tag {:?}", tag))),
        }
    }
}