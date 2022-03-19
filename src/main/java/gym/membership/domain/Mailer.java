package gym.membership.domain;

import java.time.LocalDate;

public interface Mailer {

    void sendWelcomeEmail(Member member);

    void sendSubscriptionSummary(EmailAddress emailAddress, LocalDate startDate, LocalDate endDate, Integer price);

    void send3YearsAnniversaryEmail(Member member, Double newSubscriptionPrice);
}
