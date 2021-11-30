package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SendSummaryUponSubscriptionTest {

    @Test
    void handle() {
        var emailAddress = "luke@gmail.com";
        var startDate = "2018-06-05";
        var endDate = "2018-07-05";
        var price = 250;

        var mailer = new InMemoryMailer();

        var tested = new SendSummaryUponSubscription.Handler(mailer);

        tested.handle(
            new SendSummaryUponSubscription(
                emailAddress,
                startDate,
                endDate,
                price
            )
        );

        assertTrue(
            mailer.subscriptionSummaryEmailWasSentTo(
                new EmailAddress(emailAddress),
                startDate,
                endDate,
                price
            )
        );
    }
}
