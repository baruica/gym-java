package fr.the.gym.subscriptions.domain;

final class Discount {

    private Double rate = 0.0;

    Discount(final Boolean chosenPlanIsYearly, final Boolean isStudent) {
        if (chosenPlanIsYearly) {
            rate += 0.3;
        }
        if (isStudent) {
            rate += 0.2;
        }
    }

    Double rate() {
        return rate;
    }
}
