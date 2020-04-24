package gym.membership.use_cases;

import gym.membership.domain.Member;
import gym.membership.domain.MemberId;
import gym.membership.domain.NewMemberSubscribed;
import gym.membership.infrastructure.InMemoryMailer;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.membership.infrastructure.MemberRepositoryException;
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
            "bob@gmail.com",
            "subscriptionId def",
            LocalDate.now()
        );
        var memberRepository = new MemberInMemoryRepository();
        memberRepository.store(member);

        var mailer = new InMemoryMailer();

        var tested = new SendWelcomeEmailToNewMembers(memberRepository, mailer);

        var event = tested.handle(
            new NewMemberSubscribed(memberId, member.email)
        );

        assertEquals(memberId.toString(), event.memberId);
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"));
    }
}
