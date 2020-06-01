package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.infrastructure.InMemoryMailer;
import gym.membership.infrastructure.MemberInMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterNewMemberTest {

    @Test
    void handle() {
        var repository = new MemberInMemoryRepository();
        var memberId = repository.nextId();

        var email = "luke@gmail.com";

        assertTrue(repository.findByEmail(new EmailAddress(email)).isEmpty());

        var subscriptionId = "subscriptionId def";
        var subscriptionStartDate = "2018-06-05";
        var command = new RegisterNewMemberCommand(
            memberId,
            subscriptionId,
            subscriptionStartDate,
            email
        );

        var mailer = new InMemoryMailer();

        var tested = new RegisterNewMember(repository, mailer);
        var member = tested.handle(command);

        if (member != null) {
            assertEquals(memberId, member.id.toString());
        }

        assertTrue(mailer.welcomeEmailWasSentTo("luke@gmail.com"));
    }
}
