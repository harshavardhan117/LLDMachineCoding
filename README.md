# LLDMachineCoding

Collection of machine-coding and low-level design practice implementations.

## Practice modules

| Module | Description | Current version |
| --- | --- | --- |
| [Parking Lot](ParkingLotDesign/README.md) | In-memory parking allocation, ticketing, and fee calculation. | v1 |
| BookMyShow | Planned. | — |

## Versioning approach

- `master` contains the latest reviewed version of each module.
- New iterations are developed on versioned branches such as `v2` and merged through pull requests.
- Each module owns its own README with version history, requirements, design notes, and implementation details.

### Current structure

- `ParkingLotDesign/entities` — domain objects such as `ParkingLot`, `ParkingSpot`, `Vehicle`, and `Ticket`.
- `ParkingLotDesign/services` — parking operations and fee calculation.
- `ParkingLotDesign/Orchestrator.java` — runnable demonstration.

### Scope and future work

Version 1 assumes a single lot and a single entry/exit flow. Future versions can add active-ticket validation, configurable pricing, tests, multiple floors, persistence, and concurrent gate handling.
