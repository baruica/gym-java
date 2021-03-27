package gym.plans.use_cases;

import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InMemoryPlanRepository implements PlanRepository {

    private final Map<Plan.PlanId, Plan> plans = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Plan plan) {
        plans.put(plan.id, plan);
    }

    @Override
    public Plan get(Plan.PlanId planId) {
        if (plans.containsKey(planId)) {
            return plans.get(planId);
        }

        throw new RuntimeException(planId + " not found.");
    }
}
