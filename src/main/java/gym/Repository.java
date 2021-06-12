package gym;

public interface Repository<T> {

    String nextId();

    void store(T item);

    T get(String itemId);
}
