package project.swithme.domain.core.payment.entity;

public enum InterestPayer {
    BUYER,
    CARD_COMPANY,
    MERCHANT;

    public static InterestPayer findByPayer(String interestPayer) {
        if (interestPayer == null) {
            return null;
        }
        try {
            return InterestPayer.valueOf(interestPayer);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바른 할부 수수료 부담 주체를 입력해주세요.");
        }
    }
}
