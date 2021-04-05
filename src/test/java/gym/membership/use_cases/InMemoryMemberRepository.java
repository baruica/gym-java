package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class InMemoryMemberRepository implements MemberRepository {

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
    public List<Member> threeYearsAnniversaryMembers(LocalDate date) {
        return members.values().stream()
            .filter(member -> member.isThreeYearsAnniversary(date))
            .collect(Collectors.toList());
    }
}
