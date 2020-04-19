package fr.craft.gym.subscriptions.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

final class Period {

    final LocalDate startDate;
    private final LocalDate endDate;

    Period(LocalDate startDate, Integer nbMonths) {
        this.startDate = startDate;
        this.endDate = startDate.plus(nbMonths, ChronoUnit.MONTHS).minusDays(1);
    }

    Boolean isBefore(final LocalDate date) {
        return date.isAfter(endDate);
    }

    Boolean contains(final LocalDate date) {
        return (date.isAfter(startDate) || date.isEqual(startDate))
            && (date.isBefore(endDate) || date.isEqual(endDate));
    }

    Period next() {
        var firstDayOfNextPeriod = endDate.plusDays(1);
        var nbMonthsCurrentPeriod = java.time.Period.between(startDate, firstDayOfNextPeriod).getMonths();

        return new Period(
            firstDayOfNextPeriod,
            nbMonthsCurrentPeriod
        );
    }
}
