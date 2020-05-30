package common;

import java.util.List;

public interface Repository {

    String nextId();

    void store(Aggregate aggregate);

    void storeAll(List<? extends Aggregate> aggregates);

    Aggregate get(AggregateId aggregateId) throws RepositoryException;
}
