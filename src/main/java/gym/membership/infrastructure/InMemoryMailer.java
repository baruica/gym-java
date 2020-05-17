package gym.membership.infrastructure;

import gym.membership.domain.Email;
import gym.membership.domain.Mailer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InMemoryMailer implements Mailer {

    public final Map<String, String> sentEmails = new HashMap<>();

    @Override
    public void sendEmail(Email email, String message) {
        sentEmails.put(UUID.randomUUID().toString(), message);
    }
}
