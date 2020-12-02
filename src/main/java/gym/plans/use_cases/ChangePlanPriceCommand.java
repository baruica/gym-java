package gym.plans.use_cases;

public final record ChangePlanPriceCommand(
    String planId,
    Integer newPrice
) {
}
