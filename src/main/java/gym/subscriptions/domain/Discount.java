package gym.subscriptions.domain;

final class Discount {

    private Double rate = 0.0;

    Discount(final Integer durationInMonths, final Boolean isStudent) {
        if (durationInMonths == 12) {
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
