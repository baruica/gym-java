package gym;

import java.util.List;

public interface Repository<AGGREGATE> {

    String nextId();

    void store(AGGREGATE aggregate);

    void storeAll(List<AGGREGATE> aggregates);

    AGGREGATE get(String aggregateId);
}
