package project.swithme.order.core.domain.order.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

@Getter
public enum PayType {
    CARD("신용카드"),
    KAKAO_PAY("카카오 페이"),
    NAVER_PAY("네이버 페이"),
    TOSS_PAY("토스 페이"),
    REMITTANCE("무통장 입금");

    private final String type;

    PayType(String type) {
        this.type = type;
    }

    public static PayType findPayType(String type) {
        return Arrays.stream(values())
                .filter(isEqualTo(type))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 결제 수단을 입력해주세요."));
    }

    private static Predicate<PayType> isEqualTo(String type) {
        return payType -> payType.type.equals(type);
    }
}
