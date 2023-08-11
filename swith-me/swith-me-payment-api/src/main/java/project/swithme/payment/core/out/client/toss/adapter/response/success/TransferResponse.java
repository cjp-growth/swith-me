package project.swithme.payment.core.out.client.toss.adapter.response.success;

import lombok.Getter;

@Getter
public class TransferResponse {

    private String bankCode;
    private String settlementStatus;

    private TransferResponse() {
    }
}
