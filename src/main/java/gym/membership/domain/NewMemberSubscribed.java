package gym.membership.domain;

public final class NewMemberSubscribed {

    public final String memberId;
    public final String memberEmail;
    public final String subscriptionId;
    public final String memberSince;

    public NewMemberSubscribed(String memberId, String memberEmail, String subscriptionId, String memberSince) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.subscriptionId = subscriptionId;
        this.memberSince = memberSince;
    }
}
