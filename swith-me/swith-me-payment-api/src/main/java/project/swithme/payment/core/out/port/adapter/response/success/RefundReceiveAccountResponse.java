package project.swithme.payment.core.out.port.adapter.response.success;

import lombok.Getter;

@Getter
public class RefundReceiveAccountResponse {

    private String bankCode;
    private String accountNumber;
    private String holderName;

    private RefundReceiveAccountResponse() {
    }
}
