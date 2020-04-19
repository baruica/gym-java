package fr.the.gym.membership.domain;

public final class WelcomeEmailWasSentToNewMember {

    public final MemberId memberId;

    public WelcomeEmailWasSentToNewMember(final MemberId memberId) {
        this.memberId = memberId;
    }
}
