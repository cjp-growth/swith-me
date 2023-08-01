package project.swithme.order.core.web.payment.out.adapter.response;

import lombok.Getter;

@Getter
public class TransferResponse {

    private String bankCode;
    private String settlementStatus;

    private TransferResponse() {
    }
}
