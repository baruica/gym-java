package gym.membership.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {

    private final String email;

    public Email(String email) {
        if (!Pattern.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$", email)) {
            throw new IllegalArgumentException();
        }

        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email that = (Email) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
