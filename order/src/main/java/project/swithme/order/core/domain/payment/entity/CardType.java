package project.swithme.order.core.domain.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CardType {
    CREDIT("신용"),
    CHECK("체크"),
    GIFT("기프트"),
    UN_IDENTIFIED("미확인");

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    public static CardType findByType(String type) {
        return Arrays.stream(values())
            .filter(equalTo(type))
            .findAny()
            .orElseGet(() -> UN_IDENTIFIED);
    }

    private static Predicate<CardType> equalTo(String type) {
        return card -> card.type.equals(type);
    }
}
