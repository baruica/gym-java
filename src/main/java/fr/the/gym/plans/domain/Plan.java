package fr.the.gym.plans.domain;

public final class Plan {

    private final PlanId id;
    private Integer price;
    private final Integer durationInMonths;

    private Plan(PlanId id, Integer price, Integer durationInMonths) throws PlanException {
        this.id = id;

        if (price < 0) {
            throw new PlanException("Price amount must be non-negative, was " + price);
        }
        this.price = price;

        this.durationInMonths = durationInMonths;
    }

    public static Plan create(PlanId id, Integer basePrice, PlanPeriodicity planPeriodicity) throws PlanException {
        if (PlanPeriodicity.MONTHLY.equals(planPeriodicity)) {
            return new Plan(id, basePrice, 1);
        }
        if (PlanPeriodicity.YEARLY.equals(planPeriodicity)) {
            return new Plan(id, basePrice, 12);
        }
        throw new PlanException("Plan periodicity must be either monthly or yearly");
    }

    public PlanId id() {
        return id;
    }

    public Integer price() {
        return price;
    }

    public void changePrice(final Integer newPrice) {
        price = newPrice;
    }
}
