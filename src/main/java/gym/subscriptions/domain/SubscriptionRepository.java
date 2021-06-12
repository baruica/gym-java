package gym.subscriptions.domain;

import gym.Repository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends Repository<Subscription> {

    List<Subscription> ongoingSubscriptions(LocalDate date);

    List<Subscription> endedMonthlySubscriptions(LocalDate date);

    List<Subscription> threeYearsAnniversarySubscriptions(LocalDate date);
}
