package gym.membership.domain;

public final class NewMemberSubscribed {

    public final String memberId;
    public final String memberEmail;

    public NewMemberSubscribed(MemberId memberId, EmailAddress memberEmail) {
        this.memberId = memberId.toString();
        this.memberEmail = memberEmail.toString();
    }
}
