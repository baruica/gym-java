package gym.subscriptions.use_cases;

import gym.subscriptions.domain.Subscription;
import gym.subscriptions.domain.SubscriptionRepository;

import java.time.LocalDate;
import java.util.List;

public record ApplyThreeYearsAnniversaryDiscount(String asOfDate) {
    public record Handler(
        SubscriptionRepository subscriptionRepository
    ) {
        List<Subscription> handle(ApplyThreeYearsAnniversaryDiscount command) {

            var date = LocalDate.parse(command.asOfDate());

            var threeYearsAnniversarySubscriptions = subscriptionRepository.threeYearsAnniversarySubscriptions(date);

            threeYearsAnniversarySubscriptions.forEach(
                subscription -> subscription.applyThreeYearsAnniversaryDiscount(date)
            );

            subscriptionRepository.storeAll(threeYearsAnniversarySubscriptions);

            return threeYearsAnniversarySubscriptions;
        }
    }
}
