package gym.membership.domain;

import gym.subscriptions.domain.Subscription;

import java.time.LocalDate;

public final class Member {

    public final MemberId id;
    public final EmailAddress emailAddress;
    private final Subscription.SubscriptionId subscriptionId;
    private final LocalDate memberSince;
    private boolean welcomeEmailWasSent = false;
    private boolean threeYearsAnniversaryThankYouEmailWasSent = false;

    public static record MemberId(String id) {
    }

    private Member(MemberId id, EmailAddress emailAddress, Subscription.SubscriptionId subscriptionId, LocalDate memberSince) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.subscriptionId = subscriptionId;
        this.memberSince = memberSince;
    }

    public static Member register(String id, EmailAddress emailAddress, Subscription.SubscriptionId subscriptionId, LocalDate memberSince) {
        return new Member(
            new MemberId(id),
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
