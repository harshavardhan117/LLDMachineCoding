# Parking Lot

## v1

An in-memory parking lot for a single facility with one entry/exit flow.

### Requirements implemented

- Supports `BIKE`, `CAR`, and `TRUCK` vehicle types.
- Allocates only an available parking spot of the matching vehicle type.
- Issues a unique ticket when a vehicle is parked.
- Records the assigned spot and entry time on the ticket.
- Calculates charges in rounded-up 30-minute blocks using vehicle-specific rates.
- Unparks a vehicle through its ticket and releases the exact assigned spot.
- Demonstrates multiple vehicle entries and exits in `Orchestrator`.

### Structure

- `entities` — domain objects such as `ParkingLot`, `ParkingSpot`, `Vehicle`, and `Ticket`.
- `services` — parking operations and fee calculation.
- `Orchestrator.java` — runnable demonstration.

### Future iterations

- Active-ticket validation and ticket invalidation.
- Configurable pricing rules.
- Automated tests and edge-case handling.
- Multiple floors, persistence, and concurrent entry/exit gates.
