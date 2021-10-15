package gym.plans.domain;

import gym.Aggregate;

import static kotlin.collections.CollectionsKt.listOf;

public final class Plan implements Aggregate {

    public final PlanId id;
    public Price price;
    private final Duration duration;

    @Override
    public String getId() {
        return this.id.id();
    }

    public static record PlanId(String id) {
    }

    private Plan(PlanId id, Price price, Duration duration) {
        this.id = id;
        this.price = price;
        this.duration = duration;
    }

    public static Plan create(String id, Integer priceAmount, Integer durationInMonths) {
        return new Plan(
            new PlanId(id),
            new Price(priceAmount),
            new Duration(durationInMonths)
        );
    }

    public void changePrice(final Integer newPriceAmount) {
        this.price = new Price(newPriceAmount);
    }

    public record Price(Integer amount) {
        public Price {
            if (amount < 0) {
                throw new IllegalArgumentException("Price amount must be non-negative, was [" + amount + "]");
            }
        }
    }

    private record Duration(Integer durationInMonths) {
        public Duration {
            if (!listOf(1, 12).contains(durationInMonths)) {
                throw new IllegalArgumentException("Plan duration is either 1 month or 12 months, was [" + durationInMonths + "]");
            }
        }
    }
}
