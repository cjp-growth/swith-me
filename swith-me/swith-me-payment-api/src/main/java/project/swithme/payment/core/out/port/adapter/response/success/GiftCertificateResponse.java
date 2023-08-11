package project.swithme.payment.core.out.port.adapter.response.success;

import lombok.Getter;

@Getter
public class GiftCertificateResponse {

    private String approveNo;
    private String settlementStatus;

    private GiftCertificateResponse() {
    }
}
