package gym.membership.domain;

public final class NewMemberSubscribed {

    public final MemberId memberId;
    public final EmailAddress memberEmail;

    public NewMemberSubscribed(MemberId memberId, EmailAddress memberEmail) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
    }
}
