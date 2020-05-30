package gym.membership.use_cases;

import gym.membership.domain.*;
import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public final class RegisterNewMember {

    private final MemberRepository memberRepository;

    public RegisterNewMember(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberEvent> handle(RegisterNewMemberCommand command) {
        var email = new EmailAddress(command.email);
        var knownMemberOpt = memberRepository.findByEmail(email);

        if (knownMemberOpt.isEmpty()) {
            var member = Member.register(
                new MemberId(memberRepository.nextId()),
                email,
                new SubscriptionId(command.subscriptionId),
                LocalDate.parse(command.subscriptionStartDate)
            );

            memberRepository.store(member);

            return member.getRaisedEvents();
        }

        return Collections.emptyList();

    }
}
