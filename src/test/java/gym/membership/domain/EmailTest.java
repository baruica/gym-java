package gym.membership.domain;

import org.junit.Test;

public class EmailTest {

    @Test(expected = IllegalArgumentException.class)
    public void does_not_allow_invalid_emails() {
        new Email("bob[at]gmail.com");
    }
}
