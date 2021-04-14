package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.Member.MemberId;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class InMemoryMemberRepository implements MemberRepository {

    private final Map<MemberId, Member> members = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Member member) {
        members.put(member.id, member);
    }

    @Override
    public Member get(MemberId memberId) {
        if (members.containsKey(memberId)) {
            return members.get(memberId);
        }

        throw new RuntimeException(memberId + " not found.");
    }

    @Override
    public Optional<Member> findByEmailAddress(EmailAddress emailAddress) {
        return members.values().stream()
            .filter(member -> Objects.equals(emailAddress, member.emailAddress))
            .findFirst();
    }

    @Override
    public List<Member> threeYearsAnniversaryMembers(LocalDate date) {
        return members.values().stream()
            .filter(member -> member.isThreeYearsAnniversary(date))
            .collect(Collectors.toList());
    }
}
