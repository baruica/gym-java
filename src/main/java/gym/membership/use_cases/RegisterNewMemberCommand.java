package gym.membership.use_cases;

public final class RegisterNewMemberCommand {

    public final String subscriptionId;
    public final String subscriptionStartDate;
    public final String email;

    public RegisterNewMemberCommand(String subscriptionId, String subscriptionStartDate, String email) {
        this.subscriptionId = subscriptionId;
        this.subscriptionStartDate = subscriptionStartDate;
        this.email = email;
    }
}
