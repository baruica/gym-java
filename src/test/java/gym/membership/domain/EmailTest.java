package gym.membership.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void does_not_allow_invalid_emails() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Email("bob[at]gmail.com");
        });
    }
}
