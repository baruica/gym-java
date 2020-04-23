package gym.plans.use_cases;

public final class ChangePlanPriceCommand {
    public final String planId;
    public final Integer newPrice;

    public ChangePlanPriceCommand(String planId, Integer newPrice) {
        this.planId = planId;
        this.newPrice = newPrice;
    }
}
