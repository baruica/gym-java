package gym.subscriptions.domain;

import java.time.LocalDate;

public final class Subscription {

    public final SubscriptionId id;
    private final Integer durationInMonths;
    private final LocalDate startDate;
    public LocalDate endDate;
    public Price price;
    private Boolean threeYearsAnniversaryDiscountApplied;

    public static record SubscriptionId(String id) {
    }

    private Subscription(SubscriptionId id, Integer planDurationInMonths, LocalDate startDate, LocalDate endDate, Price price, Boolean threeYearsAnniversaryDiscountApplied) {
        this.id = id;
        this.durationInMonths = planDurationInMonths;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.threeYearsAnniversaryDiscountApplied = threeYearsAnniversaryDiscountApplied;
    }

    public static Subscription subscribe(
        String id,
        LocalDate startDate,
        Integer planDurationInMonths,
        Integer planPrice,
        Boolean isStudent
    ) {
        return new Subscription(
            new SubscriptionId(id),
            planDurationInMonths,
            startDate,
            startDate.plusMonths(planDurationInMonths),
            new Price(planPrice)
                .applyDurationDiscount(planDurationInMonths)
                .applyStudentDiscount(isStudent),
            false
        );
    }

    public void renew() {
        endDate = endDate.plusMonths(durationInMonths);
    }

    public Boolean willBeEndedAsFrom(final LocalDate date) {
        return date.isAfter(endDate);
    }

    public boolean isMonthly() {
        return durationInMonths == 1;
    }

    public Boolean isOngoing(final LocalDate date) {
        return (startDate.isEqual(date) || startDate.isBefore(date))
            && (endDate.isEqual(date) || endDate.isAfter(date));
    }

    public int monthlyTurnover() {
        return (int) Math.round(price.amount / durationInMonths);
    }

    public boolean hasThreeYearsAnniversaryOn(LocalDate date) {
        return date.equals(startDate.plusYears(3))
            && date.equals(endDate);
    }

    public void applyThreeYearsAnniversaryDiscount(LocalDate date) {
        if (!threeYearsAnniversaryDiscountApplied) {
            var newPrice = price.applyThreeYearsAnniversaryDiscount(
                hasThreeYearsAnniversaryOn(date)
            );

            if (price != newPrice) {
                price = newPrice;
                threeYearsAnniversaryDiscountApplied = true;
            }
        }
    }

    public record Price(Double amount) {

        public Price {
            if (amount < 0) {
                throw new IllegalArgumentException("Price amount must be non-negative, was [" + amount + "]");
            }
        }

        public Price(Integer amount) {
            this(Double.valueOf(amount));
        }

        public Price applyDurationDiscount(Integer durationInMonths) {
            return (durationInMonths == 12) ? applyDiscount(0.1) : this;
        }

        public Price applyStudentDiscount(Boolean isStudent) {
            return isStudent ? applyDiscount(0.2) : this;
        }

        public Price applyThreeYearsAnniversaryDiscount(boolean hasThreeYearsAnniversary) {
            return hasThreeYearsAnniversary ? applyDiscount(0.05) : this;
        }

        private Price applyDiscount(double rate) {
            return new Price(amount * (1 - rate));
        }
    }
}
