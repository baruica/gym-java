package gym.membership.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public final class EmailAddress {

    private final String value;

    public EmailAddress(String email) {
        if (!Pattern.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$", email)) {
            throw new IllegalArgumentException();
        }

        this.value = email;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
