package gym.subscriptions.use_cases;

import gym.plans.domain.PlanId;

import java.time.LocalDate;

public final class SubscribeToPlanCommand {
    public final PlanId planId;
    public final Integer planPrice;
    public final Integer planDurationInMonths;
    public final LocalDate startDate;
    public final Boolean isStudent;
    public final String email;

    public SubscribeToPlanCommand(PlanId planId, Integer planPrice, Integer planDurationInMonths, LocalDate startDate, Boolean isStudent, String email) {
        this.planId = planId;
        this.planPrice = planPrice;
        this.planDurationInMonths = planDurationInMonths;
        this.startDate = startDate;
        this.isStudent = isStudent;
        this.email = email;
    }
}
