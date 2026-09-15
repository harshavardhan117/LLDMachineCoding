# Parking Lot — High-Level Design

An in-memory parking lot system for practicing low-level design, layering, and extensibility.

## Scope

The parking lot supports multiple floors, vehicle-specific spots, ticket-based entry and exit, pricing, payment recording, and administrative configuration.

## Functional Requirements

- Support `CAR`, `BIKE`, `TRUCK`, and `EV` vehicles.
- Support multiple floors, such as B1 and B2, with multiple parking spots per floor.
- Allocate a compatible available spot and issue a ticket when a vehicle enters.
- Calculate hourly and daily parking charges using vehicle-specific pricing rules.
- Support cash, UPI, and credit-card payment methods.
- Generate a receipt after successful payment.
- Allow administrators to manage floors, spots, and pricing rules.

## Entry Flow

1. A vehicle arrives at the entry gate.
2. `ParkingService` finds an available compatible spot.
3. The spot is marked occupied and linked to the new ticket.
4. A ticket is saved and returned through `VehicleEntryController`.

## Exit Flow

1. The user presents a ticket ID and payment method.
2. `ParkingService` validates the ticket is active.
3. `FeeCalculatorService` calculates the fee from the parking duration and pricing rule.
4. A payment record is created and processed.
5. On successful payment, the spot is released, the ticket is closed, and a receipt is generated.
6. If payment fails, the ticket remains active and the spot remains occupied.

## Edge Cases

- No compatible spot is available.
- A ticket ID is unknown or already completed.
- Payment fails.
- Partial-hour billing uses a configured grace period before rounding to the next hour.
- Parking longer than a day uses daily pricing plus charges for remaining hours.

## Architecture

```text
ParkingLotSimulator
        ↓
Controllers
        ↓
Services
        ↓
Repositories
        ↓
In-memory collections
```

### Domain Layer

- `ParkingLot` and `ParkingFloor`
- `ParkingSpot`
- `Vehicle`
- `Ticket`
- `PricingRule`
- `Payment`
- `Receipt`
- Enums for vehicle type, payment method/status, and spot status

### Repository Layer

Repositories isolate storage access. They currently use in-memory `List`, `Map`, and `HashMap` collections.

- `ParkingFloorRepository`
- `ParkingSpotRepository`
- `TicketRepository`
- `PricingRuleRepository`
- `PaymentRepository`

### Service Layer

- `ParkingService` coordinates vehicle entry and exit.
- `FeeCalculatorService` calculates hourly/daily fees and applies grace periods.
- `AdminService` manages floors, spots, and pricing rules.

### Controller Layer

- `VehicleEntryController`
- `VehicleExitController`
- `AdminController`

Controllers return `EntryResult` and `ExitResult` DTOs to the simulator/client.

## Current Simulator

`ParkingLotSimulator` wires repositories, services, and controllers. It currently demonstrates:

1. Creating a floor, car spot, and car pricing rule.
2. Parking a car and issuing a ticket.
3. Exiting with credit-card payment.

## Planned Extensions

- Implement `PaymentStrategy` for method-specific payment behavior.
- Add receipt storage and retrieval.
- Add richer EV charging compatibility rules.
- Add concurrency handling for multiple entry and exit gates.
