package gym.membership.domain;

import common.DomainEvent;

import java.util.Objects;

public abstract class MemberEvent implements DomainEvent {

    public final String memberId;

    public MemberEvent(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String aggregateId() {
        return memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEvent that = (MemberEvent) o;
        return memberId.equals(that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
