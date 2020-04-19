package fr.craft.gym.membership.domain;

public final class MemberRepositoryException extends Throwable {

    private MemberRepositoryException(String message) {
        super(message);
    }

    public static MemberRepositoryException notFound(final MemberId memberId) {
        return new MemberRepositoryException("Member [" + memberId + "] not found.");
    }
}
