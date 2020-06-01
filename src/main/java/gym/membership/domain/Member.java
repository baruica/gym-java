package gym.membership.domain;

import common.Aggregate;
import common.AggregateId;
import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class Member implements Aggregate {

    public final MemberId id;
    public final EmailAddress emailAddress;
    public final SubscriptionId subscriptionId;
    public final LocalDate memberSince;
    private boolean welcomeEmailWasSent = false;
    private boolean threeYearsAnniversaryThankYouEmailWasSent = false;

    private Member(MemberId id, EmailAddress emailAddress, SubscriptionId subscriptionId, LocalDate memberSince) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.subscriptionId = subscriptionId;
        this.memberSince = memberSince;
    }

    @Override
    public AggregateId id() {
        return id;
    }

    public static Member register(MemberId id, EmailAddress emailAddress, SubscriptionId subscriptionId, LocalDate memberSince) {
        return new Member(
            id,
            emailAddress,
            subscriptionId,
            memberSince
        );
    }

    public Boolean isThreeYearsAnniversary(LocalDate asOfDate) {
        return asOfDate.minusYears(3).isEqual(memberSince);
    }

    public void markWelcomeEmailAsSent() {
        welcomeEmailWasSent = true;
    }

    public void mark3YearsAnniversaryThankYouEmailAsSent() {
        threeYearsAnniversaryThankYouEmailWasSent = true;
    }
}
