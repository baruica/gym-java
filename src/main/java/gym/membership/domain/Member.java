package gym.membership.domain;

import gym.Aggregate;

import java.time.LocalDate;

public final class Member implements Aggregate {

    public final MemberId id;
    public final EmailAddress emailAddress;
    private final LocalDate memberSince;
    private boolean welcomeEmailWasSent = false;
    private boolean threeYearsAnniversaryThankYouEmailWasSent = false;

    @Override
    public String getId() {
        return this.id.id();
    }

    public record MemberId(String id) {
        @Override
        public String toString() {
            return id;
        }
    }

    private Member(MemberId id, EmailAddress emailAddress, LocalDate memberSince) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.memberSince = memberSince;
    }

    public static Member register(MemberId id, EmailAddress emailAddress, LocalDate memberSince) {
        return new Member(
            id,
            emailAddress,
            memberSince
        );
    }

    public Boolean isThreeYearsAnniversary(LocalDate date) {
        return date.minusYears(3).isEqual(memberSince);
    }

    public void markWelcomeEmailAsSent() {
        welcomeEmailWasSent = true;
    }

    public void mark3YearsAnniversaryThankYouEmailAsSent() {
        threeYearsAnniversaryThankYouEmailWasSent = true;
    }
}
