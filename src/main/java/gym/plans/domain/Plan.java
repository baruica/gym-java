package gym.plans.domain;

import common.Aggregate;
import common.AggregateId;

import java.util.Objects;

import static java.util.Arrays.asList;

public final class Plan implements Aggregate {

    public final PlanId id;
    public Price price;
    private final Duration duration;

    private Plan(PlanId id, Price price, Duration duration) {
        this.id = id;
        this.price = price;
        this.duration = duration;
    }

    @Override
    public AggregateId id() {
        return id;
    }

    public static Plan create(String id, Integer priceAmount, Integer durationInMonths) throws PlanException {
        return new Plan(
            new PlanId(id),
            new Price(priceAmount),
            new Duration(durationInMonths)
        );
    }

    public void changePrice(final Integer newPriceAmount) throws PlanException {
        this.price = new Price(newPriceAmount);
    }

    public static final class Price {

        public final Integer amount;

        Price(Integer amount) throws PlanException {
            if (amount < 0) {
                throw new PlanException("Price amount must be non-negative, was " + amount);
            }
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Price price = (Price) o;
            return amount.equals(price.amount);
        }

        @Override
        public int hashCode() {
            return Objects.hash(amount);
        }
    }

    private static final class Duration {

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
}
