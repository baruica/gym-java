package gym.subscriptions.domain;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository {

    String nextId();

    void store(Subscription subscription);

    void storeAll(List<? extends Subscription> subscriptions);

    List<Subscription> ongoingSubscriptions(LocalDate date);

    List<Subscription> endedMonthlySubscriptions(LocalDate date);
}
