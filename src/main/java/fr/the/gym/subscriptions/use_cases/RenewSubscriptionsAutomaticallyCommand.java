package fr.the.gym.subscriptions.use_cases;

import java.time.LocalDate;

public final class RenewSubscriptionsAutomaticallyCommand {
    public final LocalDate asOfDate;

    public RenewSubscriptionsAutomaticallyCommand(LocalDate asOfDate) {
        this.asOfDate = asOfDate;
    }
}
