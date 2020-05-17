package gym.membership.use_cases;

import gym.membership.domain.Email;
import gym.membership.domain.NewMemberSubscribed;
import gym.membership.infrastructure.MemberInMemoryRepository;
import gym.subscriptions.domain.NewSubscription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewSubscriptionEventListenerTest {

    @Test
    public void handle() {
        var memberRepository = new MemberInMemoryRepository();

        var email = "luke@gmail.com";

        assertTrue(memberRepository.findByEmail(new Email(email)).isEmpty());

        var subscriptionId = "subscriptionId def";
        var subscriptionStartDate = "2018-06-05";
        NewSubscription newSubscriptionEvent = new NewSubscription(
            subscriptionId,
            subscriptionStartDate,
            email
        );

        var tested = new NewSubscriptionEventListener(memberRepository);
        var events = tested.handle(newSubscriptionEvent);

        assertEquals(
            events.get(events.size() - 1),
            new NewMemberSubscribed(
                events.get(events.size() - 1).aggregateId,
                email,
                subscriptionId,
                subscriptionStartDate
            )
        );
    }
}
