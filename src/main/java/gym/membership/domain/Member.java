package gym.membership.domain;

import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Member {

    public final MemberId id;
    public final EmailAddress emailAddress;
    public final SubscriptionId subscriptionId;
    public final LocalDate memberSince;

    private final List<MemberEvent> raisedEvents = new ArrayList<>();

    private Member(MemberId id, EmailAddress emailAddress, SubscriptionId subscriptionId, LocalDate memberSince) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.subscriptionId = subscriptionId;
        this.memberSince = memberSince;
    }

    public static Member register(MemberId id, EmailAddress emailAddress, SubscriptionId subscriptionId, LocalDate memberSince) {
        var member = new Member(
            id,
            emailAddress,
            subscriptionId,
            memberSince
        );

        member.raisedEvents.add(
            new NewMemberSubscribed(
                member.id.toString(),
                member.emailAddress.toString(),
                member.subscriptionId.toString(),
                member.memberSince.toString()
            )
        );

        return member;
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
                emailAddress.toString(),
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
