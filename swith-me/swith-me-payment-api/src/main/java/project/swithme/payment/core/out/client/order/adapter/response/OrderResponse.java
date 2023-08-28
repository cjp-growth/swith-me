package project.swithme.payment.core.out.client.order.adapter.response;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class OrderResponse {

    private Long orderId;
    private Long userId;
    private String title;
    private String orderUniqueId;
    private String orderStatus;
    private BigDecimal price;

    private OrderResponse() {
    }

    @Override
    public String toString() {
        return String.format(
            "orderId: %s, userId: %s, title: %s, orderUniqueId: %s, orderStatus: %s, price: %s",
            orderId, userId, title, orderUniqueId, orderStatus, price
        );
    }
}
