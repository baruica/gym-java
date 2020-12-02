package gym.membership.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    String nextId();

    void store(Member member);

    Optional<Member> findByEmail(EmailAddress emailAddress);

    List<Member> threeYearsAnniversaryMembers(LocalDate asOfDate);
}
