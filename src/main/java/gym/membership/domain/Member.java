package gym.membership.domain;

import gym.HasAnId;

import java.time.LocalDate;

public final class Member implements HasAnId {

    public final MemberId id;
    public final EmailAddress emailAddress;
    private final LocalDate memberSince;
    private boolean welcomeEmailWasSent = false;
    private boolean threeYearsAnniversaryThankYouEmailWasSent = false;

    @Override
    public String getId() {
        return this.id.id();
    }

    public static record MemberId(String id) {
    }

    private Member(MemberId id, EmailAddress emailAddress, LocalDate memberSince) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.memberSince = memberSince;
    }

    public static Member register(String id, EmailAddress emailAddress, LocalDate memberSince) {
        return new Member(
            new MemberId(id),
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
