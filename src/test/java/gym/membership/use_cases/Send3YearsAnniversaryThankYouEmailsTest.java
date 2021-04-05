package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    public void handle() {
        var memberRepository = new InMemoryMemberRepository();

        var memberLuke = Member.register(
            UUID.randomUUID().toString(),
            new EmailAddress("luke@gmail.com"),
            LocalDate.parse("2018-06-05").minusYears(3)
        );
        memberRepository.store(memberLuke);

        var mailer = new InMemoryMailer();

        var tested = new Send3YearsAnniversaryThankYouEmails(memberRepository, mailer);

        tested.handle(
            new Send3YearsAnniversaryThankYouEmailsCommand(memberLuke.id.id(), 780.0)
        );

        assertTrue(mailer.threeYearsAnniversaryWasSentTo(memberLuke.emailAddress, 780.0));
    }
}
