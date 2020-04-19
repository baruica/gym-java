package fr.the.gym.membership.use_cases;

import fr.the.gym.membership.domain.Mailer;
import fr.the.gym.membership.domain.MemberRepository;
import fr.the.gym.membership.domain.ThreeYearsAnniversaryThankYouEmailsSent;

import java.util.ArrayList;

public final class Send3YearsAnniversaryThankYouEmails {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    Send3YearsAnniversaryThankYouEmails(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    public ThreeYearsAnniversaryThankYouEmailsSent handle(Send3YearsAnniversaryThankYouEmailsCommand command) {

        var threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(command.asOfDate);

        threeYearsAnniversaryMembers.forEach(
            (memberId, member) -> {
                mailer.sendEmail(member.email(), "Thank you for your loyalty " + member.email() + " !");
                member.mark3YearsAnniversaryThankYouEmailAsSent();
            }
        );

        return new ThreeYearsAnniversaryThankYouEmailsSent(
            new ArrayList<>(threeYearsAnniversaryMembers.keySet())
        );
    }
}
