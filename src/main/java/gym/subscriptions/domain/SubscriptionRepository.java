package gym.subscriptions.domain;

import common.Repository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends Repository {

    List<Subscription> ongoingSubscriptions(LocalDate date);

    List<Subscription> endedSubscriptions(LocalDate date);
}
