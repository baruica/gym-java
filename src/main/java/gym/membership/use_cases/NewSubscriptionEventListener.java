package gym.membership.use_cases;

import gym.membership.domain.MemberEvent;
import gym.subscriptions.domain.NewSubscription;

import java.util.List;

public final class NewSubscriptionEventListener {

    private final RegisterNewMember commandHandler;

    public NewSubscriptionEventListener(RegisterNewMember commandHandler) {
        this.commandHandler = commandHandler;
    }

    public List<MemberEvent> handle(NewSubscription event) {

        return commandHandler.handle(
            new RegisterNewMemberCommand(
                event.subscriptionId,
                event.subscriptionStartDate,
                event.email
            )
        );
    }
}
