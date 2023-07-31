package project.swithme.order.core.web.payment.out.adapter.response;

import lombok.Getter;

@Getter
public class MobilePhoneResponse {

    private String customerMobilePhone;
    private String settlementStatus;
    private String receiptUrl;

    private MobilePhoneResponse() {
    }
}
