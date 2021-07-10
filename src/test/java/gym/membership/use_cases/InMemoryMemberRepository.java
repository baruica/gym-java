package gym.membership.use_cases;

import gym.InMemoryRepository;
import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class InMemoryMemberRepository extends InMemoryRepository<Member> implements MemberRepository {

    @Override
    public Optional<Member> findByEmailAddress(EmailAddress emailAddress) {
        return aggregates.values().stream()
            .filter(member -> Objects.equals(emailAddress, member.emailAddress))
            .findFirst();
    }

    @Override
    public List<Member> threeYearsAnniversaryMembers(LocalDate date) {
        return aggregates.values().stream()
            .filter(member -> member.isThreeYearsAnniversary(date))
            .collect(Collectors.toList());
    }
}
