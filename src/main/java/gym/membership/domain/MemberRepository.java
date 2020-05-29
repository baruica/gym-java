package gym.membership.domain;

import gym.membership.infrastructure.MemberRepositoryException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    MemberId nextId();

    void store(Member member);

    Member get(MemberId memberId) throws MemberRepositoryException;

    Optional<Member> findByEmail(EmailAddress emailAddress);

    List<Member> threeYearsAnniversaryMembers(LocalDate asOfDate);
}
