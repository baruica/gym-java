package gym.plans.domain;

import gym.plans.infrastructure.PlanRepositoryException;

public interface PlanRepository {

    PlanId nextId();

    void store(Plan plan);

    Plan get(PlanId planId) throws PlanRepositoryException;
}
