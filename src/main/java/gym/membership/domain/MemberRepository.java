package gym.membership.domain;

import common.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends Repository {

    Optional<Member> findByEmail(EmailAddress emailAddress);

    List<Member> threeYearsAnniversaryMembers(LocalDate asOfDate);
}
