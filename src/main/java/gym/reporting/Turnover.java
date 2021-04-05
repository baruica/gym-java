package gym.reporting;

import gym.subscriptions.domain.Subscription;

import java.util.List;

public final record Turnover(int total) {

    public static Turnover monthly(List<Subscription> subscriptions) {
        return new Turnover(
            subscriptions
                .stream()
                .map(Subscription::monthlyTurnover)
                .reduce(0, Integer::sum)
        );
    }
}
