package gym.subscriptions.domain;

import java.util.Objects;

final class Price {

    private final Integer amount;

    Price(Integer amount) {
        this.amount = amount;
    }

    Integer afterDiscount(Boolean chosenPlanIsYearly, Boolean isStudent) {
        return (int) (amount * (1 - new Discount(chosenPlanIsYearly, isStudent).rate()));
    }

    @Override
    public String toString() {
        return String.valueOf(amount.intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(amount, price.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
