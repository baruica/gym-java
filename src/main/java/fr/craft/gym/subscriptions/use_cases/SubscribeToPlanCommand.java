package fr.craft.gym.subscriptions.use_cases;

import fr.craft.gym.subscriptions.domain.ChosenPlan;
import fr.craft.gym.subscriptions.domain.SubscriptionId;

import java.time.LocalDate;

public final class SubscribeToPlanCommand {
    public final ChosenPlan chosenPlan;
    public final SubscriptionId subscriptionId;
    public final LocalDate startDate;
    public final Boolean isStudent;
    public final String email;

    public SubscribeToPlanCommand(ChosenPlan chosenPlan, SubscriptionId subscriptionId, LocalDate startDate, Boolean isStudent, String email) {
        this.chosenPlan = chosenPlan;
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.isStudent = isStudent;
        this.email = email;
    }
}
