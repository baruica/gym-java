package fr.the.gym.membership.infrastructure;

import fr.the.gym.membership.domain.EmailAddress;
import fr.the.gym.membership.domain.Mailer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InMemoryMailer implements Mailer {

    public final Map<String, String> sentEmails = new HashMap<>();

    @Override
    public void sendEmail(EmailAddress email, String message) {
        sentEmails.put(UUID.randomUUID().toString(), message);
    }
}
