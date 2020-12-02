package gym.membership.use_cases;

public final record RegisterNewMemberCommand(
    String memberId,
    String subscriptionId,
    String subscriptionStartDate, String email
) {
}
