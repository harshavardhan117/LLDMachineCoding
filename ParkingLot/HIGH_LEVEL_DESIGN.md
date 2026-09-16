# Parking Lot — LLD Study Guide

This module is an in-memory parking-lot implementation used to practise a layered Java design. It is intentionally small enough for a machine-coding round while leaving clear extension points.

## 1. Problem Statement

Design a parking lot where vehicles enter, receive an allocated spot and a ticket, then pay before leaving. The system supports multiple floors, vehicle-specific spots, pricing rules, and administrative configuration.

## 2. Requirements and Assumptions

### Functional requirements

- Support `CAR`, `BIKE`, `TRUCK`, and `EV` vehicles.
- Support multiple floors and multiple spots on each floor.
- Allocate a compatible vacant spot during entry and issue a ticket.
- Calculate vehicle-specific hourly and daily prices.
- Accept cash, UPI, and credit-card payment methods.
- Release a spot only after successful payment.
- Allow an admin to add/remove floors and spots and manage pricing rules.

### Rules and failures handled

- Entry fails if there is no compatible vacant spot.
- Exit fails for an unknown or already closed ticket.
- Closing a ticket prevents it from being reused.
- A configured grace period is free; remaining time is rounded up to an hour.
- A stay longer than one day uses whole-day pricing plus the remaining hourly amount.

### Intentional simplifications

- Repositories use in-memory collections, not a database.
- IDs are simple increasing integers for this practice module.
- The current payment strategies print a successful simulated payment. They do not yet model provider failure.
- One simulator thread is assumed; multi-gate concurrency is postponed.
- `double` is used for money here to keep the exercise simple. Production code should usually use `BigDecimal` or a smallest-currency-unit integer.

## 3. Design Approach

The main idea is separation of responsibility:

```text
ParkingLotSimulator (client / composition root)
        ↓
Controllers (request boundary, success/failure DTOs)
        ↓
Services (use-case and business-flow coordination)
        ↓
Repositories (storage access)
        ↓
In-memory collections
```

### Why this layering matters

- The simulator should not decide which spot to allocate or how to calculate fees.
- Controllers should not access repositories directly.
- `ParkingService` coordinates entry and exit because each flow changes several objects together.
- Repositories only store/retrieve data; they do not decide business rules.

## 4. Core Domain Objects

| Object | Responsibility |
| --- | --- |
| `Vehicle` | Registration number and `VehicleType`. |
| `ParkingSpot` | Configured vehicle type plus current occupancy, vehicle, and ticket reference. |
| `ParkingFloor` | Represents a floor such as B1 or B2. |
| `Ticket` | Connects vehicle, allocated spot, issue time, and active/closed state. |
| `PricingRule` | Hourly price, daily price, and grace period for a vehicle type. |
| `Payment` | Payment record with selected method and status. |
| `Receipt` | Successful exit result containing amount and exit time. |

## 5. Entry Flow

1. The client calls `VehicleEntryController.parkVehicle(vehicle)`.
2. The controller delegates to `ParkingService`.
3. `ParkingService` requests compatible vacant spots from `ParkingSpotRepository`.
4. It selects the first candidate, marks it occupied, creates a ticket, and saves the ticket.
5. The controller returns an `EntryResult` containing the ticket ID or an error message.

**Interview point:** the controller does not first fetch a spot and then reserve it. `ParkingService` owns that whole workflow, which avoids splitting one business operation across layers.

## 6. Exit Flow

1. The client calls `VehicleExitController.unparkVehicle(ticketId, paymentMethod)`.
2. `ParkingService` validates that the ticket exists and is active.
3. `FeeCalculatorService` calculates the amount using `PricingRule`.
4. `PaymentStrategyFactory` selects the payment strategy for cash, UPI, or card.
5. The payment is stored and marked completed in the current simulation.
6. The spot becomes vacant, the ticket closes, and a `Receipt` is returned.

**Important invariant:** do not release the spot or close the ticket until payment succeeds. This preserves the correct state if a future payment strategy reports failure.

## 7. Pattern Used: Strategy

`PaymentStrategy` is useful because payment methods may later have different provider calls, validations, or failure behavior.

```text
PaymentMethod → PaymentStrategyFactory → PaymentStrategy
                                      ├── CashPaymentStrategy
                                      ├── UpiPaymentStrategy
                                      └── CreditCardPaymentStrategy
```

`ParkingService` only asks the selected strategy to process the amount. It does not contain `if/else` logic specific to UPI or cards.

## 8. Package Guide

| Package | What belongs there |
| --- | --- |
| `domain` | Core data and state objects, enums. |
| `repository` | In-memory storage operations. |
| `service` | Entry, exit, billing, and admin use cases. |
| `service.payment` | Payment strategy interface, implementations, factory. |
| `controller` | Calls services and converts exceptions into result DTOs. |
| `dto` | `EntryResult` and `ExitResult` returned to the client. |
| root `ParkingLotSimulator` | Dependency wiring and demonstration scenarios. |

## 9. Current Simulator Scenario

`ParkingLotSimulator` demonstrates the happy path:

1. Create repositories, services, and controllers.
2. Add floor B1, a car spot, and a car pricing rule through the admin controller.
3. Park a car and receive a ticket.
4. Exit using a credit card and receive a successful result.

Run it from the repository root:

```powershell
New-Item -ItemType Directory -Force out
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out ParkingLot.ParkingLotSimulator
```

## 10. Next Practice Exercises

Do these one at a time rather than rewriting the whole project:

1. Change `PaymentStrategy.pay(...)` to return success/failure and simulate payment failure.
2. Add negative simulator scenarios: full lot, invalid ticket, reused ticket, and failed payment.
3. Improve allocation: choose a spot by floor or create a separate allocation strategy.
4. Add EV-specific charging information if it becomes an explicit requirement.
5. After practising concurrency separately, make spot allocation and ticket ID generation safe for multiple entry/exit gates.

## 11. Interview Checklist

Before coding, state the requirements, constraints, billing assumption, and scope. Then build the happy path first. If time remains, add one or two meaningful failures and explain extensions such as payment strategies or concurrency. Do not force every design pattern into the initial solution; introduce one when the changing behavior actually needs it.
