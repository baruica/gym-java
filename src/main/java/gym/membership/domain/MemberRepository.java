package gym.membership.domain;

import gym.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends Repository<Member> {

    Optional<Member> findByEmailAddress(EmailAddress emailAddress);

    List<Member> threeYearsAnniversaryMembers(LocalDate date);
}
