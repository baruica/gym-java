package fr.craft.gym.membership.use_cases;

import fr.craft.gym.membership.domain.EmailAddress;
import fr.craft.gym.membership.domain.Member;
import fr.craft.gym.membership.domain.MemberRepository;
import fr.craft.gym.membership.domain.NewMemberSubscribed;
import fr.craft.gym.subscriptions.domain.PlanSubscribed;

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
                event.subscriptionStartDate
            );

            memberRepository.store(member);

            return new NewMemberSubscribed(member.id, member.email);
        }

        return null;
    }
}
