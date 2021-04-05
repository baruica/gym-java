package gym.membership.domain;

public final record Email(EmailAddress emailAddress, String emailBody) {

    public static Email welcome(EmailAddress emailAddress) {
        return new Email(
            emailAddress,
            "Thank you for subscribing " + emailAddress + " !"
        );
    }

    public static Email threeYearsAnniversary(EmailAddress emailAddress, Double newSubscriptionPrice) {
        return new Email(
            emailAddress,
            "To thank you for your loyalty, we've applied a 5% discount on your subscription, you will now pay " + newSubscriptionPrice + " !"
        );
    }
}
