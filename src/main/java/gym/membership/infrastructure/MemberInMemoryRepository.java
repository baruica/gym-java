package gym.membership.infrastructure;

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
