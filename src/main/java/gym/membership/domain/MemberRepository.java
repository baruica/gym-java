package gym.membership.domain;

import gym.membership.domain.Member.MemberId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    String nextId();

    void store(Member member);

    Member get(MemberId memberId);

    Optional<Member> findByEmailAddress(EmailAddress emailAddress);

    List<Member> threeYearsAnniversaryMembers(LocalDate date);
}
