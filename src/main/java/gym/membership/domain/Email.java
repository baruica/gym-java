package gym.membership.domain;

public final record Email(EmailAddress emailAddress, String emailBody) {

    public static Email welcome(EmailAddress emailAddress) {
        return new Email(
            emailAddress,
            "Thank you for subscribing " + emailAddress + " !"
        );
    }

    public static Email threeYearsAnniversary(EmailAddress emailAddress) {
        return new Email(
            emailAddress,
            "Thank you for your loyalty " + emailAddress + " !"
        );
    }
}
