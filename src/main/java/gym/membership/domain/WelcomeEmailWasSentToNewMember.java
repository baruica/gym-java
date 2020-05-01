package gym.membership.domain;

public final class WelcomeEmailWasSentToNewMember {

    public final String memberId;
    public final String memberEmail;
    public final String memberSubscriptionId;

    public WelcomeEmailWasSentToNewMember(final MemberId memberId, String memberEmail, String memberSubscriptionId) {
        this.memberId = memberId.toString();
        this.memberEmail = memberEmail;
        this.memberSubscriptionId = memberSubscriptionId;
    }
}
