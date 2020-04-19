package fr.craft.gym.reporting.use_cases;

import java.time.LocalDate;

public final class TurnoverForAGivenMonthQuery {
    public final LocalDate asOfDate;

    public TurnoverForAGivenMonthQuery(LocalDate asOfDate) {
        this.asOfDate = asOfDate;
    }
}
