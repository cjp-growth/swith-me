package project.swithme.payment.core.out.port.adapter.response.success;

import lombok.Getter;

@Getter
public class TransferResponse {

    private String bankCode;
    private String settlementStatus;

    private TransferResponse() {
    }
}
