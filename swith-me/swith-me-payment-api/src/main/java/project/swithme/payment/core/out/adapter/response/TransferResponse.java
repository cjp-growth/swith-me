package project.swithme.payment.core.out.adapter.response;

import lombok.Getter;

@Getter
public class TransferResponse {

    private String bankCode;
    private String settlementStatus;

    private TransferResponse() {
    }
}
