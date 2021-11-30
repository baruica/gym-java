package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;

public record RegisterNewMember(
    String memberId,
    String subscriptionId,
    String subscriptionStartDate, String email
) {
    public record Handler(
        MemberRepository memberRepository,
        Mailer mailer
    ) {
        public Member handle(RegisterNewMember command) {
            var emailAddress = new EmailAddress(command.email());
            var knownMemberOpt = memberRepository.findByEmailAddress(emailAddress);

            if (knownMemberOpt.isEmpty()) {
                var member = Member.register(
                    command.memberId(),
                    emailAddress,
                    LocalDate.parse(command.subscriptionStartDate())
                );

                mailer.sendWelcomeEmail(member);

                memberRepository.store(member);

                return member;
            }

            return null;
        }
    }
}
