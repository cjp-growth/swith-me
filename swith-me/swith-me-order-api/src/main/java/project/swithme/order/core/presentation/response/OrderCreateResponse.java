package project.swithme.order.core.presentation.response;

import lombok.Getter;

@Getter
public class OrderCreateResponse {

    private Long orderId;

    private OrderCreateResponse() {
    }

    @Override
    public String toString() {
        return orderId.toString();
    }
}
