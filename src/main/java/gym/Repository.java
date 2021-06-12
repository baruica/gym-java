package gym;

import java.util.List;

public interface Repository<T> {

    String nextId();

    void store(T item);

    void storeAll(List<T> items);

    T get(String itemId);
}
