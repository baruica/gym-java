package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Member.MemberId;
import gym.membership.domain.MemberRepository;
import gym.subscriptions.domain.Subscription.SubscriptionId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        var command = new RegisterNewMember(
            new MemberId(memberId),
            new SubscriptionId(subscriptionId),
            LocalDate.parse(subscriptionStartDate),
            new EmailAddress(emailAddress.value())
        );

        var mailer = new InMemoryMailer();

        var tested = new RegisterNewMember.Handler(repository, mailer);
        var member = tested.handle(command);

        if (member != null) {
            assertEquals(new MemberId(memberId), member.id);
        }

        assertTrue(mailer.welcomeEmailWasSentTo(emailAddress));
    }
}
