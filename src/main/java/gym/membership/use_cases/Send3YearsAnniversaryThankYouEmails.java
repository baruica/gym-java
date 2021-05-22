package gym.membership.use_cases;

import gym.membership.domain.Mailer;
import gym.membership.domain.Member;
import gym.membership.domain.Member.MemberId;
import gym.membership.domain.MemberRepository;

public record Send3YearsAnniversaryThankYouEmails(
    MemberRepository memberRepository,
    Mailer mailer
) {
    public Member handle(Send3YearsAnniversaryThankYouEmailsCommand command) {

        var threeYearsAnniversaryMember = memberRepository.get(
            new MemberId(command.memberId())
        );

        mailer.send3YearsAnniversaryEmail(
            threeYearsAnniversaryMember,
            command.newSubscriptionPrice()
        );

        return threeYearsAnniversaryMember;
    }
}
