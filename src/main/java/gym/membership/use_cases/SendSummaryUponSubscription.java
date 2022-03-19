package gym.membership.use_cases;

import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;

import java.time.LocalDate;

public record SendSummaryUponSubscription(
    EmailAddress email,
    LocalDate startDate,
    LocalDate endDate,
    Integer price
) {
    record Handler(
        Mailer mailer
    ) {
        public void handle(SendSummaryUponSubscription command) {

            mailer.sendSubscriptionSummary(
                command.email(),
                command.startDate(),
                command.endDate(),
                command.price()
            );
        }
    }
}
