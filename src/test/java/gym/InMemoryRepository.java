package gym;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryRepository<T extends Aggregate> implements Repository<T> {

    protected final Map<String, T> aggregates = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(T aggregate) {
        aggregates.put(aggregate.getId(), aggregate);
    }

    @Override
    public void storeAll(List<T> aggregates) {
        aggregates.forEach(this::store);
    }

    @Override
    public T get(String aggregateId) {
        if (aggregates.containsKey(aggregateId)) {
            return aggregates.get(aggregateId);
        }

        throw new RuntimeException(aggregateId + " not found.");
    }
}
