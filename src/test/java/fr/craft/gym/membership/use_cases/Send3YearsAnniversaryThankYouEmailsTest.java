package fr.craft.gym.membership.use_cases;

import fr.craft.gym.membership.domain.Member;
import fr.craft.gym.membership.domain.MemberId;
import fr.craft.gym.membership.infrastructure.InMemoryMailer;
import fr.craft.gym.membership.infrastructure.MemberInMemoryRepository;
import fr.craft.gym.subscriptions.domain.SubscriptionId;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    public void handle() {
        var memberRepository = new MemberInMemoryRepository();

        var memberJulie = buildMember("julie@gmail.com", XYearsBeforeThe(3, fifthOfJune()));
        memberRepository.store(memberJulie);

        var memberBob = buildMember("bob@gmail.com", XYearsBeforeThe(2, fifthOfJune()));
        memberRepository.store(memberBob);

        var memberLuke = buildMember("luke@gmail.com", XYearsBeforeThe(3, fifthOfJune()));
        memberRepository.store(memberLuke);

        var mailer = new InMemoryMailer();

        var tested = new Send3YearsAnniversaryThankYouEmails(memberRepository, mailer);

        var event = tested.handle(
            new Send3YearsAnniversaryThankYouEmailsCommand(fifthOfJune())
        );

        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty julie@gmail.com !"));
        assertFalse(mailer.sentEmails.containsValue("Thank you for your loyalty bob@gmail.com !"));
        assertTrue(mailer.sentEmails.containsValue("Thank you for your loyalty luke@gmail.com !"));
        assertTrue(event.memberIds.contains(memberJulie.id));
        assertFalse(event.memberIds.contains(memberBob.id));
        assertTrue(event.memberIds.contains(memberLuke.id));
    }

    private LocalDate fifthOfJune() {
        return LocalDate.parse("2018-06-05");
    }

    private LocalDate XYearsBeforeThe(Integer nbYearsBefore, LocalDate date) {
        return date.minusYears(nbYearsBefore);
    }

    private Member buildMember(String email, LocalDate startDate) {
        return new Member(
            new MemberId(UUID.randomUUID().toString()),
            email,
            new SubscriptionId("def"),
            startDate
        );
    }
}
