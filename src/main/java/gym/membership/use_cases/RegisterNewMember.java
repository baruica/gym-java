package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;
import gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class RegisterNewMember {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    public RegisterNewMember(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    public Member handle(RegisterNewMemberCommand command) {
        var email = new EmailAddress(command.email);
        var knownMemberOpt = memberRepository.findByEmail(email);

        if (knownMemberOpt.isEmpty()) {
            var member = Member.register(
                command.memberId,
                email,
                new SubscriptionId(command.subscriptionId),
                LocalDate.parse(command.subscriptionStartDate)
            );

            mailer.sendWelcomeEmail(member);

            memberRepository.store(member);

            return member;
        }

        return null;
    }
}