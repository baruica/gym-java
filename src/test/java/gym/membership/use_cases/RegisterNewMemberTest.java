package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.NewMemberRegistered;
import gym.membership.infrastructure.MemberInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterNewMemberTest {

    @Test
    void handle() {
        var memberRepository = new MemberInMemoryRepository();

        var email = "luke@gmail.com";

        assertTrue(memberRepository.findByEmail(new EmailAddress(email)).isEmpty());

        var subscriptionId = "subscriptionId def";
        var subscriptionStartDate = "2018-06-05";
        var command = new RegisterNewMemberCommand(
            subscriptionId,
            subscriptionStartDate,
            email
        );

        var tested = new RegisterNewMember(memberRepository);
        var events = tested.handle(command);

        assertEquals(
            events.get(events.size() - 1),
            new NewMemberRegistered(
                events.get(events.size() - 1).aggregateId(),
                email,
                subscriptionId,
                subscriptionStartDate
            )
        );
    }
}
