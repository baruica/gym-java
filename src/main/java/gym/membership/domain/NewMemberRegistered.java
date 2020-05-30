package gym.membership.domain;

import java.util.Objects;

public final class NewMemberRegistered extends MemberEvent {

    public final String memberEmail;
    public final String subscriptionId;
    public final String memberSince;

    public NewMemberRegistered(String memberId, String memberEmail, String subscriptionId, String memberSince) {
        super(memberId);
        this.memberEmail = memberEmail;
        this.subscriptionId = subscriptionId;
        this.memberSince = memberSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewMemberRegistered that = (NewMemberRegistered) o;
        return memberId.equals(that.memberId) &&
            memberEmail.equals(that.memberEmail) &&
            subscriptionId.equals(that.subscriptionId) &&
            memberSince.equals(that.memberSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, memberEmail, subscriptionId, memberSince);
    }
}
