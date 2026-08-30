# LLDMachineCoding

Machine-coding and low-level design practice implementations.

## Parking Lot — v1

An in-memory parking lot for a single facility with one entry/exit flow.

### Requirements implemented

- Supports `BIKE`, `CAR`, and `TRUCK` vehicle types.
- Allocates only an available parking spot of the matching vehicle type.
- Issues a unique ticket when a vehicle is parked.
- Records the assigned spot and entry time on the ticket.
- Calculates charges in rounded-up 30-minute blocks using vehicle-specific rates.
- Unparks a vehicle through its ticket and releases the exact assigned spot.
- Demonstrates multiple vehicle entries and exits in `Orchestrator`.

### Current structure

- `ParkingLotDesign/entities` — domain objects such as `ParkingLot`, `ParkingSpot`, `Vehicle`, and `Ticket`.
- `ParkingLotDesign/services` — parking operations and fee calculation.
- `ParkingLotDesign/Orchestrator.java` — runnable demonstration.

### Scope and future work

Version 1 assumes a single lot and a single entry/exit flow. Future versions can add active-ticket validation, configurable pricing, tests, multiple floors, persistence, and concurrent gate handling.
