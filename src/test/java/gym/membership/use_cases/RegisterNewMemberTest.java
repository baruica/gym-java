package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member;
import gym.membership.domain.MemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegisterNewMemberTest {

    @Test
    void handle() {
        MemberRepository repository = new InMemoryMemberRepository();
        var memberId = repository.nextId();

        var emailAddress = new EmailAddress("luke@gmail.com");

        assertTrue(repository.findByEmailAddress(emailAddress).isEmpty());

        var subscriptionId = "subscriptionId def";
        var subscriptionStartDate = "2018-06-05";
        var command = new RegisterNewMemberCommand(
            memberId,
            subscriptionId,
            subscriptionStartDate,
            emailAddress.value()
        );

        var mailer = new InMemoryMailer();

        var tested = new RegisterNewMember(repository, mailer);
        var member = tested.handle(command);

        if (member != null) {
            assertEquals(new Member.MemberId(memberId), member.id);
        }

        assertTrue(mailer.welcomeEmailWasSentTo(emailAddress));
    }
}
