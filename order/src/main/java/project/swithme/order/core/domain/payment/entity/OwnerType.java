package project.swithme.order.core.domain.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum OwnerType {
    INDIVIDUAL("개인"),
    CORPORATION("법인"),
    UN_IDENTIFIED("미확인");

    private final String type;

    OwnerType(String type) {
        this.type = type;
    }

    public static OwnerType findByType(String type) {
        if (type == null) {
            return UN_IDENTIFIED;
        }
        return Arrays.stream(values())
            .filter(equalTo(type))
            .findAny()
            .orElseGet(() -> UN_IDENTIFIED);
    }

    private static Predicate<OwnerType> equalTo(String type) {
        return card -> card.type.equals(type);
    }
}
