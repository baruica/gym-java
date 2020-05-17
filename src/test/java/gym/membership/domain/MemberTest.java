package gym.membership.domain;

import gym.subscriptions.domain.SubscriptionId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberTest {

    @Test
    public void is_three_years_anniversary() {
        var memberWith3yearsAnniversaryOnTheFifthOfJune = new Member(
            new MemberId("abc"),
            new Email("julie@gmail.com"),
            new SubscriptionId("subscriptionId def"),
            fifthOfJune().minusYears(3)
        );

        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-04")));
        assertTrue(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(fifthOfJune()));
        assertFalse(memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-07-06")));
    }

    private LocalDate fifthOfJune() {
        return LocalDate.parse("2018-06-05");
    }
}
