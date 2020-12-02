package gym.subscriptions.use_cases;

public final record SubscribeToPlanCommand(
    String subscriptionId,
    Integer planPrice,
    Integer planDurationInMonths,
    String startDate,
    Boolean isStudent
) {
}
