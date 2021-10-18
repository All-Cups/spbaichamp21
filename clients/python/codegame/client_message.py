from debugging.debug_command import DebugCommand
from model.action import Action
from stream_wrapper import StreamWrapper

class ClientMessage:
    """Message sent from client"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "ClientMessage":
        """Read ClientMessage from input stream
        """
        tag = stream.read_int()
        if tag == DebugMessage.TAG:
            return ClientMessage.DebugMessage.read_from(stream)
        if tag == ActionMessage.TAG:
            return ClientMessage.ActionMessage.read_from(stream)
        if tag == DebugUpdateDone.TAG:
            return ClientMessage.DebugUpdateDone.read_from(stream)
        if tag == RequestDebugState.TAG:
            return ClientMessage.RequestDebugState.read_from(stream)
        raise Exception("Unexpected tag value")

class DebugMessage(ClientMessage):
    """Ask app to perform new debug command"""

    TAG = 0

    __slots__ = ("command",)

    command: DebugCommand

    def __init__(self, command: DebugCommand):
        self.command = command
        """Command to perform"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "DebugMessage":
        """Read DebugMessage from input stream
        """
        command = DebugCommand.read_from(stream)
        return DebugMessage(command)
    
    def write_to(self, stream: StreamWrapper):
        """Write DebugMessage to output stream
        """
        stream.write_int(self.TAG)
        self.command.write_to(stream)
    
    def __repr__(self):
        return "DebugMessage(" + \
            repr(self.command) + \
            ")"

ClientMessage.DebugMessage = DebugMessage

class ActionMessage(ClientMessage):
    """Reply for ServerMessage::GetAction"""

    TAG = 1

    __slots__ = ("action",)

    action: Action

    def __init__(self, action: Action):
        self.action = action
        """Player's action"""

    @staticmethod
    def read_from(stream: StreamWrapper) -> "ActionMessage":
        """Read ActionMessage from input stream
        """
        action = Action.read_from(stream)
        return ActionMessage(action)
    
    def write_to(self, stream: StreamWrapper):
        """Write ActionMessage to output stream
        """
        stream.write_int(self.TAG)
        self.action.write_to(stream)
    
    def __repr__(self):
        return "ActionMessage(" + \
            repr(self.action) + \
            ")"

ClientMessage.ActionMessage = ActionMessage

class DebugUpdateDone(ClientMessage):
    """Signifies finish of the debug update"""

    TAG = 2

    __slots__ = ()


    def __init__(self):
        pass

    @staticmethod
    def read_from(stream: StreamWrapper) -> "DebugUpdateDone":
        """Read DebugUpdateDone from input stream
        """
        return DebugUpdateDone()
    
    def write_to(self, stream: StreamWrapper):
        """Write DebugUpdateDone to output stream
        """
        stream.write_int(self.TAG)
    
    def __repr__(self):
        return "DebugUpdateDone(" + \
            ")"

ClientMessage.DebugUpdateDone = DebugUpdateDone

class RequestDebugState(ClientMessage):
    """Request debug state from the app"""

    TAG = 3

    __slots__ = ()


    def __init__(self):
        pass

    @staticmethod
    def read_from(stream: StreamWrapper) -> "RequestDebugState":
        """Read RequestDebugState from input stream
        """
        return RequestDebugState()
    
    def write_to(self, stream: StreamWrapper):
        """Write RequestDebugState to output stream
        """
        stream.write_int(self.TAG)
    
    def __repr__(self):
        return "RequestDebugState(" + \
            ")"

ClientMessage.RequestDebugState = RequestDebugState