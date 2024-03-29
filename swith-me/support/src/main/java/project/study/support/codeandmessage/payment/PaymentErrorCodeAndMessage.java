package project.study.support.codeandmessage.payment;

import project.study.support.codeandmessage.CodeAndMessage;

public enum PaymentErrorCodeAndMessage implements CodeAndMessage {
    INVALID_PAYMENT_EXECUTION(
        600,
        "결제가 정상적으로 이루어지지 않았습니다.",
        "Payment was not made normally."
    );

    private final int code;
    private final String krErrorMessage;
    private final String engErrorMessage;

    PaymentErrorCodeAndMessage(
        int code,
        String krErrorMessage,
        String engErrorMessage
    ) {
        this.code = code;
        this.krErrorMessage = krErrorMessage;
        this.engErrorMessage = engErrorMessage;
    }

    @Override
    public String getErrorCode() {
        return name();
    }

    @Override
    public int getStatusCode() {
        return code;
    }

    @Override
    public String getKrErrorMessage() {
        return krErrorMessage;
    }

    @Override
    public String getEnErrorMessage() {
        return engErrorMessage;
    }
}
