package gym.membership.use_cases;

public record SendSummaryUponSubscriptionCommand(
    String email,
    String startDate,
    String endDate,
    Integer price
) {
}
