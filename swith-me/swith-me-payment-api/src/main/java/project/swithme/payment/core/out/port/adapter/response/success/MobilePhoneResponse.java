package project.swithme.payment.core.out.port.adapter.response.success;

import lombok.Getter;

@Getter
public class MobilePhoneResponse {

    private String customerMobilePhone;
    private String settlementStatus;
    private String receiptUrl;

    private MobilePhoneResponse() {
    }
}
