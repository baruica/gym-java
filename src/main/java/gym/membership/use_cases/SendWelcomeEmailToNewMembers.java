package gym.membership.use_cases;

import gym.membership.domain.*;

final class SendWelcomeEmailToNewMembers {

    private final MemberRepository memberRepository;
    private final Mailer mailer;

    SendWelcomeEmailToNewMembers(MemberRepository memberRepository, Mailer mailer) {
        this.memberRepository = memberRepository;
        this.mailer = mailer;
    }

    WelcomeEmailWasSentToNewMember handle(final NewMemberSubscribed event) throws MemberRepositoryException {

        var member = memberRepository.get(new MemberId(event.memberId));

        mailer.sendEmail(
            member.email,
            "Thank you for subscribing " + member.email + " !"
        );

        member.markWelcomeEmailAsSent();

        memberRepository.store(member);

        return new WelcomeEmailWasSentToNewMember(member.id);
    }
}
