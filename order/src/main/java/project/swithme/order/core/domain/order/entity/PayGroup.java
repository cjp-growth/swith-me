package project.swithme.order.core.domain.order.entity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum PayGroup {
    CASH("현금", List.of(PayType.REMITTANCE, PayType.TOSS)),
    CARD("카드", List.of(PayType.NAVER_PAY, PayType.KAKAO_PAY));

    private final String group;
    private final List<PayType> payTypes;

    PayGroup(
        String group,
        List<PayType> payTypes
    ) {
        this.group = group;
        this.payTypes = payTypes;
    }

    public static List<String> findPayTypes(PayGroup payGroup) {
        return Arrays.stream(values())
            .filter(isEqualTo(payGroup))
            .map(PayGroup::getPayTypes)
            .map(Object::toString)
            .toList();
    }

    private static Predicate<PayGroup> isEqualTo(PayGroup payGroup) {
        return group -> group.equals(payGroup);
    }

    private List<PayType> getPayTypes() {
        return payTypes;
    }
}
