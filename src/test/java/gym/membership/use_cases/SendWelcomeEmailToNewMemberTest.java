package gym.membership.use_cases;

import common.RepositoryException;
import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberId;
import gym.membership.domain.WelcomeEmailWasSentToNewMember;
import gym.membership.infrastructure.InMemoryMailer;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.subscriptions.domain.SubscriptionId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendWelcomeEmailToNewMemberTest {

    @Test
    public void handle() throws RepositoryException {

        var memberId = new MemberId("abc");
        var email = "bob@gmail.com";
        var subscriptionId = new SubscriptionId("def");
        var startDate = LocalDate.now();
        var member = Member.register(
            memberId,
            new EmailAddress(email),
            subscriptionId,
            startDate
        );
        var memberRepository = new MemberInMemoryRepository();
        memberRepository.store(member);

        var mailer = new InMemoryMailer();

        var tested = new SendWelcomeEmailToNewMember(memberRepository, mailer);

        var events = tested.handle(
            new SendWelcomeEmailToNewMemberCommand(
                memberId.toString()
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
        assertTrue(mailer.welcomeEmailWasSentTo("bob@gmail.com"));
    }
}
