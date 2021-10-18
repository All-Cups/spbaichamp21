#include "ServerMessage.hpp"
#include <stdexcept>

namespace codegame {

ServerMessage::GetAction::GetAction(model::Game playerView, bool debugAvailable) : playerView(playerView), debugAvailable(debugAvailable) { }

// Read GetAction from input stream
ServerMessage::GetAction ServerMessage::GetAction::readFrom(InputStream& stream) {
    model::Game playerView = model::Game::readFrom(stream);
    bool debugAvailable = stream.readBool();
    return ServerMessage::GetAction(playerView, debugAvailable);
}

// Write GetAction to output stream
void ServerMessage::GetAction::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    playerView.writeTo(stream);
    stream.write(debugAvailable);
}

// Get string representation of GetAction
std::string ServerMessage::GetAction::toString() const {
    std::stringstream ss;
    ss << "ServerMessage::GetAction { ";
    ss << "playerView: ";
    ss << playerView.toString();
    ss << ", ";
    ss << "debugAvailable: ";
    ss << debugAvailable;
    ss << " }";
    return ss.str();
}

ServerMessage::Finish::Finish() { }

// Read Finish from input stream
ServerMessage::Finish ServerMessage::Finish::readFrom(InputStream& stream) {
    return ServerMessage::Finish();
}

// Write Finish to output stream
void ServerMessage::Finish::writeTo(OutputStream& stream) const {
    stream.write(TAG);
}

// Get string representation of Finish
std::string ServerMessage::Finish::toString() const {
    std::stringstream ss;
    ss << "ServerMessage::Finish { ";
    ss << " }";
    return ss.str();
}

bool ServerMessage::Finish::operator ==(const ServerMessage::Finish& other) const {
    return true;
}

ServerMessage::DebugUpdate::DebugUpdate(model::Game playerView) : playerView(playerView) { }

// Read DebugUpdate from input stream
ServerMessage::DebugUpdate ServerMessage::DebugUpdate::readFrom(InputStream& stream) {
    model::Game playerView = model::Game::readFrom(stream);
    return ServerMessage::DebugUpdate(playerView);
}

// Write DebugUpdate to output stream
void ServerMessage::DebugUpdate::writeTo(OutputStream& stream) const {
    stream.write(TAG);
    playerView.writeTo(stream);
}

// Get string representation of DebugUpdate
std::string ServerMessage::DebugUpdate::toString() const {
    std::stringstream ss;
    ss << "ServerMessage::DebugUpdate { ";
    ss << "playerView: ";
    ss << playerView.toString();
    ss << " }";
    return ss.str();
}

// Read ServerMessage from input stream
std::shared_ptr<ServerMessage> ServerMessage::readFrom(InputStream& stream) {
    switch (stream.readInt()) {
    case 0:
        return std::shared_ptr<ServerMessage::GetAction>(new ServerMessage::GetAction(ServerMessage::GetAction::readFrom(stream)));
    case 1:
        return std::shared_ptr<ServerMessage::Finish>(new ServerMessage::Finish(ServerMessage::Finish::readFrom(stream)));
    case 2:
        return std::shared_ptr<ServerMessage::DebugUpdate>(new ServerMessage::DebugUpdate(ServerMessage::DebugUpdate::readFrom(stream)));
    default:
        throw std::runtime_error("Unexpected tag value");
    }
}

}