package gym.plans.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public final class Plan {

    public final PlanId id;
    Price price;
    private final Duration duration;

    private final List<PlanEvent> raisedEvents = new ArrayList<>();

    private Plan(PlanId id, Price price, Duration duration) {
        this.id = id;
        this.price = price;
        this.duration = duration;
    }

    public static Plan create(PlanId id, Integer priceAmount, Integer durationInMonths) throws PlanException {
        var plan = new Plan(
            id,
            new Price(priceAmount),
            new Duration(durationInMonths)
        );

        plan.raisedEvents.add(
            new NewPlanCreated(
                plan.id.toString(),
                plan.price.amount,
                plan.duration.value
            )
        );

        return plan;
    }

    public List<PlanEvent> getRaisedEvents() {
        return raisedEvents;
    }

    public void changePrice(final Integer newPriceAmount) throws PlanException {
        var oldPrice = price.amount;

        this.price = new Price(newPriceAmount);

        this.raisedEvents.add(
            new PlanPriceChanged(
                this.id.toString(),
                oldPrice,
                this.price.amount
            )
        );
    }

    static final class Price {

        final Integer amount;

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
