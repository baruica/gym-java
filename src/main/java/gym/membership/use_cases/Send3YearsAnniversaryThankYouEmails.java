package gym.membership.use_cases;

import gym.membership.domain.Mailer;
import gym.membership.domain.MemberRepository;
import gym.membership.domain.ThreeYearsAnniversaryThankYouEmailsSent;

import java.time.LocalDate;

public final class Send3YearsAnniversaryThankYouEmails {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    Send3YearsAnniversaryThankYouEmails(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    public ThreeYearsAnniversaryThankYouEmailsSent handle(Send3YearsAnniversaryThankYouEmailsCommand command) {

        var threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(
            LocalDate.parse(command.asOfDate)
        );

        threeYearsAnniversaryMembers.values().forEach(
            (member) -> {
                mailer.sendEmail(member.email, "Thank you for your loyalty " + member.email + " !");
                member.mark3YearsAnniversaryThankYouEmailAsSent();
            }
        );

        return new ThreeYearsAnniversaryThankYouEmailsSent(threeYearsAnniversaryMembers);
    }
}
