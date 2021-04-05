package gym.membership.domain;

public interface Mailer {

    void sendWelcomeEmail(Member member);

    void send3YearsAnniversaryEmail(Member member, Double newSubscriptionPrice);
}
