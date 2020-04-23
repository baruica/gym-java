package gym.membership.domain;

import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class Member {

    public final MemberId id;
    public final EmailAddress email;
    private final SubscriptionId subscriptionId;
    private final LocalDate startDate;

    private Boolean welcomeEmailHasBeenSent;
    private Boolean threeYearsAnniversaryEmailHasBeenSent;

    public Member(MemberId id, String email, String subscriptionId, LocalDate startDate) {
        this.id = id;
        this.email = new EmailAddress(email);
        this.subscriptionId = new SubscriptionId(subscriptionId);
        this.startDate = startDate;
        this.welcomeEmailHasBeenSent = false;
        this.threeYearsAnniversaryEmailHasBeenSent = false;
    }

    public Boolean isThreeYearsAnniversary(LocalDate asOfDate) {
        return asOfDate.minusYears(3).isEqual(startDate);
    }

    public void markWelcomeEmailAsSent() {
        this.welcomeEmailHasBeenSent = true;
    }

    public void mark3YearsAnniversaryThankYouEmailAsSent() {
        this.threeYearsAnniversaryEmailHasBeenSent = true;
    }
}
