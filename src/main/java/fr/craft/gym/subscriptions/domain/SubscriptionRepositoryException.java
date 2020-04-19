package fr.craft.gym.subscriptions.domain;

public final class SubscriptionRepositoryException extends Throwable {

    private SubscriptionRepositoryException(String message) {
        super(message);
    }

    public static SubscriptionRepositoryException notFound(final SubscriptionId subscriptionId) {
        return new SubscriptionRepositoryException("Subscription [" + subscriptionId + "] not found.");
    }
}
