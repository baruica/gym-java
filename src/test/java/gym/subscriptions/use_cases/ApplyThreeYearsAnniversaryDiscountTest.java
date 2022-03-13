package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplyThreeYearsAnniversaryDiscountTest {

    @Test
    void handle() {
        var repository = new InMemorySubscriptionRepository();

        var subscription = Subscription.subscribe(
            repository.nextId(),
            LocalDate.parse("2015-07-09"),
            12,
            1300,
            false
        );
        subscription.renew();
        subscription.renew();
        repository.store(subscription);

        var tested = new ApplyThreeYearsAnniversaryDiscount.Handler(repository);

        var subscriptionsBeforeThreeYearsAnniversary = tested.handle(
            new ApplyThreeYearsAnniversaryDiscount(LocalDate.parse("2018-07-08"))
        );
        assertEquals(0, subscriptionsBeforeThreeYearsAnniversary.size());

        var subscriptionsWithThreeYearsDiscount = tested.handle(
            new ApplyThreeYearsAnniversaryDiscount(LocalDate.parse("2018-07-12"))
        );
        assertEquals(1111.5, subscriptionsWithThreeYearsDiscount.get(subscriptionsWithThreeYearsDiscount.size() - 1).price.amount());

        var subscriptionsAfterThreeYearsAnniversary = tested.handle(
            new ApplyThreeYearsAnniversaryDiscount(LocalDate.parse("2018-07-09"))
        );
        assertEquals(0, subscriptionsAfterThreeYearsAnniversary.size());
    }
}
