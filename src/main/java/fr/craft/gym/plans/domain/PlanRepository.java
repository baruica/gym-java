package fr.craft.gym.plans.domain;

public interface PlanRepository {

    PlanId nextId();

    void store(Plan plan);

    Plan get(PlanId planId) throws PlanRepositoryException;
}
