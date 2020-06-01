package gym.plans.use_cases;

public final class CreateNewPlanCommand {
    public final String planId;
    public final Integer basePrice;
    public final Integer planDurationsInMonths;

    public CreateNewPlanCommand(String planId, Integer basePrice, Integer planDurationsInMonths) {
        this.planId = planId;
        this.basePrice = basePrice;
        this.planDurationsInMonths = planDurationsInMonths;
    }
}
