cmake_minimum_required(VERSION 3.5)
set (CMAKE_CXX_STANDARD 20)
project(spb_ai_champ)

# OS and compiler checks.
if(WIN32)
    add_definitions(-DWIN32)
    SET(PROJECT_LIBS Ws2_32.lib)
endif()

set(HEADERS
    "MyStrategy.hpp"
    "Stream.hpp"
    "TcpStream.hpp"
    "codegame/ClientMessage.hpp"
    "codegame/ServerMessage.hpp"
    "debugging/DebugCommand.hpp"
    "model/Action.hpp"
    "model/Building.hpp"
    "model/BuildingAction.hpp"
    "model/BuildingProperties.hpp"
    "model/BuildingType.hpp"
    "model/DebugData.hpp"
    "model/FlyingWorkerGroup.hpp"
    "model/Game.hpp"
    "model/MoveAction.hpp"
    "model/Planet.hpp"
    "model/Player.hpp"
    "model/Resource.hpp"
    "model/WorkerGroup.hpp"
)
set (SRC
    "MyStrategy.cpp"
    "Stream.cpp"
    "TcpStream.cpp"
    "codegame/ClientMessage.cpp"
    "codegame/ServerMessage.cpp"
    "debugging/DebugCommand.cpp"
    "main.cpp"
    "model/Action.cpp"
    "model/Building.cpp"
    "model/BuildingAction.cpp"
    "model/BuildingProperties.cpp"
    "model/BuildingType.cpp"
    "model/DebugData.cpp"
    "model/FlyingWorkerGroup.cpp"
    "model/Game.cpp"
    "model/MoveAction.cpp"
    "model/Planet.cpp"
    "model/Player.cpp"
    "model/Resource.cpp"
    "model/WorkerGroup.cpp"
)
SET_SOURCE_FILES_PROPERTIES(${HEADERS} PROPERTIES HEADER_FILE_ONLY TRUE)
include_directories(".")
add_executable(spb_ai_champ ${HEADERS} ${SRC})
TARGET_LINK_LIBRARIES(spb_ai_champ ${PROJECT_LIBS})