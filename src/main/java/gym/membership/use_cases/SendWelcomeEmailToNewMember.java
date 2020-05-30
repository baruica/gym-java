package gym.membership.use_cases;

import common.RepositoryException;
import gym.membership.domain.*;

import java.util.List;

final class SendWelcomeEmailToNewMember {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    SendWelcomeEmailToNewMember(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    List<MemberEvent> handle(final SendWelcomeEmailToNewMemberCommand command) throws RepositoryException {

        var member = (Member) memberRepository.get(new MemberId(command.memberId));

        mailer.sendWelcomeEmail(member);

        memberRepository.store(member);

        return member.getRaisedEvents();
    }
}
