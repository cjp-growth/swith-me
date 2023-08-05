package project.swithme.order.core.domain.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PaymentMethod {
    CARD("카드"),
    VIRTUAL_ACCOUNT("가상계좌"),
    SIMPLE_PAYMENT("간편결제"),
    MOBILE_PHONE("휴대폰"),
    ACCOUNT_TRANSFER("계좌이체"),
    CULTURAL_GIFT_CERTIFICATE("문화상품권"),
    BOOK_CULTURE_GIFT_CERTIFICATE("도서문화상품권"),
    GAME_CULTURE_GIFT_CERTIFICATE("게임문화상품권");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public static PaymentMethod findMethod(String method) {
        return Arrays.stream(values())
            .filter(equalTo(method))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 결제 수단을 입력해주세요."));
    }

    private static Predicate<PaymentMethod> equalTo(String method) {
        return paymentMethod -> paymentMethod.method.equals(method);
    }
}
