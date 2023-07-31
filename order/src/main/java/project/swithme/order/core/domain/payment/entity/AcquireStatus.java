package project.swithme.order.core.domain.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum AcquireStatus {
    READY,
    REQUESTED,
    COMPLETED,
    CANCEL_REQUESTED,
    CANCELED;

    public static AcquireStatus findByStatus(String status) {
        return Arrays.stream(values())
            .filter(equalTo(status))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 카드 결제 매입 상태를 입력해주세요."));
    }

    private static Predicate<AcquireStatus> equalTo(String status) {
        return acquireStatus -> acquireStatus.name().equals(status);
    }
}
