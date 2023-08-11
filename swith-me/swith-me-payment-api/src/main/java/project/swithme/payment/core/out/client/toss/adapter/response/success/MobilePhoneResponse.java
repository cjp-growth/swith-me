package project.swithme.payment.core.out.client.toss.adapter.response.success;

import lombok.Getter;

@Getter
public class MobilePhoneResponse {

    private String customerMobilePhone;
    private String settlementStatus;
    private String receiptUrl;

    private MobilePhoneResponse() {
    }
}
