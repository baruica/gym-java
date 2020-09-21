package gym.membership.domain;

import java.util.Objects;

public final class Email {

    private final EmailAddress emailAddress;
    private final String emailBody;

    private Email(EmailAddress emailAddress, String emailBody) {
        this.emailAddress = emailAddress;
        this.emailBody = emailBody;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return emailAddress.equals(email.emailAddress) &&
            emailBody.equals(email.emailBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, emailBody);
    }
}
