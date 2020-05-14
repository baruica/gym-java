package gym.plans.domain;

import java.util.Objects;

final class Price {

    final Integer amount;

    Price(Integer amount) throws PlanException {
        if (amount < 0) {
            throw new PlanException("Price amount must be non-negative, was " + amount);
        }
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return amount.equals(price.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
