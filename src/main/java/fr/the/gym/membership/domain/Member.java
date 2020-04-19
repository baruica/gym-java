package fr.the.gym.membership.domain;

import fr.the.gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class Member {

    private final MemberId id;
    private final EmailAddress email;
    private final SubscriptionId subscriptionId;
    private final LocalDate startDate;

    private Boolean welcomeEmailHasBeenSent;
    private Boolean threeYearsAnniversaryEmailHasBeenSent;

    public Member(MemberId id, String email, SubscriptionId subscriptionId, LocalDate startDate) {
        this.id = id;
        this.email = new EmailAddress(email);
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.welcomeEmailHasBeenSent = false;
        this.threeYearsAnniversaryEmailHasBeenSent = false;
    }

    public MemberId id() {
        return id;
    }

    public EmailAddress email() {
        return email;
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
