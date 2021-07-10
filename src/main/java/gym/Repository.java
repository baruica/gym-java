package gym;

import java.util.List;

public interface Repository<T> {

    String nextId();

    void store(T aggregate);

    void storeAll(List<T> aggregates);

    T get(String aggregateId);
}
