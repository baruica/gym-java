package gym.membership.use_cases;

import gym.membership.domain.Mailer;
import gym.membership.domain.MemberEvent;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public final class Send3YearsAnniversaryThankYouEmails {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    Send3YearsAnniversaryThankYouEmails(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    public List<MemberEvent> handle(Send3YearsAnniversaryThankYouEmailsCommand command) {

        var threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(
            LocalDate.parse(command.asOfDate)
        );

        threeYearsAnniversaryMembers.values().forEach(
            (member) -> {
                mailer.sendEmail(member.email, "Thank you for your loyalty " + member.email + " !");
                member.mark3YearsAnniversaryThankYouEmailAsSent();
            }
        );

        return threeYearsAnniversaryMembers.values().stream()
            .map((member -> member.getRaisedEvents().get(member.getRaisedEvents().size() - 1)))
            .collect(Collectors.toList());
    }
}
