package fr.the.gym.membership.use_cases;

import fr.the.gym.membership.domain.EmailAddress;
import fr.the.gym.membership.infrastructure.MemberInMemoryRepository;
import fr.the.gym.subscriptions.domain.PlanSubscribed;
import fr.the.gym.subscriptions.domain.SubscriptionId;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlanSubscribedEventListenerTest {

    @Test
    public void handle() {
        var memberRepository = new MemberInMemoryRepository();
        var subscriptionStartDate = LocalDate.now();
        var email = new EmailAddress("luke@gmail.com");

        var tested = new PlanSubscribedEventListener(memberRepository);

        assertTrue(memberRepository.findByEmail(email).isEmpty());

        var event = tested.handle(
            new PlanSubscribed(
                new SubscriptionId("def"),
                subscriptionStartDate,
                email.toString()
            )
        );

        assertTrue(memberRepository.findByEmail(email).isPresent());
        assertEquals(email, event.memberEmail);
    }
}
