package fr.craft.gym.membership.use_cases;

import fr.craft.gym.membership.domain.Member;
import fr.craft.gym.membership.domain.MemberId;
import fr.craft.gym.membership.domain.MemberRepositoryException;
import fr.craft.gym.membership.domain.NewMemberSubscribed;
import fr.craft.gym.membership.infrastructure.InMemoryMailer;
import fr.craft.gym.membership.infrastructure.MemberInMemoryRepository;
import fr.craft.gym.subscriptions.domain.SubscriptionId;
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
            new SubscriptionId("def"),
            LocalDate.now()
        );
        var memberRepository = new MemberInMemoryRepository();
        memberRepository.store(member);

        var mailer = new InMemoryMailer();

        var tested = new SendWelcomeEmailToNewMembers(memberRepository, mailer);

        var event = tested.handle(
            new NewMemberSubscribed(memberId, member.email)
        );

        assertEquals(memberId, event.memberId);
        assertTrue(mailer.sentEmails.containsValue("Thank you for subscribing bob@gmail.com !"));
    }
}
