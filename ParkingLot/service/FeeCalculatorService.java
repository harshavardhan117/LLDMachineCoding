package ParkingLot.service;

import ParkingLot.repository.PricingRuleRepository;
import ParkingLot.domain.PricingRule;
import ParkingLot.domain.Ticket;

public class FeeCalculatorService {
    public PricingRuleRepository pricingRuleRepository;

    public FeeCalculatorService(PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public double calculateTotalFees(Ticket ticket) {
        PricingRule rule = pricingRuleRepository.getPricingRule(ticket.getVehicle().getType());
        if (rule == null) {
            throw new IllegalArgumentException("No pricing rule for vehicle type.");
        }

        long duration = System.currentTimeMillis() - ticket.getIssuedAt().getTime();
        long dayMillis = 24L * 60 * 60 * 1000;
        long hourMillis = 60L * 60 * 1000;
        long graceMillis = rule.getGracePeriodMinutes() * 60L * 1000;

        long fullDays = duration / dayMillis;
        long remainingMillis = duration % dayMillis;
        long billedHours = remainingMillis <= graceMillis
            ? 0
            : (remainingMillis - graceMillis + hourMillis - 1) / hourMillis;

        return fullDays * rule.getDailyPrice() + billedHours * rule.getPricePerHour();
    }

}
