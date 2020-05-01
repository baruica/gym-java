package gym.plans.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public final class Plan {

    public final PlanId id;
    Price price;
    private final Integer durationInMonths;

    private final List<PlanEvent> raisedEvents = new ArrayList<>();

    public Plan(PlanId id, Integer priceAmount, Integer durationInMonths) throws PlanException {
        this.id = id;

        this.price = new Price(priceAmount);

        if (!asList(1, 12).contains(durationInMonths)) {
            throw new PlanException("Plan duration is either 1 month or 12 months, was " + durationInMonths);
        }

        this.durationInMonths = durationInMonths;

        this.raisedEvents.add(
            new NewPlanCreated(
                this.id.toString(),
                this.price.amount,
                this.durationInMonths
            )
        );
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
}

