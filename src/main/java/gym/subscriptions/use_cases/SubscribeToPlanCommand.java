package gym.subscriptions.use_cases;

public final class SubscribeToPlanCommand {
    public final String planId;
    public final Integer planPrice;
    public final Integer planDurationInMonths;
    public final String startDate;
    public final Boolean isStudent;
    public final String email;

    public SubscribeToPlanCommand(String planId, Integer planPrice, Integer planDurationInMonths, String startDate, Boolean isStudent, String email) {
        this.planId = planId;
        this.planPrice = planPrice;
        this.planDurationInMonths = planDurationInMonths;
        this.startDate = startDate;
        this.isStudent = isStudent;
        this.email = email;
    }
}
