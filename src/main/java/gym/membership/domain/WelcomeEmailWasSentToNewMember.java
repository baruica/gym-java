package gym.membership.domain;

public final class WelcomeEmailWasSentToNewMember {

    public final String memberId;

    public WelcomeEmailWasSentToNewMember(final MemberId memberId) {
        this.memberId = memberId.toString();
    }
}
