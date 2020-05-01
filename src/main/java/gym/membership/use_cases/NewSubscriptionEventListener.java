package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;
import gym.membership.domain.NewMemberSubscribed;
import gym.subscriptions.domain.NewSubscription;
import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class NewSubscriptionEventListener {

    private final MemberRepository memberRepository;

    public NewSubscriptionEventListener(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public NewMemberSubscribed handle(NewSubscription event) {

        var email = new EmailAddress(event.email);
        var knownMemberOpt = memberRepository.findByEmail(email);

        if (knownMemberOpt.isEmpty()) {
            var member = new Member(
                memberRepository.nextId(),
                email,
                new SubscriptionId(event.subscriptionId),
                LocalDate.parse(event.subscriptionStartDate)
            );

            memberRepository.store(member);

            return new NewMemberSubscribed(
                member.id.toString(),
                member.email.toString(),
                member.subscriptionId.toString(),
                member.memberSince.toString()
            );
        }

        return null;
    }
}
