package gym.membership.use_cases;

import gym.membership.domain.Email;
import gym.membership.domain.EmailAddress;
import gym.membership.domain.Mailer;
import gym.membership.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InMemoryMailer implements Mailer {

    private final Map<String, Email> sentEmails = new HashMap<>();

    @Override
    public void sendWelcomeEmail(Member member) {
        sentEmails.put(
            UUID.randomUUID().toString(),
            Email.welcome(member.emailAddress)
        );

        member.markWelcomeEmailAsSent();
    }

    @Override
    public void send3YearsAnniversaryEmail(Member member, Double newSubscriptionPrice) {
        sentEmails.put(
            UUID.randomUUID().toString(),
            Email.threeYearsAnniversary(member.emailAddress, newSubscriptionPrice)
        );

        member.mark3YearsAnniversaryThankYouEmailAsSent();
    }

    public boolean welcomeEmailWasSentTo(EmailAddress emailAddress) {
        return sentEmails.containsValue(
            Email.welcome(emailAddress)
        );
    }

    public boolean threeYearsAnniversaryWasSentTo(EmailAddress emailAddress, Double newSubscriptionPrice) {
        return sentEmails.containsValue(
            Email.threeYearsAnniversary(emailAddress, newSubscriptionPrice)
        );
    }
}
