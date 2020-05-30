package gym.membership.domain;

import java.util.Objects;

public final class WelcomeEmailWasSentToNewMember extends MemberEvent {

    public final String memberEmail;
    public final String memberSubscriptionId;

    public WelcomeEmailWasSentToNewMember(String memberId, String memberEmail, String memberSubscriptionId) {
        super(memberId);
        this.memberEmail = memberEmail;
        this.memberSubscriptionId = memberSubscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WelcomeEmailWasSentToNewMember that = (WelcomeEmailWasSentToNewMember) o;
        return memberId.equals(that.memberId) &&
            memberEmail.equals(that.memberEmail) &&
            memberSubscriptionId.equals(that.memberSubscriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, memberEmail, memberSubscriptionId);
    }
}
