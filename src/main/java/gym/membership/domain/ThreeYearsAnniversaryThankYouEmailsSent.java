package gym.membership.domain;

import gym.AggregateId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ThreeYearsAnniversaryThankYouEmailsSent {

    public final List<String> memberIds;

    public ThreeYearsAnniversaryThankYouEmailsSent(Map<MemberId, Member> memberIds) {
        this.memberIds = new ArrayList<>(memberIds.keySet()).stream()
            .map(AggregateId::toString)
            .collect(Collectors.toList());
    }
}
