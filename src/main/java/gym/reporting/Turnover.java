package gym.reporting;

import gym.subscriptions.domain.Subscription;

import java.util.List;
import java.util.Objects;

public final class Turnover {

    private final int total;

    public Turnover(int total) {
        this.total = total;
    }

    public static Turnover monthly(List<Subscription> subscriptions) {
        return new Turnover(
            subscriptions
                .stream()
                .map(Subscription::monthlyTurnover)
                .reduce(0, Integer::sum)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turnover turnover = (Turnover) o;
        return total == turnover.total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }
}
