package project.swithme.payment.core.exception;

import lombok.Getter;

@Getter
public class PaymentErrorResponse {

    private String code;
    private String message;

    private PaymentErrorResponse() {
    }

    @Override
    public String toString() {
        return String.format("code: %s, message: %s", code, message);
    }
}
