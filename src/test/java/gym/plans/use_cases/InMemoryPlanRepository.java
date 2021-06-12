package gym.plans.use_cases;

import gym.InMemoryRepository;
import gym.plans.domain.Plan;
import gym.plans.domain.PlanRepository;

public final class InMemoryPlanRepository extends InMemoryRepository<Plan> implements PlanRepository {
}
