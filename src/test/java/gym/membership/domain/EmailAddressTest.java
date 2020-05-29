package gym.membership.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailAddressTest {

    @Test
    public void does_not_allow_invalid_emails() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EmailAddress("bob[at]gmail.com"));
    }
}
