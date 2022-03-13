package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

public record ApplyThreeYearsAnniversaryDiscount(LocalDate asOfDate) {
    public record Handler(
        SubscriptionRepository subscriptionRepository
    ) {
        List<Subscription> handle(ApplyThreeYearsAnniversaryDiscount command) {

            var threeYearsAnniversarySubscriptions = subscriptionRepository.threeYearsAnniversarySubscriptions(command.asOfDate());

            threeYearsAnniversarySubscriptions.forEach(
                subscription -> subscription.applyThreeYearsAnniversaryDiscount(command.asOfDate())
            );

            subscriptionRepository.storeAll(threeYearsAnniversarySubscriptions);

            return threeYearsAnniversarySubscriptions;
        }
    }
}
