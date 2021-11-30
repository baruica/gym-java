package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;

public record SendSummaryUponSubscription(
    String email,
    String startDate,
    String endDate,
    Integer price
) {
    record Handler(
        Mailer mailer
    ) {
        public void handle(SendSummaryUponSubscription command) {

            mailer.sendSubscriptionSummary(
                new EmailAddress(command.email()),
                command.startDate(),
                command.endDate(),
                command.price()
            );
        }
    }
}
