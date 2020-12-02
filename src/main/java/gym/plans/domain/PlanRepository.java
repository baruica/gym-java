package gym.plans.domain;

public interface PlanRepository {

    String nextId();

    void store(Plan plan);

    Plan get(Plan.PlanId planId) throws RuntimeException;
}
