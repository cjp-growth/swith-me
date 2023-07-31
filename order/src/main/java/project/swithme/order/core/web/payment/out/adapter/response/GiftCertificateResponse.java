package project.swithme.order.core.web.payment.out.adapter.response;

import lombok.Getter;

@Getter
public class GiftCertificateResponse {

    private String approveNo;
    private String settlementStatus;

    private GiftCertificateResponse() {
    }
}
