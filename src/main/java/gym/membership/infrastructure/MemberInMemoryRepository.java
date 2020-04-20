package gym.membership.infrastructure;

import gym.membership.domain.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public final class MemberInMemoryRepository implements MemberRepository {

    private final Map<MemberId, Member> members = new HashMap<>();

    @Override
    public MemberId nextId() {
        return new MemberId(UUID.randomUUID().toString());
    }

    @Override
    public void store(Member member) {
        members.put(member.id, member);
    }

    @Override
    public Member get(MemberId memberId) throws MemberRepositoryException {
        if (members.containsKey(memberId)) {
            return members.get(memberId);
        }

        throw MemberRepositoryException.notFound(memberId);
    }

    @Override
    public Optional<Member> findByEmail(EmailAddress email) {
        return members.values().stream()
            .filter(member -> email.equals(member.email))
            .findFirst();
    }

    @Override
    public Map<MemberId, Member> threeYearsAnniversaryMembers(LocalDate asOfDate) {
        return members.entrySet().stream()
            .filter(member -> member.getValue().isThreeYearsAnniversary(asOfDate))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
