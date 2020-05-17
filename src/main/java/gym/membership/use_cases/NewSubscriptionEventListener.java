package gym.membership.use_cases;

import gym.membership.domain.Email;
import gym.membership.domain.Member;
import gym.membership.domain.MemberEvent;
import gym.membership.domain.MemberRepository;
import gym.subscriptions.domain.NewSubscription;
import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public final class NewSubscriptionEventListener {

    private final MemberRepository memberRepository;

    public NewSubscriptionEventListener(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberEvent> handle(NewSubscription event) {

        var email = new Email(event.email);
        var knownMemberOpt = memberRepository.findByEmail(email);

        if (knownMemberOpt.isEmpty()) {
            var member = new Member(
                memberRepository.nextId(),
                email,
                new SubscriptionId(event.subscriptionId),
                LocalDate.parse(event.subscriptionStartDate)
            );

            memberRepository.store(member);

            return member.getRaisedEvents();
        }

        return Collections.emptyList();
    }
}
