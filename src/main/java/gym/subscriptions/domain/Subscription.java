package gym.subscriptions.domain;

import gym.Aggregate;

import java.time.LocalDate;

public final class Subscription implements Aggregate {

    public final SubscriptionId id;
    private final Integer durationInMonths;
    private final LocalDate startDate;
    public LocalDate endDate;
    public Price price;
    private Boolean threeYearsAnniversaryDiscountApplied;

    @Override
    public String getId() {
        return this.id.id();
    }

    public record SubscriptionId(String id) {
        @Override
        public String toString() {
            return id;
        }
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
        SubscriptionId id,
        LocalDate startDate,
        Integer planDurationInMonths,
        Integer planPrice,
        Boolean isStudent
    ) {
        return new Subscription(
            id,
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

    public boolean hasThreeYearsAnniversaryAfter(LocalDate date) {
        LocalDate threeYearsAfterStartDate = startDate.plusYears(3);

        return (date.equals(threeYearsAfterStartDate) || date.isAfter(threeYearsAfterStartDate))
            && threeYearsAfterStartDate.equals(endDate)
            && (date.equals(endDate) || date.isAfter(endDate))
            && !threeYearsAnniversaryDiscountApplied;
    }

    public void applyThreeYearsAnniversaryDiscount(LocalDate date) {
        boolean hasThreeYearsAnniversaryPassed = hasThreeYearsAnniversaryAfter(date);

        if (hasThreeYearsAnniversaryPassed) {
            var newPrice = price.applyThreeYearsAnniversaryDiscount(
                hasThreeYearsAnniversaryPassed
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
                throw new IllegalArgumentException(STR."Price amount must be non-negative, was [\{amount}]");
            }
        }

        Price(Integer amount) {
            this(Double.valueOf(amount));
        }

        Price applyDurationDiscount(Integer durationInMonths) {
            return (durationInMonths == 12) ? applyDiscount(0.1) : this;
        }

        Price applyStudentDiscount(Boolean isStudent) {
            return isStudent ? applyDiscount(0.2) : this;
        }

        Price applyThreeYearsAnniversaryDiscount(boolean hasThreeYearsAnniversary) {
            return hasThreeYearsAnniversary ? applyDiscount(0.05) : this;
        }

        private Price applyDiscount(double rate) {
            return new Price(amount * (1 - rate));
        }
    }
}
