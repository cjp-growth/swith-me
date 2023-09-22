package project.study.support.codeandmessage.order;

import project.study.support.codeandmessage.CodeAndMessage;

public enum OrderErrorCodeAndMessage implements CodeAndMessage {
    INVALID_ORDER_ID(
        400,
        "올바른 주문 아이디를 입력해주세요.",
        "Please enter a valid order ID."
    ),
    INVALID_PRICE(
        400,
        "올바른 주문 가격을 입력해주세요.",
        "Please enter the correct order price."
    ),
    INVALID_ORDER_STATUS(
        400,
        "올바르지 않은 주문 상태입니다.",
        "Invalid order status."
    ),
    ORDER_NOT_FOUND(
        404,
        "주문을 찾을 수 없습니다.",
        "Can not find order."
    );

    private final int code;
    private final String krErrorMessage;
    private final String engErrorMessage;

    OrderErrorCodeAndMessage(
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
