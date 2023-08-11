package project.swithme.payment.core.out.port.adapter.response.success;

import lombok.Getter;

@Getter
public class CheckoutResponse {

    private String url;

    private CheckoutResponse() {
    }
}
