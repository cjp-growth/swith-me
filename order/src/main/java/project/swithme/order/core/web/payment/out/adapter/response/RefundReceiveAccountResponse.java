package project.swithme.order.core.web.payment.out.adapter.response;

import lombok.Getter;

@Getter
public class RefundReceiveAccountResponse {

    private String bankCode;
    private String accountNumber;
    private String holderName;

    private RefundReceiveAccountResponse() {
    }
}
