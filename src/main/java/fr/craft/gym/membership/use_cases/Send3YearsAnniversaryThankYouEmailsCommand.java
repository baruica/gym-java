package fr.craft.gym.membership.use_cases;

import java.time.LocalDate;

public final class Send3YearsAnniversaryThankYouEmailsCommand {

    public final LocalDate asOfDate;

    public Send3YearsAnniversaryThankYouEmailsCommand(LocalDate asOfDate) {
        this.asOfDate = asOfDate;
    }
}
