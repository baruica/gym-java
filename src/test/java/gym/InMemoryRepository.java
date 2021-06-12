package gym;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryRepository<T extends HasAnId> implements Repository<T> {

    protected final Map<String, T> items = new HashMap<>();

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(T item) {
        items.put(item.getId(), item);
    }

    @Override
    public void storeAll(List<T> items) {
        items.forEach(this::store);
    }

    @Override
    public T get(String itemId) {
        if (items.containsKey(itemId)) {
            return items.get(itemId);
        }

        throw new RuntimeException(itemId + " not found.");
    }
}
