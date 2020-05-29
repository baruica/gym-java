package gym.subscriptions.domain;

import gym.subscriptions.infrastructure.SubscriptionRepositoryException;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository {

    SubscriptionId nextId();

    void store(Subscription subscription);

    void storeAll(List<Subscription> subscriptions);

    Subscription get(SubscriptionId subscriptionId) throws SubscriptionRepositoryException;

    List<Subscription> ongoingSubscriptions(LocalDate date);

    List<Subscription> endedSubscriptions(LocalDate date);
}
