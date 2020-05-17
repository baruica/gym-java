package gym.membership.use_cases;

import gym.membership.domain.*;
import gym.membership.infrastructure.InMemoryMailer;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.membership.infrastructure.MemberRepositoryException;
import gym.subscriptions.domain.SubscriptionId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendWelcomeEmailToNewMembersTest {

    @Test
    public void handle() throws MemberRepositoryException {

        var memberId = new MemberId("abc");
        var email = "bob@gmail.com";
        var subscriptionId = new SubscriptionId("def");
        var startDate = LocalDate.now();
        var member = new Member(
            memberId,
            new Email(email),
            subscriptionId,
            startDate
        );
        var memberRepository = new MemberInMemoryRepository();
        memberRepository.store(member);

        var mailer = new InMemoryMailer();

        var tested = new SendWelcomeEmailToNewMembers(memberRepository, mailer);

        var events = tested.handle(
            new NewMemberSubscribed(
                memberId.toString(),
                email,
                subscriptionId.toString(),
                startDate.toString()
            )
        );

        assertEquals(
            events.get(events.size() - 1),
            new WelcomeEmailWasSentToNewMember(
                memberId.toString(),
                email,
                subscriptionId.toString()
            )
        );
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"));
    }
}
