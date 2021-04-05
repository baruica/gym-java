package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

public class ApplyThreeYearsAnniversaryDiscount {

    private final SubscriptionRepository subscriptionRepository;

    public ApplyThreeYearsAnniversaryDiscount(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    List<Subscription> handle(ApplyThreeYearsAnniversaryDiscountCommand command) {

        var date = LocalDate.parse(command.asOfDate());

        var threeYearsAnniversarySubscriptions = subscriptionRepository.threeYearsAnniversarySubscriptions(date);

        threeYearsAnniversarySubscriptions.forEach(
            subscription -> subscription.applyThreeYearsAnniversaryDiscount(date)
        );

        subscriptionRepository.storeAll(threeYearsAnniversarySubscriptions);

        return threeYearsAnniversarySubscriptions;
    }
}
