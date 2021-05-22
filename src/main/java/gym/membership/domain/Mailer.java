package gym.membership.domain;

public interface Mailer {

    void sendWelcomeEmail(Member member);

    void sendSubscriptionSummary(EmailAddress emailAddress, String startDate, String endDate, Integer price);

    void send3YearsAnniversaryEmail(Member member, Double newSubscriptionPrice);
}
