package gym.membership.domain;

import java.util.Objects;

public final class ThreeYearsAnniversaryThankYouEmailSent extends MemberEvent {

    public final String memberSince;

    public ThreeYearsAnniversaryThankYouEmailSent(String memberId, String memberSince) {
        super(memberId);
        this.memberSince = memberSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeYearsAnniversaryThankYouEmailSent that = (ThreeYearsAnniversaryThankYouEmailSent) o;
        return memberId.equals(that.memberId) &&
            memberSince.equals(that.memberSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, memberSince);
    }
}
