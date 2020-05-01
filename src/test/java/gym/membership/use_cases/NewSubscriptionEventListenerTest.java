package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.subscriptions.domain.NewSubscription;
import gym.subscriptions.domain.SubscriptionId;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewSubscriptionEventListenerTest {

    @Test
    public void handle() {
        var memberRepository = new MemberInMemoryRepository();
        var subscriptionStartDate = LocalDate.now();
        var email = new EmailAddress("luke@gmail.com");

        var tested = new NewSubscriptionEventListener(memberRepository);

        assertTrue(memberRepository.findByEmail(email).isEmpty());

        var event = tested.handle(
            new NewSubscription(
                new SubscriptionId("def"),
                subscriptionStartDate,
                email.toString()
            )
        );

        Optional<Member> knownMemberOpt = memberRepository.findByEmail(email);
        assertTrue(knownMemberOpt.isPresent());
        assertEquals(email, knownMemberOpt.get().email);

        assertEquals(email.toString(), event.memberEmail);
    }
}
