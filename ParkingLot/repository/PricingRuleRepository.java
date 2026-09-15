package ParkingLot.repository;

import ParkingLot.domain.*;
import  java.util.*;

public class PricingRuleRepository {
    List<PricingRule> pricingRules;

    public PricingRuleRepository() {
        this.pricingRules = new ArrayList<>();
    }

    public void addPricingRule(PricingRule rule) {
        pricingRules.add(rule);
    }

    public PricingRule getPricingRule(VehicleType type) {
        for (PricingRule rule : pricingRules) {
            if (rule.getVehicleType() == type) {
                return rule;
            }
        }
        return null; // or throw an exception if not found
    }

    public List<PricingRule> getAllPricingRules() {
        return pricingRules;
    }

    public void updatePricingRule(PricingRule newRule) {
        for (int i = 0; i < pricingRules.size(); i++) {
            if (pricingRules.get(i).getVehicleType() == newRule.getVehicleType()) {
                pricingRules.set(i, newRule);
                return;
            }
        }
        throw new IllegalArgumentException("Pricing rule for this vehicle type does not exist.");
    }
}
