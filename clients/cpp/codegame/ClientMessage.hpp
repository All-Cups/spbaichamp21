#ifndef __MODEL_CLIENT_MESSAGE_HPP__
#define __MODEL_CLIENT_MESSAGE_HPP__

#include "Stream.hpp"
#include "debugging/DebugCommand.hpp"
#include "model/Action.hpp"
#include "model/BuildingAction.hpp"
#include "model/BuildingType.hpp"
#include "model/DebugData.hpp"
#include "model/MoveAction.hpp"
#include "model/Resource.hpp"
#include <memory>
#include <optional>
#include <sstream>
#include <stdexcept>
#include <string>
#include <vector>

namespace codegame {

// Message sent from client
class ClientMessage {
public:
    // Ask app to perform new debug command
    class DebugMessage;
    // Reply for ServerMessage::GetAction
    class ActionMessage;
    // Signifies finish of the debug update
    class DebugUpdateDone;
    // Request debug state from the app
    class RequestDebugState;

    // Read ClientMessage from input stream
    static std::shared_ptr<ClientMessage> readFrom(InputStream& stream);

    // Write ClientMessage to output stream
    virtual void writeTo(OutputStream& stream) const = 0;

    // Get string representation of ClientMessage
    virtual std::string toString() const = 0;
};

// Ask app to perform new debug command
class ClientMessage::DebugMessage : public ClientMessage {
public:
    static const int TAG = 0;

    // Command to perform
    std::shared_ptr<debugging::DebugCommand> command;

    DebugMessage(std::shared_ptr<debugging::DebugCommand> command);

    // Read DebugMessage from input stream
    static DebugMessage readFrom(InputStream& stream);

    // Write DebugMessage to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of DebugMessage
    std::string toString() const;
};

// Reply for ServerMessage::GetAction
class ClientMessage::ActionMessage : public ClientMessage {
public:
    static const int TAG = 1;

    // Player's action
    model::Action action;

    ActionMessage(model::Action action);

    // Read ActionMessage from input stream
    static ActionMessage readFrom(InputStream& stream);

    // Write ActionMessage to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of ActionMessage
    std::string toString() const;
};

// Signifies finish of the debug update
class ClientMessage::DebugUpdateDone : public ClientMessage {
public:
    static const int TAG = 2;


    DebugUpdateDone();

    // Read DebugUpdateDone from input stream
    static DebugUpdateDone readFrom(InputStream& stream);

    // Write DebugUpdateDone to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of DebugUpdateDone
    std::string toString() const;

    bool operator ==(const DebugUpdateDone& other) const;
};

// Request debug state from the app
class ClientMessage::RequestDebugState : public ClientMessage {
public:
    static const int TAG = 3;


    RequestDebugState();

    // Read RequestDebugState from input stream
    static RequestDebugState readFrom(InputStream& stream);

    // Write RequestDebugState to output stream
    void writeTo(OutputStream& stream) const;

    // Get string representation of RequestDebugState
    std::string toString() const;

    bool operator ==(const RequestDebugState& other) const;
};

}

#endif