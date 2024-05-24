package gym.membership.domain;

import java.util.regex.Pattern;

public record EmailAddress(String value) {

    public EmailAddress {
        if (!Pattern.matches("^[\\w-_.+]*[\\w-_.]@(\\w+\\.)+\\w+\\w$", value)) {
            throw new IllegalArgumentException(STR."EmailAddress must be a valid email address, was [\{value}]");
        }
    }
}
