package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;
import gym.membership.domain.NewMemberSubscribed;
import gym.subscriptions.domain.PlanSubscribed;

import java.time.LocalDate;

public final class PlanSubscribedEventListener {

    private final MemberRepository memberRepository;

    public PlanSubscribedEventListener(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public NewMemberSubscribed handle(PlanSubscribed event) {

        var memberOpt = memberRepository.findByEmail(new EmailAddress(event.email));

        if (memberOpt.isEmpty()) {
            var member = new Member(
                memberRepository.nextId(),
                event.email,
                event.subscriptionId,
                LocalDate.parse(event.subscriptionStartDate)
            );

            memberRepository.store(member);

            return new NewMemberSubscribed(member.id, member.email);
        }

        return null;
    }
}
