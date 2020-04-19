package fr.the.gym.membership.domain;

import fr.the.gym.subscriptions.domain.SubscriptionId;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemberTest {

    @Test
    public void is_three_years_anniversary() {
        var memberWith3yearsAnniversaryOnTheFifthOfJune = new Member(
            new MemberId("abc"),
            "julie@gmail.com",
            new SubscriptionId("def"),
            XYearsBeforeThe(3, fifthOfJune())
        );

        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-04")));
        assertTrue(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(fifthOfJune()));
        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-07-06")));
    }

    private LocalDate fifthOfJune() {
        return LocalDate.parse("2018-06-05");
    }

    private LocalDate XYearsBeforeThe(Integer nbYearsBefore, LocalDate date) {
        return date.minusYears(nbYearsBefore);
    }
}
