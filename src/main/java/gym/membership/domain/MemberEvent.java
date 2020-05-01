package gym.membership.domain;

public class MemberEvent {
    public final String aggregateId;

    public MemberEvent(String aggregateId) {
        this.aggregateId = aggregateId;
    }
}
