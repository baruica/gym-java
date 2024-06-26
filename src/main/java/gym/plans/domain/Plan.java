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

    public record PlanId(String id) {
        @Override
        public String toString() {
            return id;
        }
    }

    private Plan(PlanId id, Price price, Duration duration) {
        this.id = id;
        this.price = price;
        this.duration = duration;
    }

    public static Plan create(PlanId id, Integer priceAmount, Integer durationInMonths) {
        return new Plan(
            id,
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
                throw new IllegalArgumentException(STR."Price amount must be non-negative, was [\{amount}]");
            }
        }
    }

    private record Duration(Integer durationInMonths) {
        private Duration {
            if (!listOf(1, 12).contains(durationInMonths)) {
                throw new IllegalArgumentException(STR."Plan duration is either 1 month or 12 months, was [\{durationInMonths}]");
            }
        }
    }
}
