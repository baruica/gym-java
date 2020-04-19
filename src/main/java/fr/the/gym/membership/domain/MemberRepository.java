package fr.the.gym.membership.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository {

    MemberId nextId();

    void store(Member member);

    Member get(MemberId memberId) throws MemberRepositoryException;

    Optional<Member> findByEmail(EmailAddress email);

    Map<MemberId, Member> threeYearsAnniversaryMembers(LocalDate asOfDate);
}
