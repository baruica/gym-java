package gym.subscriptions.use_cases;

public final class RenewSubscriptionsAutomaticallyCommand {
    public final String asOfDate;

    public RenewSubscriptionsAutomaticallyCommand(String asOfDate) {
        this.asOfDate = asOfDate;
    }
}
