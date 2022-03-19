package gym.membership.use_cases;

import gym.membership.domain.Mailer;
import gym.membership.domain.Member;
import gym.membership.domain.Member.MemberId;
import gym.membership.domain.MemberRepository;

public record Send3YearsAnniversaryThankYouEmails(
    MemberId memberId,
    Double newSubscriptionPrice
) {
    public record Handler(
        MemberRepository memberRepository,
        Mailer mailer
    ) {
        public Member handle(Send3YearsAnniversaryThankYouEmails command) {

            var threeYearsAnniversaryMember = memberRepository.get(
                command.memberId().toString()
            );

            mailer.send3YearsAnniversaryEmail(
                threeYearsAnniversaryMember,
                command.newSubscriptionPrice()
            );

            return threeYearsAnniversaryMember;
        }
    }
}
