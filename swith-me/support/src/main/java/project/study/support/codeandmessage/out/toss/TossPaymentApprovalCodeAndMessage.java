package project.study.support.codeandmessage.out.toss;

import project.study.support.codeandmessage.CodeAndMessage;

public enum TossPaymentApprovalCodeAndMessage implements CodeAndMessage {
    ALREADY_PROCESSED_PAYMENT(
        409,
        "ALREADY_PROCESSED_PAYMENT",
        "이미 처리된 결제 입니다.",
        "This is a payment that has already been processed."
    ),
    NOT_FOUND_PAYMENT_SESSION(
        410,
        "NOT_FOUND_PAYMENT_SESSION",
        "결제 시간이 만료되어 결제 진행 데이터가 존재하지 않습니다.",
        "Payment session does not exist because the session time has expired."
    );

    private final int statusCode;
    private final String errorCode;
    private final String krErrorMessage;
    private final String engErrorMessage;

    TossPaymentApprovalCodeAndMessage(
        int statusCode,
        String errorCode,
        String krErrorMessage,
        String engErrorMessage
    ) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.krErrorMessage = krErrorMessage;
        this.engErrorMessage = engErrorMessage;
    }

    public static CodeAndMessage findCodeAndMessage(String errorCode) {
        return TossPaymentApprovalCodeAndMessage.valueOf(errorCode);
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
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
