package gym;

import com.github.f4b6a3.ulid.UlidCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<AGGREGATE extends Aggregate> implements Repository<AGGREGATE> {

    protected final Map<String, AGGREGATE> aggregates = new HashMap<>();

    @Override
    public String nextId() {
        return UlidCreator.getUlid().toString();
    }

    @Override
    public void store(AGGREGATE aggregate) {
        aggregates.put(aggregate.getId(), aggregate);
    }

    @Override
    public void storeAll(List<AGGREGATE> aggregates) {
        aggregates.forEach(this::store);
    }

    @Override
    public AGGREGATE get(String aggregateId) {
        if (aggregates.containsKey(aggregateId)) {
            return aggregates.get(aggregateId);
        }

        throw new RuntimeException(aggregateId + " not found.");
    }
}
