package fr.the.gym.plans.domain;

public final class PlanRepositoryException extends Exception {

    private PlanRepositoryException(String message) {
        super(message);
    }

    public static PlanRepositoryException notFound(final PlanId planId) {
        return new PlanRepositoryException("Plan [" + planId + "] not found.");
    }
}
