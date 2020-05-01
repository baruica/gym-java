package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberId;
import gym.membership.domain.NewMemberSubscribed;
import gym.membership.infrastructure.InMemoryMailer;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.membership.infrastructure.MemberRepositoryException;
import gym.subscriptions.domain.SubscriptionId;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SendWelcomeEmailToNewMembersTest {

    @Test
    public void handle() throws MemberRepositoryException {

        var memberId = new MemberId("abc");
        var member = new Member(
            memberId,
            new EmailAddress("bob@gmail.com"),
            new SubscriptionId("subscriptionId def"),
            LocalDate.now()
        );
        var memberRepository = new MemberInMemoryRepository();
        memberRepository.store(member);

        var mailer = new InMemoryMailer();

        var tested = new SendWelcomeEmailToNewMembers(memberRepository, mailer);

        var event = tested.handle(
            new NewMemberSubscribed(
                memberId.toString(),
                member.email.toString(),
                member.subscriptionId.toString(),
                member.memberSince.toString()
            )
        );

        assertEquals(memberId.toString(), event.memberId);
        assertEquals(member.email.toString(), event.memberEmail);
        assertEquals(member.subscriptionId.toString(), event.memberSubscriptionId);
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"));
    }
}
