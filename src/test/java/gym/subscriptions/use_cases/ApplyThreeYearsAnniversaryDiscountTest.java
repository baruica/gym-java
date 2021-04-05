package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplyThreeYearsAnniversaryDiscountTest {

    @Test
    void handle() {
        var repository = new InMemorySubscriptionRepository();

        repository.store(
            Subscription.subscribe(
                repository.nextId(),
                LocalDate.parse("2015-07-09"),
                12,
                1300,
                false
            )
        );

        var tested = new ApplyThreeYearsAnniversaryDiscount(repository);

        var subscriptionsBeforeThreeYearsAnniversary = tested.handle(
            new ApplyThreeYearsAnniversaryDiscountCommand("2018-07-08")
        );
        assertEquals(0, subscriptionsBeforeThreeYearsAnniversary.size());

        var subscriptionsWithThreeYearsDiscount = tested.handle(
            new ApplyThreeYearsAnniversaryDiscountCommand("2018-07-09")
        );
        assertEquals(1111.5, subscriptionsWithThreeYearsDiscount.get(subscriptionsWithThreeYearsDiscount.size() - 1).price.amount());

        var subscriptionsAfterThreeYearsAnniversary = tested.handle(
            new ApplyThreeYearsAnniversaryDiscountCommand("2018-07-10")
        );
        assertEquals(0, subscriptionsAfterThreeYearsAnniversary.size());
    }
}
