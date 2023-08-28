package project.study.support.codeandmessage.order;

import project.study.support.codeandmessage.CodeAndMessage;

public enum OrderCodeAndMessage implements CodeAndMessage {
    INVALID_ORDER_ID(400, "올바른 주문 아이디를 입력해주세요."),
    INVALID_PRICE(400, "올바른 주문 가격을 입력해주세요."),
    INVALID_ORDER_STATUS(400, "올바르지 않은 주문 상태입니다."),
    ORDER_NOT_FOUND(404, "주문을 찾을 수 없습니다."),
    INVALID_ORDER(404, "올바른 주문이 아니라 조회할 수 없습니다.");

    private final int code;
    private final String message;

    OrderCodeAndMessage(
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
