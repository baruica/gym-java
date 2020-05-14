package gym.plans.domain;

import java.util.ArrayList;
import java.util.List;

public final class Plan {

    public final PlanId id;
    Price price;
    private final Duration durationInMonths;

    private final List<PlanEvent> raisedEvents = new ArrayList<>();

    public Plan(PlanId id, Integer priceAmount, Integer durationInMonths) throws PlanException {
        this.id = id;

        this.price = new Price(priceAmount);
        this.durationInMonths = new Duration(durationInMonths);

        this.raisedEvents.add(
            new NewPlanCreated(
                this.id.toString(),
                this.price.amount,
                this.durationInMonths.value
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
