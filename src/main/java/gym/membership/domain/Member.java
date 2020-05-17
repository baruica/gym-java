package gym.membership.domain;

import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Member {

    public final MemberId id;
    public final Email email;
    public final SubscriptionId subscriptionId;
    public final LocalDate memberSince;

    private final List<MemberEvent> raisedEvents = new ArrayList<>();

    public Member(MemberId id, Email email, SubscriptionId subscriptionId, LocalDate startDate) {
        this.id = id;
        this.email = email;
        this.subscriptionId = subscriptionId;
        this.memberSince = startDate;

        raisedEvents.add(
            new NewMemberSubscribed(
                this.id.toString(),
                this.email.toString(),
                this.subscriptionId.toString(),
                this.memberSince.toString()
            )
        );
    }

    public List<MemberEvent> getRaisedEvents() {
        return raisedEvents;
    }

    public Boolean isThreeYearsAnniversary(LocalDate asOfDate) {
        return asOfDate.minusYears(3).isEqual(memberSince);
    }

    public void markWelcomeEmailAsSent() {
        raisedEvents.add(
            new WelcomeEmailWasSentToNewMember(
                id.toString(),
                email.toString(),
                subscriptionId.toString()
            )
        );
    }

    public void mark3YearsAnniversaryThankYouEmailAsSent() {
        raisedEvents.add(
            new ThreeYearsAnniversaryThankYouEmailSent(
                id.toString(),
                memberSince.toString()
            )
        );
    }
}
