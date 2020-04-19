package fr.the.gym.subscriptions.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

final class Period {

    private static final String UNTIL = " until ";

    final LocalDate startDate;
    private final LocalDate endDate;

    Period(LocalDate startDate, Integer nbMonths) {
        this.startDate = startDate;
        this.endDate = startDate.plus(nbMonths, ChronoUnit.MONTHS).minusDays(1);
    }

    Period(String period) {
        this(
            LocalDate.parse(period.split(UNTIL)[0]),
            LocalDate.parse(period.split(UNTIL)[1])
        );
    }

    private Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Boolean contains(final LocalDate date) {
        return (date.isAfter(startDate) || date.isEqual(startDate)) && date.isBefore(endDate);
    }

    Boolean beforeDate(final LocalDate date) {
        return date.isAfter(endDate);
    }

    Period next() {
        var firstDayOfNextPeriod = endDate.plusDays(1);
        var nbMonthsCurrentPeriod = java.time.Period.between(startDate, firstDayOfNextPeriod).getMonths();

        return new Period(
            firstDayOfNextPeriod,
            nbMonthsCurrentPeriod
        );
    }

    @Override
    public String toString() {
        return startDate + UNTIL + endDate;
    }
}
