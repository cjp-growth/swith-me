package project.swithme.payment.core.out.adapter.response;

import lombok.Getter;

@Getter
public class GiftCertificateResponse {

    private String approveNo;
    private String settlementStatus;

    private GiftCertificateResponse() {
    }
}
