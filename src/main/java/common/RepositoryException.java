package common;

public final class RepositoryException extends Exception {

    private RepositoryException(String message) {
        super(message);
    }

    public static RepositoryException notFound(final AggregateId aggregateId) {
        return new RepositoryException(aggregateId.getClass().getSimpleName() + " [" + aggregateId + "] not found.");
    }
}
