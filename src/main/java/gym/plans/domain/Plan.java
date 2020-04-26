package gym.plans.domain;

import static java.util.Arrays.asList;

public final class Plan {

    public final PlanId id;
    public Price price;
    private final Integer durationInMonths;

    public Plan(PlanId id, Integer priceAmount, Integer durationInMonths) throws PlanException {
        this.id = id;

        this.price = new Price(priceAmount);

        if (!asList(1, 12).contains(durationInMonths)) {
            throw new PlanException("Plan duration is either 1 month or 12 months, was " + durationInMonths);
        }

        this.durationInMonths = durationInMonths;
    }

    public void changePrice(final Integer newPriceAmount) throws PlanException {
        var oldPrice = price.amount;
        this.price = new Price(newPriceAmount);
    }
}

