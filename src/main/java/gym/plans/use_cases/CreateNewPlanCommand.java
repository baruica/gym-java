package gym.plans.use_cases;

public final class CreateNewPlanCommand {
    public final Integer basePrice;
    public final Integer planDurationsInMonths;

    public CreateNewPlanCommand(Integer basePrice, Integer planDurationsInMonths) {
        this.basePrice = basePrice;
        this.planDurationsInMonths = planDurationsInMonths;
    }
}
