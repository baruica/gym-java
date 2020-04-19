package fr.craft.gym.membership.domain;

public interface Mailer {

    void sendEmail(EmailAddress email, String message);
}
