package gym.membership.use_cases;

public final record Send3YearsAnniversaryThankYouEmailsCommand(
    String memberId,
    Double newSubscriptionPrice
) {
}
