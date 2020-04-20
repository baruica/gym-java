package gym.subscriptions.domain;

import java.time.LocalDate;
import java.util.Map;

public interface SubscriptionRepository {

    SubscriptionId nextId();

    void store(Subscription subscription);

    void storeAll(Map<SubscriptionId, Subscription> subscriptions);

    Subscription get(SubscriptionId subscriptionId) throws SubscriptionRepositoryException;

    Map<SubscriptionId, Subscription> ongoingSubscriptions(LocalDate date);

    Map<SubscriptionId, Subscription> endedSubscriptions(LocalDate date);
}
