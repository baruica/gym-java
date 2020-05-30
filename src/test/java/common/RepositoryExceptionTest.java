package common;

import gym.membership.domain.MemberId;
import gym.plans.domain.PlanId;
import gym.subscriptions.domain.SubscriptionId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryExceptionTest {

    @Test
    void has_a_message_that_tells_which_type_of_aggregate_is_not_found() {
        assertEquals(
            "MemberId [member 42] not found.",
            RepositoryException.notFound(new MemberId("member 42")).getMessage()
        );
        assertEquals(
            "PlanId [plan 42] not found.",
            RepositoryException.notFound(new PlanId("plan 42")).getMessage()
        );
        assertEquals(
            "SubscriptionId [subscription 42] not found.",
            RepositoryException.notFound(new SubscriptionId("subscription 42")).getMessage()
        );
    }
}
