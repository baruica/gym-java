package gym.membership.infrastructure;

import common.Aggregate;
import common.AggregateId;
import common.RepositoryException;
import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberId;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class MemberInMemoryRepository implements MemberRepository {

    private final Map<MemberId, Member> members = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Aggregate aggregate) {
        members.put((MemberId) aggregate.id(), (Member) aggregate);
    }

    @Override
    public void storeAll(List<? extends Aggregate> aggregates) {
        aggregates.forEach(this::store);
    }

    @Override
    public Aggregate get(AggregateId aggregateId) throws RepositoryException {
        if (members.containsKey((MemberId) aggregateId)) {
            return members.get(aggregateId);
        }

        throw RepositoryException.notFound(aggregateId);
    }

    @Override
    public Optional<Member> findByEmail(EmailAddress emailAddress) {
        return members.values().stream()
            .filter(member -> emailAddress.equals(member.emailAddress))
            .findFirst();
    }

    @Override
    public List<Member> threeYearsAnniversaryMembers(LocalDate asOfDate) {
        return members.values().stream()
            .filter(member -> member.isThreeYearsAnniversary(asOfDate))
            .collect(Collectors.toList());
    }
}
