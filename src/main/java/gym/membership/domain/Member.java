package gym.membership.domain;

import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class Member {

    public final MemberId id;
    public final EmailAddress email;
    public final SubscriptionId subscriptionId;
    public final LocalDate memberSince;

    public Member(MemberId id, EmailAddress email, SubscriptionId subscriptionId, LocalDate startDate) {
        this.id = id;
        this.email = email;
        this.subscriptionId = subscriptionId;
        this.memberSince = startDate;
    }

    public Boolean isThreeYearsAnniversary(LocalDate asOfDate) {
        return asOfDate.minusYears(3).isEqual(memberSince);
    }

    public void markWelcomeEmailAsSent() {
    }

    public void mark3YearsAnniversaryThankYouEmailAsSent() {
    }
}
