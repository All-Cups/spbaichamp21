## Codegame

### `ServerMessage`

Message sent from server

One of:

* `GetAction` &mdash; Get action for next tick

  Fields:

  + `player_view`: `Model::Game` &mdash; Player's view
  + `debug_available`: `boolean` &mdash; Whether app is running with debug interface available
* `Finish` &mdash; Signifies end of the game

  No fields
* `DebugUpdate` &mdash; Debug update

  Fields:

  + `player_view`: `Model::Game` &mdash; Player's view

### `ClientMessage`

Message sent from client

One of:

* `DebugMessage` &mdash; Ask app to perform new debug command

  Fields:

  + `command`: `Debugging::DebugCommand` &mdash; Command to perform
* `ActionMessage` &mdash; Reply for ServerMessage::GetAction

  Fields:

  + `action`: `Model::Action` &mdash; Player's action
* `DebugUpdateDone` &mdash; Signifies finish of the debug update

  No fields
* `RequestDebugState` &mdash; Request debug state from the app

  No fields

## Debugging

### `DebugCommand`

Debug commands that can be sent while debugging with the app

One of:

* `Add` &mdash; Add debug data to current tick

  Fields:

  + `data`: `Model::DebugData` &mdash; Data to add
* `Clear` &mdash; Clear current tick's debug data

  No fields
* `SetAutoFlush` &mdash; Enable/disable auto performing of commands

  Fields:

  + `enable`: `boolean` &mdash; Enable/disable autoflush
* `Flush` &mdash; Perform all previously sent commands

  No fields

## Model

### `Specialty`

Player's specialty

Variants:

* `Logistics` &mdash; Logistics. Increased travel distance
* `Production` &mdash; Production. Increased work speed
* `Combat` &mdash; Combat. Increased damage

### `Player`

Player (game participant)

Fields:

* `team_index`: `int32` &mdash; Team index
* `score`: `int32` &mdash; Current score points
* `specialty`: `Option<Specialty>` &mdash; Player's specialty

### `Resource`

Resource type

Variants:

* `Stone` &mdash; Stone
* `Ore` &mdash; Ore
* `Sand` &mdash; Sand
* `Organics` &mdash; Organics
* `Metal` &mdash; Metal
* `Silicon` &mdash; Silicon
* `Plastic` &mdash; Plastic
* `Chip` &mdash; Chip
* `Accumulator` &mdash; Accumulator

### `WorkerGroup`

Group of workers on a planet

Fields:

* `player_index`: `int32` &mdash; Index of player controlling the workers
* `number`: `int32` &mdash; Number of workers in the group

### `BuildingType`

Building type

Variants:

* `Quarry` &mdash; Quarry harvests stone
* `Mines` &mdash; Mines harvests ore
* `Career` &mdash; Career harvest sand
* `Farm` &mdash; Farm harvests organics
* `Foundry` &mdash; Foundry produces metal
* `Furnace` &mdash; Furnace produces silicon
* `Bioreactor` &mdash; Bioreactor produces plastic
* `ChipFactory` &mdash; Chip factory produces chips
* `AccumulatorFactory` &mdash; Accumulator factory produces accumulators
* `Replicator` &mdash; Replicator produces new workers
* `Replicator2` &mdash; Second level replicator
* `Replicator3` &mdash; Third level replicator

### `Building`

A building

Fields:

* `building_type`: `BuildingType` &mdash; Building's type
* `health`: `int32` &mdash; Current health
* `work_done`: `int32` &mdash; Amount of work done for current task
* `last_tick_tasks_done`: `int32` &mdash; Number of tasks finished since last tick

### `Planet`

A planet

Fields:

* `id`: `int32` &mdash; Unique identifier of the planet
* `x`: `int32` &mdash; X coordinate
* `y`: `int32` &mdash; Y coordinate
* `harvestable_resource`: `Option<Resource>` &mdash; Resource that can be harvested on the planet
* `worker_groups`: `[WorkerGroup]` &mdash; List of worker groups
* `resources`: `Map<Resource -> int32>` &mdash; Resources stored on the planet
* `building`: `Option<Building>` &mdash; Building on the planet

### `FlyingWorkerGroup`

A flying worker group

Fields:

* `player_index`: `int32` &mdash; Index of player controlling workers
* `number`: `int32` &mdash; Number of workers in the group
* `departure_tick`: `int32` &mdash; Tick when workers left previous planet on their path
* `departure_planet`: `int32` &mdash; Id of the previous planet on the path
* `next_planet_arrival_tick`: `int32` &mdash; Tick when workers will arrive to the next planet in their path
* `next_planet`: `int32` &mdash; Id of the next planet in the path
* `target_planet`: `int32` &mdash; Id of the target planet
* `resource`: `Option<Resource>` &mdash; Resource that workers are carrying

### `BuildingProperties`

Building properties

Fields:

* `base_building`: `Option<BuildingType>` &mdash; Building type that this building can be upgraded from
* `build_resources`: `Map<Resource -> int32>` &mdash; Resources required for building
* `max_health`: `int32` &mdash; Max health points of the building
* `max_workers`: `int32` &mdash; Max number of workers in the building
* `work_resources`: `Map<Resource -> int32>` &mdash; Resources required to start another task
* `produce_worker`: `boolean` &mdash; Whether performing a task spawn new workers
* `produce_resource`: `Option<Resource>` &mdash; Resource produced when performing a task
* `produce_amount`: `int32` &mdash; Amount of resources/workers produced when performing one task
* `produce_score`: `int32` &mdash; Score points given for performing one task
* `harvest`: `boolean` &mdash; Whether building is harvesting. In this case resource can only be produced if it is harvestable on the planet
* `work_amount`: `int32` &mdash; Amount of work needed to finish one task

### `Game`

Current game's state

Fields:

* `my_index`: `int32` &mdash; Your player's index
* `current_tick`: `int32` &mdash; Current tick number
* `max_tick_count`: `int32` &mdash; Max number of ticks in the game
* `players`: `[Player]` &mdash; List of players
* `planets`: `[Planet]` &mdash; List of planets
* `flying_worker_groups`: `[FlyingWorkerGroup]` &mdash; List of flying worker groups
* `max_flying_worker_groups`: `int32` &mdash; Max number of flying worker groups for one player
* `max_travel_distance`: `int32` &mdash; Max distance of direct travel between planets
* `logistics_upgrade`: `int32` &mdash; Additional distance of direct travel between planets for player with Logistics specialty
* `production_upgrade`: `int32` &mdash; Additional work done by player with Production specialty (in percent)
* `combat_upgrade`: `int32` &mdash; Additional strength workers for player with Combat specialty (in percent)
* `max_builders`: `int32` &mdash; Max number of workers performing building on one planet
* `building_properties`: `Map<BuildingType -> BuildingProperties>` &mdash; Properties of every building type
* `specialties_allowed`: `boolean` &mdash; Whether choosing specialties is allowed
* `view_distance`: `Option<int32>` &mdash; View distance

### `DebugData`

TODO - Document

Fields:

### `MoveAction`

Movement order

Fields:

* `start_planet`: `int32` &mdash; Id of the planet where workers need to be sent from
* `target_planet`: `int32` &mdash; Id of the target planet
* `worker_number`: `int32` &mdash; Number of workers to send
* `take_resource`: `Option<Resource>` &mdash; Resource workers should carry

### `BuildingAction`

Building order

Fields:

* `planet`: `int32` &mdash; Id of the planet where the action needs to be performed
* `building_type`: `Option<BuildingType>` &mdash; Type of a building to build. If absent, current building will be destroyed

### `Action`

Player's actions

Fields:

* `moves`: `[MoveAction]` &mdash; List of movement orders
* `buildings`: `[BuildingAction]` &mdash; List of building orders
* `choose_specialty`: `Option<Specialty>` &mdash; Choosing specialty