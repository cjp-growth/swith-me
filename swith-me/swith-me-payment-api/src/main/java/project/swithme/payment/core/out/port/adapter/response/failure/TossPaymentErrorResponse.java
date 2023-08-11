package project.swithme.payment.core.out.port.adapter.response.failure;

import lombok.Getter;

@Getter
public class TossPaymentErrorResponse {

    private String code;
    private String message;

    private TossPaymentErrorResponse() {
    }

    @Override
    public String toString() {
        return String.format("code: %s, message: %s", code, message);
    }
}
