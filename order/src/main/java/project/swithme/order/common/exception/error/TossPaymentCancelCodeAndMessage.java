package project.swithme.order.common.exception.error;

import java.util.Arrays;

public enum TossPaymentCancelCodeAndMessage implements PaymentCodeAndMessage {
    ALREADY_CANCELED_PAYMENT(
        400,
        "ALREADY_CANCELED_PAYMENT",
        "이미 취소된 결제 입니다.",
        "The payment has already been canceled."
    ),
    INVALID_REFUND_ACCOUNT_INFO(
        400,
        "INVALID_REFUND_ACCOUNT_INFO",
        "환불 계좌번호와 예금주명이 일치하지 않습니다.",
        "`accountNumber` and holderName is not matched."
    ),
    EXCEED_CANCEL_AMOUNT_DISCOUNT_AMOUNT(
        400,
        "EXCEED_CANCEL_AMOUNT_DISCOUNT_AMOUNT",
        "즉시할인금액보다 적은 금액은 부분취소가 불가능합니다.",
        "The cancelPayment amount cannot exceed discount amount."
    ),
    INVALID_REQUEST(
        400,
        "INVALID_REQUEST",
        "잘못된 요청입니다.",
        "The bad request."
    ),
    INVALID_REFUND_ACCOUNT_NUMBER(
        400,
        "INVALID_REFUND_ACCOUNT_NUMBER",
        "잘못된 환불 계좌번호입니다.",
        "Incorrect `accountNumber`."
    ),
    INVALID_BANK(
        400,
        "INVALID_BANK",
        "유효하지 않은 은행입니다.",
        "It is an Invalid bank."
    ),
    NOT_MATCHES_REFUNDABLE_AMOUNT(
        400,
        "NOT_MATCHES_REFUNDABLE_AMOUNT",
        "잔액 결과가 일치하지 않습니다.",
        "Balance results do not match."
    ),
    PROVIDER_ERROR(
        400,
        "PROVIDER_ERROR",
        "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요.",
        "This is temporary error. Please try again in a few minutes."
    ),
    REFUND_REJECTED(
        400,
        "REFUND_REJECTED",
        "환불이 거절됐습니다. 결제사에 문의 부탁드립니다.",
        "The refund has been rejected. Please contact the respective payment provider."
    ),
    ALREADY_REFUND_PAYMENT(
        400,
        "ALREADY_REFUND_PAYMENT",
        "이미 환불된 결제입니다.",
        "The payment has been refunded."
    ),
    UNAUTHORIZED_KEY(
        401,
        "UNAUTHORIZED_KEY",
        "인증되지 않은 시크릿 키 혹은 클라이언트 키 입니다.",
        "Unauthorized secretKey or clientKey."
    ),
    NOT_CANCELABLE_AMOUNT(
        403,
        "NOT_CANCELABLE_AMOUNT",
        "취소 할 수 없는 금액 입니다.",
        "This is a non-cancelable amount."
    ),
    FORBIDDEN_CONSECUTIVE_REQUEST(
        403,
        "FORBIDDEN_CONSECUTIVE_REQUEST",
        "반복적인 요청은 허용되지 않습니다. 잠시 후 다시 시도해주세요.",
        "Repetitive requests are not allowed. Please try again in a few minutes."
    ),
    FORBIDDEN_REQUEST(
        403,
        "FORBIDDEN_REQUEST",
        "허용되지 않은 요청입니다.",
        "Not allowed request."
    ),
    NOT_CANCELABLE_PAYMENT(
        403,
        "NOT_CANCELABLE_PAYMENT",
        "취소 할 수 없는 결제 입니다.",
        "This is a non-cancelable payment."
    ),
    EXCEED_MAX_REFUND_DUE(
        403,
        "EXCEED_MAX_REFUND_DUE",
        "환불 가능한 기간이 지났습니다.",
        "Refundable date has passed."
    ),
    NOT_ALLOWED_PARTIAL_REFUND_WAITING_DEPOSIT(
        403,
        "NOT_ALLOWED_PARTIAL_REFUND_WAITING_DEPOSIT",
        "입금 대기중인 결제는 부분 환불이 불가합니다.",
        "Partial refund is not available while pending deposit."
    ),
    NOT_ALLOWED_PARTIAL_REFUND(
        403,
        "NOT_ALLOWED_PARTIAL_REFUND",
        "에스크로 주문, 현금 카드 결제일 때는 부분 환불이 불가합니다. 이외 다른 결제 수단에서 부분 취소가 되지 않을 때는 토스페이먼츠에 문의해 주세요.",
        "Escrow orders or debit card payments cannot be partially canceled. If you are unable to partially cancelPayment your order with any other payment method, please contact Tosspayments."
    ),
    NOT_AVAILABLE_BANK(
        403,
        "NOT_AVAILABLE_BANK",
        "은행 서비스 시간이 아닙니다.",
        "It's not banking hour."
    ),
    INCORRECT_BASIC_AUTH_FORMAT(
        403,
        "INCORRECT_BASIC_AUTH_FORMAT",
        "잘못된 요청입니다. ':' 를 포함해 인코딩해주세요.",
        "Invalid request. Please encode including the ':' character."
    ),
    NOT_CANCELABLE_PAYMENT_FOR_DORMANT_USER(
        403,
        "NOT_CANCELABLE_PAYMENT_FOR_DORMANT_USER",
        "휴면 처리된 회원의 결제는 취소할 수 없습니다.",
        "This is a non-cancelable payment. It is a dormant user account."
    ),
    NOT_FOUND_PAYMENT(
        404,
        "NOT_FOUND_PAYMENT",
        "존재하지 않는 결제 정보 입니다.",
        "Not found payment."
    ),
    FAILED_INTERNAL_SYSTEM_PROCESSING(
        500,
        "FAILED_INTERNAL_SYSTEM_PROCESSING",
        "내부 시스템 처리 작업이 실패했습니다. 잠시 후 다시 시도해주세요.",
        "Internal system processing operation has failed. Please try again in a few minutes."
    ),
    FAILED_REFUND_PROCESS(
        500,
        "FAILED_REFUND_PROCESS",
        "은행 응답시간 지연이나 일시적인 오류로 환불요청에 실패했습니다.",
        "The refund request failed due to a delay in the bank response time or a temporary error."
    ),
    FAILED_METHOD_HANDLING_CANCEL(
        500,
        "FAILED_METHOD_HANDLING_CANCEL",
        "취소 중 결제 시 사용한 결제 수단 처리과정에서 일시적인 오류가 발생했습니다.",
        "A temporary error occurred while processing cancellation."
    ),
    FAILED_PARTIAL_REFUND(
        500,
        "FAILED_PARTIAL_REFUND",
        "은행 점검, 해약 계좌 등의 사유로 부분 환불이 실패했습니다.",
        "Partial refund failed due to bank check, account canceled, etc."
    ),
    COMMON_ERROR(
        500,
        "COMMON_ERROR",
        "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요.",
        "This is temporary error. Please try again in a few minutes."
    ),
    FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING(
        500,
        "FAILED_PAYMENT_INTERNAL_SYSTEM_PROCESSING",
        "결제가 완료되지 않았어요. 다시 시도해주세요.",
        "Payment has not been completed. please try again."
    );

    private final int statusCode;
    private final String errorCode;
    private final String krErrorMessage;
    private final String engErrorMessage;

    TossPaymentCancelCodeAndMessage(
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
        return Arrays.stream(values())
            .filter(x -> x.errorCode.equals(errorCode))
            .findAny()
            .orElseThrow();
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
    public String getEngErrorMessage() {
        return engErrorMessage;
    }
}
