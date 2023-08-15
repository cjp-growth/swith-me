package project.swithme.order.core.presentation.command.response;

import lombok.Getter;

@Getter
public class OrderCreateResponse {

    private Long orderId;

    private OrderCreateResponse() {
    }

    public OrderCreateResponse(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return orderId.toString();
    }
}
