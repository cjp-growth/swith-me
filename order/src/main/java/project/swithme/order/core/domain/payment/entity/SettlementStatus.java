package project.swithme.order.core.domain.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum SettlementStatus {
    INCOMPLETED,
    COMPLETED;

    public static SettlementStatus findByStatus(String status) {
        if (status == null) {
            return null;
        }
        return Arrays.stream(values())
            .filter(equalTo(status))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 정산 상태를 입력해주세요."));
    }

    private static Predicate<SettlementStatus> equalTo(String status) {
        return settlementStatus -> settlementStatus.name().equals(status);
    }
}
