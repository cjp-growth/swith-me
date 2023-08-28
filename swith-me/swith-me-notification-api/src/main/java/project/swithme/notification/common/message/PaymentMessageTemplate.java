package project.swithme.notification.common.message;

public enum PaymentMessageTemplate {
    PAYMENT_SUCCESS("[%s] 결제가 성공적으로 이루어졌습니다.");

    private final String message;

    PaymentMessageTemplate(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
