package project.study.support.codeandmessage.payment;

import project.study.support.codeandmessage.CodeAndMessage;

public enum PaymentCodeAndMessage implements CodeAndMessage {
    INVALID_PAYMENT_EXECUTION(500, "결제가 정상적으로 이루어지지 않았습니다.");

    private final int code;
    private final String message;

    PaymentCodeAndMessage(
        int code,
        String message
    ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return code;
    }

    @Override
    public String getKrErrorMessage() {
        return message;
    }
}
