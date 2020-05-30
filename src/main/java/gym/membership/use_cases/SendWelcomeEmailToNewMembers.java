package gym.membership.use_cases;

import common.RepositoryException;
import gym.membership.domain.*;

import java.util.List;

final class SendWelcomeEmailToNewMembers {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    SendWelcomeEmailToNewMembers(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    List<MemberEvent> handle(final NewMemberSubscribed event) throws RepositoryException {

        var member = (Member) memberRepository.get(new MemberId(event.memberId));

        mailer.sendWelcomeEmail(member);

        memberRepository.store(member);

        return member.getRaisedEvents();
    }
}
