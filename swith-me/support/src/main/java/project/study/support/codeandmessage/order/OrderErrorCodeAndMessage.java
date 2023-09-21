package project.study.support.codeandmessage.order;

import project.study.support.codeandmessage.CodeAndMessage;

public enum OrderErrorCodeAndMessage implements CodeAndMessage {
    INVALID_ORDER_ID(
        400,
        "INVALID_ORDER_ID",
        "올바른 주문 아이디를 입력해주세요.",
        "Please enter a valid order ID."
    ),
    INVALID_PRICE(
        400,
        "INVALID_PRICE",
        "올바른 주문 가격을 입력해주세요.",
        "Please enter the correct order price."
    ),
    INVALID_ORDER_STATUS(
        400,
        "INVALID_ORDER_STATUS",
        "올바르지 않은 주문 상태입니다.",
        "Invalid order status."
    ),
    ORDER_NOT_FOUND(
        404,
        "ORDER_NOT_FOUND",
        "주문을 찾을 수 없습니다.",
        "Can not find order."
    );

    private final int code;
    private final String errorCode;
    private final String krErrorMessage;
    private final String engErrorMessage;

    OrderErrorCodeAndMessage(
        int code,
        String errorCode,
        String krErrorMessage,
        String engErrorMessage
    ) {
        this.code = code;
        this.errorCode = errorCode;
        this.krErrorMessage = krErrorMessage;
        this.engErrorMessage = engErrorMessage;
    }

    @Override
    public int getStatusCode() {
        return code;
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
