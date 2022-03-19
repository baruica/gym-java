package gym.membership.use_cases;

import com.github.f4b6a3.ulid.UlidCreator;
import gym.membership.domain.Email;
import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;
import gym.membership.domain.Member;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class InMemoryMailer implements Mailer {

    private final Map<String, Email> sentEmails = new HashMap<>();

    @Override
    public void sendWelcomeEmail(Member member) {
        sentEmails.put(
            UlidCreator.getUlid().toString(),
            new Email.Welcome(member.emailAddress)
        );
        member.markWelcomeEmailAsSent();
    }

    @Override
    public void sendSubscriptionSummary(EmailAddress emailAddress, LocalDate startDate, LocalDate endDate, Integer price) {
        sentEmails.put(
            UlidCreator.getUlid().toString(),
            new Email.SubscriptionSummary(emailAddress, startDate, endDate, price)
        );
    }

    @Override
    public void send3YearsAnniversaryEmail(Member member, Double newSubscriptionPrice) {
        sentEmails.put(
            UlidCreator.getUlid().toString(),
            new Email.ThreeYearsAnniversary(member.emailAddress, newSubscriptionPrice)
        );
        member.mark3YearsAnniversaryThankYouEmailAsSent();
    }

    public boolean welcomeEmailWasSentTo(EmailAddress emailAddress) {
        return sentEmails.containsValue(
            new Email.Welcome(emailAddress)
        );
    }

    public boolean subscriptionSummaryEmailWasSentTo(EmailAddress emailAddress, LocalDate startDate, LocalDate endDate, int price) {
        return sentEmails.containsValue(
            new Email.SubscriptionSummary(emailAddress, startDate, endDate, price)
        );
    }

    public boolean threeYearsAnniversaryWasSentTo(EmailAddress emailAddress, Double newSubscriptionPrice) {
        return sentEmails.containsValue(
            new Email.ThreeYearsAnniversary(emailAddress, newSubscriptionPrice)
        );
    }
}
