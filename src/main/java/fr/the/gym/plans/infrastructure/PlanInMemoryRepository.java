package fr.the.gym.plans.infrastructure;

import fr.the.gym.plans.domain.Plan;
import fr.the.gym.plans.domain.PlanId;
import fr.the.gym.plans.domain.PlanRepository;
import fr.the.gym.plans.domain.PlanRepositoryException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlanInMemoryRepository implements PlanRepository {

    private final Map<PlanId, Plan> plans = new HashMap<>();

    @Override
    public PlanId nextId() {
        return new PlanId(UUID.randomUUID().toString());
    }

    @Override
    public void store(Plan plan) {
        plans.put(plan.id(), plan);
    }

    @Override
    public Plan get(PlanId planId) throws PlanRepositoryException {
        if (plans.containsKey(planId)) {
            return plans.get(planId);
        }

        throw PlanRepositoryException.notFound(planId);
    }
}
