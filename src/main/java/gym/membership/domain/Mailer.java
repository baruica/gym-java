package gym.membership.domain;

public interface Mailer {

    void sendEmail(Email email, String message);
}
