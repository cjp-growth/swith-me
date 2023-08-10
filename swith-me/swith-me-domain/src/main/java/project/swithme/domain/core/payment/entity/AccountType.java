package project.swithme.domain.core.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum AccountType {
    GENERAL("일반"),
    FIXED("고정");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public static AccountType findByType(String type) {
        if (type == null) {
            return null;
        }
        return Arrays.stream(values())
            .filter(equalTo(type))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 계좌 유형을 입력해주세요."));
    }

    private static Predicate<AccountType> equalTo(String type) {
        return accountType -> accountType.type.equals(type);
    }
}
