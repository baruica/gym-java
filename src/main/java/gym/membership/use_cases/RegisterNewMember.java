package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;
import gym.membership.domain.Member;
import gym.membership.domain.Member.MemberId;
import gym.membership.domain.MemberRepository;
import gym.subscriptions.domain.Subscription.SubscriptionId;

import java.time.LocalDate;

public record RegisterNewMember(
    MemberId memberId,
    SubscriptionId subscriptionId,
    LocalDate subscriptionStartDate,
    EmailAddress email
) {
    public record Handler(
        MemberRepository memberRepository,
        Mailer mailer
    ) {
        public Member handle(RegisterNewMember command) {
            var knownMemberOpt = memberRepository.findByEmailAddress(command.email());

            if (knownMemberOpt.isEmpty()) {
                var member = Member.register(
                    command.memberId(),
                    command.email(),
                    command.subscriptionStartDate()
                );

                mailer.sendWelcomeEmail(member);

                memberRepository.store(member);

                return member;
            }

            return null;
        }
    }
}
