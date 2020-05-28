package gym.membership.use_cases;

import gym.membership.domain.Email;
import gym.membership.domain.Member;
import gym.membership.domain.MemberId;
import gym.membership.domain.ThreeYearsAnniversaryThankYouEmailSent;
import gym.membership.infrastructure.InMemoryMailer;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.subscriptions.domain.SubscriptionId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    public void handle() {
        var memberRepository = new MemberInMemoryRepository();

        var startDateJulie = fifthOfJune().minusYears(3);
        var memberJulie = buildMember("julie@gmail.com", startDateJulie);
        memberRepository.store(memberJulie);

        var startDateBob = fifthOfJune().minusYears(2);
        var memberBob = buildMember("bob@gmail.com", startDateBob);
        memberRepository.store(memberBob);

        var startDateLuke = fifthOfJune().minusYears(3);
        var memberLuke = buildMember("luke@gmail.com", startDateLuke);
        memberRepository.store(memberLuke);

        var mailer = new InMemoryMailer();

        var tested = new Send3YearsAnniversaryThankYouEmails(memberRepository, mailer);

        var events = tested.handle(
            new Send3YearsAnniversaryThankYouEmailsCommand("2018-06-05")
        );

        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty julie@gmail.com !"));
        assertTrue(events.contains(new ThreeYearsAnniversaryThankYouEmailSent(memberJulie.id.toString(), startDateJulie.toString())));

        assertFalse(mailer.sentEmails.containsValue("Thank you for your loyalty bob@gmail.com !"));
        assertFalse(events.contains(new ThreeYearsAnniversaryThankYouEmailSent(memberBob.id.toString(), startDateBob.toString())));

        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty luke@gmail.com !"));
        assertTrue(events.contains(new ThreeYearsAnniversaryThankYouEmailSent(memberLuke.id.toString(), startDateLuke.toString())));
    }

    private LocalDate fifthOfJune() {
        return LocalDate.parse("2018-06-05");
    }

    private Member buildMember(String email, LocalDate startDate) {
        return Member.register(
            new MemberId(UUID.randomUUID().toString()),
            new Email(email),
            new SubscriptionId("subscriptionId def"),
            startDate
        );
    }
}
