package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;

record SendSummaryUponSubscription(
    Mailer mailer
) {
    public void handle(SendSummaryUponSubscriptionCommand command) {

        mailer.sendSubscriptionSummary(
            new EmailAddress(command.email()),
            command.startDate(),
            command.endDate(),
            command.price()
        );
    }
}
