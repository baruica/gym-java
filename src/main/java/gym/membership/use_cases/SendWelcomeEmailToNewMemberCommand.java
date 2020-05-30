package gym.membership.use_cases;

public final class SendWelcomeEmailToNewMemberCommand {

    public final String memberId;

    public SendWelcomeEmailToNewMemberCommand(String memberId) {
        this.memberId = memberId;
    }
}
