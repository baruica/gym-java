package gym.membership.use_cases;

public final class RegisterNewMemberCommand {

    public final String memberId;
    public final String subscriptionId;
    public final String subscriptionStartDate;
    public final String email;

    public RegisterNewMemberCommand(String memberId, String subscriptionId, String subscriptionStartDate, String email) {
        this.memberId = memberId;
        this.subscriptionId = subscriptionId;
        this.subscriptionStartDate = subscriptionStartDate;
        this.email = email;
    }
}
