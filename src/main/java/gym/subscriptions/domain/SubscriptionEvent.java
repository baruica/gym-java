package gym.subscriptions.domain;

public class SubscriptionEvent {
    public final String aggregateId;

    public SubscriptionEvent(String aggregateId) {
        this.aggregateId = aggregateId;
    }
}
