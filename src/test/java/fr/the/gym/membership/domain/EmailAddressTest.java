package fr.the.gym.membership.domain;

import org.junit.Test;

public class EmailAddressTest {

    @Test(expected = IllegalArgumentException.class)
    public void does_not_allow_invalid_emails() {
        new EmailAddress("bob[at]gmail.com");
    }
}
