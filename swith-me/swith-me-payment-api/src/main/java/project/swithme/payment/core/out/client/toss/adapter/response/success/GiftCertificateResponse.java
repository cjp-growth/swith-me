package project.swithme.payment.core.out.client.toss.adapter.response.success;

import lombok.Getter;

@Getter
public class GiftCertificateResponse {

    private String approveNo;
    private String settlementStatus;

    private GiftCertificateResponse() {
    }
}
