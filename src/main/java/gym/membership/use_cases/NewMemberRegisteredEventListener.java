package gym.membership.use_cases;

import common.RepositoryException;
import gym.membership.domain.MemberEvent;
import gym.membership.domain.NewMemberRegistered;

import java.util.List;

public final class NewMemberRegisteredEventListener {

    private final SendWelcomeEmailToNewMember commandHandler;

    public NewMemberRegisteredEventListener(SendWelcomeEmailToNewMember commandHandler) {
        this.commandHandler = commandHandler;
    }

    public List<MemberEvent> handle(NewMemberRegistered event) throws RepositoryException {

        return commandHandler.handle(
            new SendWelcomeEmailToNewMemberCommand(
                event.memberId
            )
        );
    }
}
