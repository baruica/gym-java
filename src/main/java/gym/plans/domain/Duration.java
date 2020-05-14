package gym.plans.domain;

import java.util.Objects;

import static java.util.Arrays.asList;

final class Duration {

    final Integer value;

    public Duration(Integer durationInMonths) throws PlanException {
        if (!asList(1, 12).contains(durationInMonths)) {
            throw new PlanException("Plan duration is either 1 month or 12 months, was " + durationInMonths);
        }

        this.value = durationInMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return value.equals(duration.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
