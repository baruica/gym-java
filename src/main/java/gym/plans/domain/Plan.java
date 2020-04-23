package gym.plans.domain;

public final class Plan {

    public final PlanId id;
    public Integer price;
    private final Integer durationInMonths;

    private Plan(String id, Integer price, Integer durationInMonths) throws PlanException {
        this.id = new PlanId(id);

        if (price < 0) {
            throw new PlanException("Price amount must be non-negative, was " + price);
        }
        this.price = price;

        this.durationInMonths = durationInMonths;
    }

    public static Plan create(String id, Integer basePrice, Integer planDurationsInMonths) throws PlanException {
        return switch (planDurationsInMonths) {
            case 1 -> new Plan(id, basePrice, 1);
            case 12 -> new Plan(id, basePrice, 12);
            default -> throw new PlanException("Plan periodicity must be either monthly or yearly");
        };
    }

    public void changePrice(final Integer newPrice) {
        price = newPrice;
    }
}
