package gym.plans.infrastructure;

import common.Aggregate;
import common.AggregateId;
import common.RepositoryException;
import gym.plans.domain.Plan;
import gym.plans.domain.PlanId;
import gym.plans.domain.PlanRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class PlanInMemoryRepository implements PlanRepository {

    private final Map<PlanId, Plan> plans = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Aggregate aggregate) {
        plans.put((PlanId) aggregate.id(), (Plan) aggregate);
    }

    @Override
    public void storeAll(List<? extends Aggregate> aggregates) {
        aggregates.forEach(this::store);
    }

    @Override
    public Aggregate get(AggregateId aggregateId) throws RepositoryException {
        if (plans.containsKey(aggregateId)) {
            return plans.get(aggregateId);
        }

        throw RepositoryException.notFound(aggregateId);
    }
}
