use super::*;

/// TODO - Document
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
pub struct DebugData {
}

impl trans::Trans for DebugData {
    fn write_to(&self, writer: &mut dyn std::io::Write) -> std::io::Result<()> {
        Ok(())
    }
    fn read_from(reader: &mut dyn std::io::Read) -> std::io::Result<Self> {
        Ok(Self {
        })
    }
}