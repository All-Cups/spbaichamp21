use super::*;

/// Building type
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub enum BuildingType {
    /// Quarry harvests stone
    Quarry,
    /// Mines harvests ore
    Mines,
    /// Career harvest sand
    Career,
    /// Farm harvests organics
    Farm,
    /// Foundry produces metal
    Foundry,
    /// Furnace produces silicon
    Furnace,
    /// Bioreactor produces plastic
    Bioreactor,
    /// Chip factory produces chips
    ChipFactory,
    /// Accumulator factory produces accumulators
    AccumulatorFactory,
    /// Replicator produces new workers
    Replicator,
}

impl trans::Trans for BuildingType {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        let tag: i32 = match self {
            Self::Quarry => 0,
            Self::Mines => 1,
            Self::Career => 2,
            Self::Farm => 3,
            Self::Foundry => 4,
            Self::Furnace => 5,
            Self::Bioreactor => 6,
            Self::ChipFactory => 7,
            Self::AccumulatorFactory => 8,
            Self::Replicator => 9,
        };
        trans::Trans::write_to(&tag, writer)
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        let tag = <i32 as trans::Trans>::read_from(reader)?;
        match tag {
            0 => Ok(Self::Quarry),
            1 => Ok(Self::Mines),
            2 => Ok(Self::Career),
            3 => Ok(Self::Farm),
            4 => Ok(Self::Foundry),
            5 => Ok(Self::Furnace),
            6 => Ok(Self::Bioreactor),
            7 => Ok(Self::ChipFactory),
            8 => Ok(Self::AccumulatorFactory),
            9 => Ok(Self::Replicator),
            _ => Err(std::io::Error::new(
                std::io::ErrorKind::Other,
                format!("Unexpected tag {:?}", tag))),
        }
    }
}