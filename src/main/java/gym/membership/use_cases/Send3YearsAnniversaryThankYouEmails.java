package gym.membership.use_cases;

import gym.membership.domain.Mailer;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.List;

public final class Send3YearsAnniversaryThankYouEmails {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    Send3YearsAnniversaryThankYouEmails(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    public List<Member> handle(Send3YearsAnniversaryThankYouEmailsCommand command) {

        var threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(
            LocalDate.parse(command.asOfDate)
        );

        threeYearsAnniversaryMembers.forEach(
            mailer::send3YearsAnniversaryEmail
        );

        return threeYearsAnniversaryMembers;
    }
}
