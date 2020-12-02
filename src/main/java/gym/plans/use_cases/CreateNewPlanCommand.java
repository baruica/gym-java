package gym.plans.use_cases;

public final record CreateNewPlanCommand(
    String planId,
    Integer basePrice,
    Integer planDurationsInMonths
) {
}
