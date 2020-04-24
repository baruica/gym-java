package gym.membership.infrastructure;

import gym.membership.domain.MemberId;

public final class MemberRepositoryException extends Throwable {

    private MemberRepositoryException(String message) {
        super(message);
    }

    public static MemberRepositoryException notFound(final MemberId memberId) {
        return new MemberRepositoryException("Member [" + memberId + "] not found.");
    }
}
