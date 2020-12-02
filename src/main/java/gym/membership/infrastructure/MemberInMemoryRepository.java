package gym.membership.infrastructure;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class MemberInMemoryRepository implements MemberRepository {

    private final Map<Member.MemberId, Member> members = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Member member) {
        members.put(member.id, member);
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
