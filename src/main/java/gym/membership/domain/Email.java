package gym.membership.domain;

import java.time.LocalDate;

public sealed interface Email {

    record Welcome(EmailAddress emailAddress, String emailBody) implements Email {
        public Welcome(EmailAddress emailAddress) {
            this(
                emailAddress,
                "Welcome to our gym :)"
            );
        }
    }

    record SubscriptionSummary(EmailAddress emailAddress, String emailBody) implements Email {
        public SubscriptionSummary(EmailAddress emailAddress, LocalDate startDate, LocalDate endDate, Integer price) {
            this(
                emailAddress,
                STR."Thank you for subscribing, this subscription will run from \{startDate} until \{endDate}, and will only cost you \{price}!"
            );
        }
    }

    record ThreeYearsAnniversary(EmailAddress emailAddress, String emailBody) implements Email {
        public ThreeYearsAnniversary(EmailAddress emailAddress, Double newSubscriptionPrice) {
            this(
                emailAddress,
                STR."To thank you for your loyalty, we've applied a 5% discount on your subscription, you will now pay \{newSubscriptionPrice} !"
            );
        }
    }
}
