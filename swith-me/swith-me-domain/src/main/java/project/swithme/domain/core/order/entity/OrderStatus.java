package project.swithme.domain.core.order.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum OrderStatus {
    PAYMENT_REQUEST,
    COMPLETE,
    CANCEL,
    REFUND;

    public static OrderStatus findStatus(String orderStatus) {
        return Arrays.stream(values())
            .filter(equalTo(orderStatus))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 주문 상태를 입력해주세요."));
    }

    private static Predicate<? super OrderStatus> equalTo(String orderStatus) {
        return status -> status.name().equals(orderStatus);
    }

}
